package client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.controller.ExitListener;
import client.controller.MoveListener;
import client.model.SnakeGame;
import client.model.SocketHandler;

@SuppressWarnings("serial")
public class ClientFrame extends JFrame implements shared.controller.SnakeGameInterface
{
	
		private SnakeGame snakeGame;
		private boolean join = false;
		private boolean create = false;
		private int windowWidth = 1250;
		private int windowHeight = 500;
		private Grid grid; 
		private RightSideBar rsb = new RightSideBar(this);
		private LeftSideBar lsb = new LeftSideBar(this);
		private JPanel empty1 = new JPanel();
		private JPanel empty2= new JPanel();
		

		public ClientFrame() {
			this.addWindowListener(new ExitListener(this));
			this.setLayout(new GridLayout(1,3));
	        this.setSize(windowWidth, windowHeight);
	        this.setResizable(true);
	        this.setLocationRelativeTo(null);
	        

	        this.add(lsb);
	        this.add(empty1);
	        this.add(empty2);
		       this.lsb.getIp().getJoin().addKeyListener(new MoveListener(this));
	        
	        //grid.addKeyListener(new MoveListener(this));
	        
	        //this.getCb().getBtn_join().addKeyListener(new MoveListener(this));
	       // this.getCb().getBtn_create().addKeyListener(new MoveListener(this));
	        
	        this.setVisible(true);
	        // this enables double buffering
	        // sometime it doesn't like to work and throws an exception :(
	        //this.createBufferStrategy(2);
		}



		public Grid getGrid() {
			return grid;
		}
		
		public boolean isCreate() {
			return create;
		}


		public void setCreate(boolean create) {
			this.create = create;
		}


		public boolean isJoin() {
			// TODO Auto-generated method stub
			return join;
		}
		public void setJoin(Boolean b){
			this.join = b;
		}
		
		public void setGrid(int length, int width)
		{
			grid = new Grid(length, width);

			this.remove(empty1);
			this.remove(empty2);
	        this.add(grid);
	        this.add(rsb);
	        this.setVisible(true);
		}


		public LeftSideBar getLSB() {
			return lsb;
		}


		public StatusPanel getSp() {
			return rsb.getSp();
		}



		public SnakeGame getSnakeGame() {
			return snakeGame;
		}
		public static void main(String args[]){
			try {
				SnakeGame s = new SnakeGame(new ClientFrame());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		public void setSnakeGame(SnakeGame sg) {
			this.snakeGame = sg;
			
		}
}
