package server.controller;

import java.awt.Point;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Map;

import server.model.GameLogic;
import shared.controller.CallBack;
import shared.controller.SnakeServer;

public class TheServer implements SnakeServer, Runnable {
	
	private Map<String, CallBack> clientMap;
	
	private int numPlayers;
	private Point bounds;
	
	private final GameLogic gameLogic;
	
	public TheServer(int numPlayers, Point bounds, int port) {
		this.bounds = bounds;
		this.gameLogic = new GameLogic(this.bounds);
		this.numPlayers = numPlayers;
		// set up RMI server
        try {
			Naming.rebind("//localhost/TheServer", this);
			// set up database connection
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		boolean gameOver = false;
		while(!gameOver) {
			gameLogic.step();
			
			if(clientMap.size() == 1){
				// someone has won
			}
			/*
			 * Send info to clients. Loop through the 
			 * clientMap and call methods for each CallBack
			 */
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int setPosition(int tryPosition) {
		return 0;
	}
	@Override
	public void sendMove(String username, int dx, int dy)
			throws RemoteException {
		gameLogic.sendMove(username,dx,dy);
		
	}
	@Override
	public boolean login(String username, String password) 
			throws RemoteException {
		// forward call to database
		return false;
	}
	@Override
	public boolean regsiter(String username, String pasword, String firstname,
			String lastname, String address, String ph_number)
			throws RemoteException {
		//forward call to database
		return false;
	}

}
