package client.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import client.model.SnakeGame;
import client.view.ClientFrame;

public class MoveListener implements KeyListener, shared.controller.SnakeGameInterface {
	
	SnakeGame snakeGame;
	
	public MoveListener(ClientFrame cf)
	{
		this.snakeGame = cf.getSnakeGame();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();  
		 
		 try{
			 switch(key) {
			 	case KeyEvent.VK_LEFT:
			 		snakeGame.getTheServer().sendMove(snakeGame.getClientFrame().getLSB().getPName(),-1,0);
			 		break;
			 	case KeyEvent.VK_UP:
			 		snakeGame.getTheServer().sendMove(snakeGame.getClientFrame().getLSB().getPName(),0,-1);
			 		break;
			 	case KeyEvent.VK_RIGHT:
			 		snakeGame.getTheServer().sendMove(snakeGame.getClientFrame().getLSB().getPName(),1,0);
			 		break;
			 	case KeyEvent.VK_DOWN:
			 		snakeGame.getTheServer().sendMove(snakeGame.getClientFrame().getLSB().getPName(),0,1);
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
