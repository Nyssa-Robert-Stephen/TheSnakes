package server.model;


import java.awt.Color;
import java.awt.Rectangle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SnakeRunnable extends Snake implements Runnable {
	
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	GameLogic gameLogic;
	
	public SnakeRunnable(Socket socket, GameLogic gameLogic, int x, int y, Color color, Rectangle bounds) throws IOException
	{
		super(x, y, color, bounds);
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
	
	}
	
	public void getMoves()
	{
		try
		{
			while(true)
			{
				//read in from client.
				//This is where we would receive Stephen's snake maneuvers
				//And the ID of the snake
				//if there is a message, break loop.
				//and update the snake moves.
				int moves = in.readInt();
				//Then move Snake with information in message.
				
				moveSnake(moves);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void moveSnake(int moves)
	{
		//This is the method that would check the proposed Snake position
		//with the rest of the game logic.  Like a
		gameLogic.checkPosition(moves);
		//kind of deal
		//Depending on the result
		//The snake will move, die, eat an apple
		//And we will send that back to the client.
		//The GameLogic will then tell all the snakes where everyone is at.	
	}
	
	public DataOutputStream getOut()
	{
		return out;
	}

	@Override
	public void run() {
		getMoves();		
	}

}
