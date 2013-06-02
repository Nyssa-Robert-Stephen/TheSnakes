package client.controller;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.JOptionPane;

import client.view.ClientFrame;

import shared.controller.CallBack;
import shared.controller.SnakeGameInterface;
import shared.model.Food;
import shared.model.Player;

@SuppressWarnings("serial")
public class CallBackImp extends UnicastRemoteObject implements CallBack,SnakeGameInterface{

	ClientFrame clientFrame;
	
	public CallBackImp(ClientFrame  cf) throws RemoteException{
		this.clientFrame = cf;
	}
	@Override
	public void sendGameState(List<Player> players) throws RemoteException {

		for(int i=0; i<players.size(); i++)
		{
			clientFrame.getGrid().addSnake(players.get(i).getSnake().getSegments());
		}
		clientFrame.repaint();

		
	}

	@Override
	public void sendFood(List<Food> food) throws RemoteException {
		clientFrame.getGrid().addFood(food);
	}

	@Override
	public void sendPlayerStatus(int state) throws RemoteException {
		switch(state){
		case STATUS_LOSE:
			JOptionPane.showMessageDialog(clientFrame, "You have lost.");
			clientFrame.getSp().setLbl_Status("LOST!!");
			break;
		case STATUS_WIN:
			JOptionPane.showMessageDialog(clientFrame, "You have won.");
			clientFrame.getSp().setLbl_Status("WINNER!");
			break;
		case STATUS_WAIT:
			clientFrame.getSp().setLbl_Status("Waiting..");
			break;
		case STATUS_PLAYING:
			clientFrame.getSp().setLbl_Status("Playing!");
			break;
		}
		
	}

	@Override
	public void setGridSize(Point p) throws RemoteException {
		clientFrame.setGrid(p.x, p.y);
		
	}

}
