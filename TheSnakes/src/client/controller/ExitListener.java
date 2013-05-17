package client.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import shared.controller.SnakeGameInterface;

import client.view.ClientFrame;

//class ensures sockets are closed before closing
public class ExitListener implements WindowListener, SnakeGameInterface{
	ClientFrame cf;
	public ExitListener(ClientFrame clientFrame) {
		this.cf = clientFrame;
		

	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		int confirm = JOptionPane.showOptionDialog(cf, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
        	if(cf.isJoin()) {
        		try {
        			cf.getSockHandler().sendMove(MOVE_EXIT);
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
        	}
        	
        	cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if(confirm == 1) {
        	cf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
