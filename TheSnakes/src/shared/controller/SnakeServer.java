package shared.controller;

import java.rmi.RemoteException;
/*
 * RMIT interface for the server. These methods are called by the client.
 */
public interface SnakeServer extends java.rmi.Remote {

	public void sendMove(String username, int dx, int dy) throws RemoteException;
	
	public boolean login(String username, String password) throws RemoteException;
	
	public boolean regsiter(String username, String pasword, 
			String firstname, String lastname, String address, String ph_number) throws RemoteException;
	
}
