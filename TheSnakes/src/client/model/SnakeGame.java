package client.model;

import java.awt.Point;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import server.controller.TheServer;
import shared.controller.SnakeServer;
import shared.model.Player;
import client.view.ClientFrame;

public class SnakeGame extends UnicastRemoteObject{

	private ClientFrame clientFrame;
	private SnakeServer theServer;
	
	public SnakeGame(ClientFrame cf ) throws RemoteException
	{
		this.setClientFrame(cf);
		cf.setSnakeGame(this);
		//RMI join server stuff goes here.
	
	}
	private void createGame(){

			System.out.println("starting..");

			Point p = new Point(getClientFrame().getLSB().getDimensions(),getClientFrame().getLSB().getDimensions());
			
			try {
				CreateServer(getClientFrame().getLSB().getPlayers(),p,getClientFrame().getLSB().getHostPort());
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				System.out.println("created");
	}


	//Constantly run while a game is in progress.
	public void gameUpdate(List<Player> players) throws IOException, ClassNotFoundException {


		
			for(int i=0; i<players.size(); i++)
			{
				getClientFrame().getGrid().addSnake(players.get(i).getSnake().getSegments());
			}

			getClientFrame().repaint();




		//Do something when a game is over (win or lose)

		

	}

	public void CreateServer(int players, Point bounds,int port) throws AlreadyBoundException{
		try {
			Runnable server = new TheServer(players, bounds, port);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ClientFrame getClientFrame() {
		return clientFrame;
	}
	private void setClientFrame(ClientFrame cf) {
		this.clientFrame = cf;
		
	}

	public SnakeServer getTheServer() {
		return theServer;
	}

	
	public boolean Register(String username, String pasword, 
			String firstname, String lastname, String address, String ph_number){
			try {
				theServer.regsiter("test", "test", "test", "test", "test", "111");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	
	public boolean JoinServer() {
		try{
		theServer  = (SnakeServer) Naming.lookup("localhost/TheServer");
		}
		catch(Exception e){
			return false;
		}
				return true;
		
	}



}
