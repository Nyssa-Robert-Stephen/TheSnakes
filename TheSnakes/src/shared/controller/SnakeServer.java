package shared.controller;

import java.rmi.RemoteException;

/*
 * RMIT interface for the server. These methods are called by the client.
 */
public interface SnakeServer extends java.rmi.Remote {

	void sendMove(String username, int dx, int dy) throws RemoteException;
	boolean addPlayer(String name, CallBack cb, int pos)throws RemoteException;
	boolean login(String username, String password) throws RemoteException;
	
	boolean regsiter(String username, String pasword, 
			String firstname, String lastname, String address, String ph_number) throws RemoteException;
	
}
