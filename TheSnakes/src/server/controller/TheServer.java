package server.controller;

import java.awt.Point;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import server.model.DBHandler;
import server.model.GameLogic;
import shared.controller.CallBack;
import shared.controller.SnakeServer;
import shared.model.Player;

public class TheServer implements SnakeServer, Runnable {
	
	private Map<String, CallBack> clientMap;
	
	private int numPlayers;
	private Point bounds;
	
	private final GameLogic gameLogic;
	
	private final DBHandler db = new DBHandler();
	
	public TheServer(int numPlayers, Point bounds, int port) {
		this.bounds = bounds;
		this.gameLogic = new GameLogic(this.bounds);
		this.numPlayers = numPlayers;
		
		db.openDatabase();
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
			
			List<Player> players = gameLogic.getPlayers();
			for(Player p: players){
				if(p.isDead()){
					// send them a message saying they have lost.
				}
			}
			
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
		//The Server is "over" at the end of the run method
		//Safe to close the database
		db.closeDatabase();
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
	public boolean login(String username, String password) throws RemoteException {
		//checkDatabase() returns a boolean
		//however it won't work unless you're absolutely certain the server is running on Nyssa's laptop
		//The Official Home of The Snakes Database
		//return db.checkDatabase(username, password);
		return true;
	}
	@Override
	public boolean regsiter(String username, String password, String firstname,
			String lastname, String address, String ph_number)
			throws RemoteException {
		//inputIntoDatabase() returns a boolean
		//however it won't work unless you're absolutely certain the server is running on Nyssa's laptop
		//The Official Home of The Snakes Database
		//return db.inputIntoDatabase(username, password, firstname, lastname, address, ph_number);
		return true;
	}

}
