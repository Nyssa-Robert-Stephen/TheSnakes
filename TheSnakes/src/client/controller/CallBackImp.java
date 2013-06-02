package client.controller;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.JOptionPane;

import client.model.SnakeGame;
import client.view.ClientFrame;

import shared.controller.CallBack;
import shared.controller.SnakeGameInterface;
import shared.model.Food;
import shared.model.Player;

@SuppressWarnings("serial")
public class CallBackImp extends UnicastRemoteObject implements CallBack,SnakeGameInterface{

	SnakeGame snakeGame;
	
	public CallBackImp(SnakeGame  snakeGame) throws RemoteException{
		this.snakeGame = snakeGame;
	}
	@Override
	public void sendGameState(List<Player> players) throws RemoteException {

		for(int i=0; i<players.size(); i++)
		{
			snakeGame.getClientFrame().getGrid().addSnake(players.get(i).getSnake().getSegments());
		}
		snakeGame.getClientFrame().repaint();
		

		
	}

	@Override
	public void sendFood(List<Food> food) throws RemoteException {
		snakeGame.getClientFrame().getGrid().addFood(food);
	}

	@Override
	public void sendPlayerStatus(int state) throws RemoteException {
		switch(state){
		case STATUS_LOSE:
			JOptionPane.showMessageDialog(snakeGame.getClientFrame(), "You have lost.");
			snakeGame.getClientFrame().getSp().setLbl_Status("LOST!!");
			break;
		case STATUS_WIN:
			JOptionPane.showMessageDialog(snakeGame.getClientFrame(), "You have won.");
			snakeGame.getClientFrame().getSp().setLbl_Status("WINNER!");
			break;
		case STATUS_WAIT:
			snakeGame.getClientFrame().getSp().setLbl_Status("Waiting..");
			break;
		case STATUS_PLAYING:
			snakeGame.getClientFrame().getSp().setLbl_Status("Playing!");
			break;
		}
		
	}

	@Override
	public void setGridSize(Point p) throws RemoteException {
		snakeGame.getClientFrame().setGrid(p.x, p.y);
		
	}

}
