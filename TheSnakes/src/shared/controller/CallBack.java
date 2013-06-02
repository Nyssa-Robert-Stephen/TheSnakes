package shared.controller;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.List;

import shared.model.Food;
import shared.model.Player;
/*
 * RMI callback interface for the client. These methods are called by the server.
 */
public interface CallBack extends java.rmi.Remote {

	public void sendGameState(List<Player> players) throws RemoteException;
	
	public void sendFood(List<Food> food) throws RemoteException;
	
	public void sendPlayerStatus(int state) throws RemoteException;
	
	public void setGridSize(Point p) throws RemoteException;

}
