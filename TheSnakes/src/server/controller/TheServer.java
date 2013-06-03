package server.controller;

import java.awt.Point;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import server.model.DBHandler;
import server.model.GameLogic;
import shared.controller.CallBack;
import shared.controller.SnakeGameInterface;
import shared.controller.SnakeServer;
import shared.model.Player;

public class TheServer extends UnicastRemoteObject
							implements SnakeServer, Runnable {
	
	private Map<String, CallBack> clientMap = new HashMap();
	
	private int numPlayers;
	private int curr_num_players = 0;
	private Point bounds;
	private boolean running = false;
	private int pos[] = {0,0,0,0};
	
	private transient final GameLogic gameLogic;
	
	private transient final DBHandler db = new DBHandler();
	
	public TheServer(int numPlayers, Point bounds, int port) throws RemoteException {
		this.bounds = bounds;
		this.gameLogic = new GameLogic(this.bounds);
		this.numPlayers = numPlayers;
		
		db.openDatabase();
		// set up RMI server
        try {
			Naming.rebind("//localhost/SnakeServer", this);
			// set up database connection
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		int i = 0;
		while(running) {
			List<Player> new_players = new ArrayList<Player>();
			                       
			                       for(String name : clientMap.keySet()) {
			                    	  Player p = new Player(name);
			                    	  p.setPosition(pos[i]);
			                               new_players.add(p);
			                               i++;
			                       }
			                       gameLogic.setPlayers(new_players);
			boolean gameOver = false;
			while(!gameOver) {
				gameLogic.step();	
				try {
					System.out.println("In the game");
					List<Player> players = gameLogic.getPlayers();
					for(Player p: players){
						if(p.isDead()){
							//Get username to identify them in database
							String name = p.getName();
							// send them a message saying they have lost.
							CallBack cb = clientMap.get(name);
							cb.sendPlayerStatus(SnakeGameInterface.STATUS_LOSE);
							clientMap.remove(name);
							//Calculating a Player's Score		
							/*
							 * A player's score is determined by how much food they ate
							 * multiplied by their ranking in the game.
							 * As the isDead() call removes them from the player map
							 * The player map is an ideal way to find their ranking.
							 */
							int ranking = 4 - gameLogic.getPlayers().size();
							int foodIntake = p.getScore();
							int score = ranking * foodIntake;
							
							//Update their score in the database
							db.updateScore(name, score);
						}
					}
					
					if(clientMap.size() == 1){
						// someone has won
						gameOver = true;
						running = false;
						//There should only be one player in this map by now
						for(Player p: players)
						{
							String name = p.getName();
							CallBack cb = clientMap.get(name);
							//cb.sendPlayerStatus(SnakeGameInterface.STATUS_WIN);
							int foodIntake = p.getScore();
							int score = 4 * foodIntake;
							
							db.updateScore(name, score);
						}
					}
					/*
					 * Send info to clients. Loop through the 
					 * clientMap and call methods for each CallBack
					 */
					for(CallBack cb : clientMap.values()) {
						cb.sendFood(gameLogic.getFood());
						cb.sendGameState(players);
					}
					
					try {
						Thread.sleep(75);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch(RemoteException e) {
					
				}
			}
			//The Server is "over" at the end of the run method
			//Safe to close the database
			db.closeDatabase();
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
	public boolean login(String username, String password) throws RemoteException {
		//checkDatabase() returns a boolean
		//however it won't work unless you're absolutely certain the server is running on Nyssa's laptop
		//The Official Home of The Snakes Database
		return db.checkDatabase(username, password);
		//return true;
	}
	@Override
	public boolean regsiter(String username, String password, String firstname,
			String lastname, String address, String ph_number)
			throws RemoteException {
		//inputIntoDatabase() returns a boolean
		//however it won't work unless you're absolutely certain the server is running on Nyssa's laptop
		//The Official Home of The Snakes Database
		return db.inputIntoDatabase(username, password, firstname, lastname, address, ph_number);
		//return true;
	}
	@Override
	public boolean addPlayer(String name, CallBack cb, int position) throws RemoteException {
		/*
		 * Add player to the callback
		 */
		if(curr_num_players == numPlayers) {
			return false;
			/*
			 * Send a message to the client that there is no more room 
			 * (game is underway)
			 */
		} else {
			System.out.println("Added: " + name);
			pos[curr_num_players++] = position;
			clientMap.put(name, cb);
			cb.setGridSize(bounds);
			if(curr_num_players == numPlayers) {
				Thread s= new Thread(this);
				s.start();
				System.out.println("Starting..");
				running = true;
				
			}
			return true;
		}
	}

}