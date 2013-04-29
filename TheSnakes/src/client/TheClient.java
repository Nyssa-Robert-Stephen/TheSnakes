package client;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import server.model.Packet;
import client.controller.MoveListener;

@SuppressWarnings("serial")
public class TheClient extends JFrame
{
	
	/*
	 * This is just a test class I made for drawing the snake to see that it works
	 * I imagine we would have similar methods like run(), gameInit() and gameLoop() somewhere on the server
	 */


		//private Snake test_player;
		//private Snake test_player2;
		
		private int windowWidth = 800;
		private int windowHeight = 600;
		
		private int sendThis;
		private ObjectInputStream in;
		private DataOutputStream out;
		private Packet info;
		Grid g1 = new Grid(50, 50);
		InitialPlayerBar cb = new InitialPlayerBar(this);
		public TheClient() {
			this.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(windowWidth, windowHeight);
	        this.setResizable(false);
	        this.setLocationRelativeTo(null);
	        g1.addKeyListener(new MoveListener());
	        this.add(g1,BorderLayout.CENTER);
	        this.setVisible(true);
	    	int option = JOptionPane.showConfirmDialog(null,  cb,
	                "Ready? ",
	                JOptionPane.OK_OPTION,
	                JOptionPane.PLAIN_MESSAGE);
	        // this enables double buffering
	        // sometime it doesn't like to work and throws an exception :(
	        this.createBufferStrategy(2);

			initGame();
		}

		private void initGame() {
			//This is where we will initialise a connection with the server
			Rectangle bounds = new Rectangle(windowWidth,windowHeight);
			
			try 
			{
				Socket socket = new Socket("localhost", 1985);
				
				in = new ObjectInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				
				try {
					info = (Packet) in.readObject();
					System.out.println(info);
					out.writeUTF(cb.getPlayerName() + cb.getPlayers());
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
				
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			/* This code starts a new snake
			 * but what we actually need to do is work with the protocol
			 * to use something like out.writeUTF(all the snake gear);
			 * 
			 * test_player = new Snake(10,10,Color.BLUE,bounds,10);
			 * test_player2 = new Snake(20,20,Color.RED,bounds,10);
			 * dx = 0;
			 * dy = 0;
			 */
		}
		public void run() {
			while(true) {
				gameLoop();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void gameLoop() {
			//test_player.move(dx, dy); 
			try {
				out.writeInt(sendThis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void setSendThis(int move)
		{
			/*
			 * This method is called from the MoveListener
			 * where "move" is which key was pressed
			 */
			sendThis = move;
		}
		
		public static void main(String args[]){
			new TheClient().run();
		}

}
