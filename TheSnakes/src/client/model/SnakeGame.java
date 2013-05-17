package client.model;

import java.awt.Point;
import java.io.IOException;

import javax.swing.JOptionPane;

import server.controller.TheServer;
import shared.controller.SnakeGameInterface;
import shared.model.Packet;
import client.view.ClientFrame;

public class SnakeGame extends Thread implements SnakeGameInterface{

	private ClientFrame clientFrame;
	private Packet info;
	private int gameStatus = STATUS_WAIT;
	
	public SnakeGame(ClientFrame clientFrame)
	{
		this.clientFrame = clientFrame;
		
		while(!clientFrame.isCreate() && !clientFrame.isJoin()){
			System.out.println("not Ready!!");
		}
		
		if(clientFrame.isCreate()) {
			System.out.println("creating..");
			createGame();
		}  
			while(!clientFrame.isJoin()) {
				System.out.println("join me!");
			}
			initGame();
		
	}
	
	private void createGame(){

			System.out.println("starting..");

			Point p = new Point(clientFrame.getLSB().getDimensions(),clientFrame.getLSB().getDimensions());
			
			CreateServer(clientFrame.getLSB().getPlayers(),p,clientFrame.getLSB().getHostPort());
				System.out.println("created");
	}

	//TODO Connect to the host and get ready to start a game.
	private void initGame() {
		try 
		{
			
			
			
			if(clientFrame.getSockHandler().initConnection(clientFrame.getLSB().getIp_address(), clientFrame.getLSB().getJoinPort()))
				System.out.println("Succeeded!");
			else
				System.out.println("Failed");
			try {
				Packet p = (Packet) clientFrame.getSockHandler().getIn().readObject();
				Point point = p.getPoint();
				clientFrame.setGrid(point.x, point.y);
				System.out.println(clientFrame.getLSB().getPName());
				clientFrame.getSockHandler().getOut().writeUTF(clientFrame.getLSB().getPName() + (clientFrame.getLSB().getPosition()));
				//clientFrame.getSockHandler().getOut().flush();
				info = (Packet) clientFrame.getSockHandler().getIn().readObject();
				while(info.getGameStatus() != STATUS_PLAYING){
					System.out.println("waiting..");
					info = (Packet) clientFrame.getSockHandler().getIn().readObject();
					if(info.getGameStatus()==MOVE_EXIT)
						this.close();
				}
				this.start();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}

		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	//Constantly run while a game is in progress.
	public void gameStart() throws IOException, ClassNotFoundException {
		Packet pack;

		while(gameStatus != STATUS_LOSE && gameStatus != STATUS_WIN) {
			pack = 	(Packet)clientFrame.getSockHandler().getIn().readObject();
			for(int i=0; i<pack.getPlayers().size(); i++)
			{
				clientFrame.getGrid().addSnake(pack.getPlayers().get(i).getSnake().getSegments());
			}
			clientFrame.getGrid().addFood(pack.getFood());
			clientFrame.repaint();
			gameStatus = pack.getGameStatus();
			clientFrame.getSp().setLbl_amountPlayers(Integer.toString(pack.getPlayers().size()));
			clientFrame.getSp().setLbl_Status(Integer.toString(pack.getGameStatus()));
			//clientFrame.getSp().setLbl_color(pack.getPlayers())
			
			try{
				Thread.sleep(75);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		//TODO Do something when a game is over (win or lose)
		if(gameStatus == STATUS_LOSE){
			JOptionPane.showMessageDialog(clientFrame, "You have lost.");
		}else if(gameStatus == STATUS_WIN){
			JOptionPane.showMessageDialog(clientFrame, "You have won.");
		}
		

	}

	public void CreateServer(int players, Point bounds,int port){
		TheServer server = new TheServer(players, bounds, port);
		new Thread(server).start();
	}
	public void run()
	{
		try {
			gameStart();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try {
			clientFrame.getSockHandler().getOut().close();
			clientFrame.getSockHandler().getIn().close();
			clientFrame.getSockHandler().getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
