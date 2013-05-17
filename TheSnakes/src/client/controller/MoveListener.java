package client.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import client.view.ClientFrame;

public class MoveListener implements KeyListener, shared.controller.SnakeGameInterface {
	
	ClientFrame client;
	
	public MoveListener(ClientFrame client)
	{
		this.client = client;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();  
		 try{
			 switch(key) {
			 	case KeyEvent.VK_LEFT:
			 		client.getSockHandler().sendMove(MOVE_LEFT);
			 		break;
			 	case KeyEvent.VK_UP:
			 		client.getSockHandler().sendMove(MOVE_FASTER);
			 		break;
			 	case KeyEvent.VK_RIGHT:
			 		client.getSockHandler().sendMove(MOVE_RIGHT);
			 		break;
			 	case KeyEvent.VK_DOWN:
			 		client.getSockHandler().sendMove(MOVE_SLOWER);
			 		break;
			 }
		 }catch(IOException ex){
	    	 ex.printStackTrace();
	     }
	     

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
