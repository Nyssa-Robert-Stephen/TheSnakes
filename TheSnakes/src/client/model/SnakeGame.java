package client.model;

import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.io.IOException;

import server.controller.TheServer;
import shared.Packet;
import shared.SnakeInterface;
import client.controller.MoveListener;
import client.view.ClientFrame;

public class SnakeGame extends Thread implements SnakeInterface{

	private ClientFrame clientFrame;
	private Packet info;
	private int gameStatus = STATUS_WAIT;
	private String playerName;
	private int pId;
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

				//Point p = new Point(Integer.parseInt(clientFrame.getCb().getField_len().getText()),Integer.parseInt(clientFrame.getCb().getField_width().getText()));
				
				CreateServer(clientFrame.getCb().getPlayers(),null);
				System.out.println("created");
	}

	//TODO Connect to the host and get ready to start a game.
	private void initGame() {
		try 
		{
			

			
			clientFrame.getSockHandler().initConnection("localhost");
			try {
				clientFrame.getSockHandler().getOut().writeUTF(clientFrame.getCb().getPlayerName() + (clientFrame.getCb().getCbx_pos().getSelectedItem()));
				clientFrame.getSockHandler().getOut().flush();
				info = (Packet) clientFrame.getSockHandler().getIn().readObject();
				while(info.getGameStatus() != STATUS_PLAYING){
				System.out.println("waiting..");
				info = (Packet) clientFrame.getSockHandler().getIn().readObject();
				}
				this.start();
				//System.out.println("Snake 0: " + info.getPlayers().get(0).getSnake().getSegments().toString() + " " + info.getPlayers().get(0).getColor().toString() );

//				for(int i = 0; i < info.getPlayers().size(); i++){
//					if(playerName == info.getPlayers().get(i).getName())
//						pId = i;
//				}
					//clientFrame.getSp().getLbl_curColour().setText(info.getPlayer(pId).getColor().toString());
					
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

		System.out.println(gameStatus);
		while(gameStatus != STATUS_LOSE && gameStatus != STATUS_WIN) {
			//System.out.println("working! ");
			//clientFrame.getSockHandler().getIn
			pack = 	(Packet)clientFrame.getSockHandler().getIn().readObject();
			//System.out.println("Players: " + pack.getPlayers().get(1).getSnake().getHeadPos().toString());
			//System.out.println("Packet: " + pack);
			for(int i=0; i<pack.getPlayers().size(); i++)
			{
				clientFrame.getGrid().addSnake(pack.getPlayers().get(i).getSnake().getSegments());
			}
			clientFrame.getGrid().addFood(pack.getFood());
			clientFrame.repaint();
			//clientFrame.getGrid().repaint();
			gameStatus = pack.getGameStatus();
			try{
				Thread.sleep(75);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		//TODO Do something when a game is over (win or lose)

	}

	public void CreateServer(int players, Point bounds){
		TheServer server = new TheServer(players, bounds);
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

}
