package client.model;

import java.awt.Point;
import java.io.IOException;
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
			
			CreateServer(getClientFrame().getLSB().getPlayers(),p,getClientFrame().getLSB().getHostPort());
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

	public void CreateServer(int players, Point bounds,int port){
		Runnable server = new TheServer(players, bounds, port);
		new Thread(server).start();
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

	
	public boolean Register(){
		
		return true;
	}
	
	public boolean JoinServer() {
		//if(theServer.Join())
				return true;
		
	}



}
