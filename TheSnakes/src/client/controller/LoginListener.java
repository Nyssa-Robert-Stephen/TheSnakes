package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import client.view.LeftSideBarPackage.LoginPanel;

public class LoginListener implements ActionListener{
	LoginPanel lp;
	public LoginListener(LoginPanel cf){
		this.lp = cf;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//verify boxes aren't empty.
		if(lp.getTxt_uname().getText().toString() != null &&lp.getTxt_pwd().getText().toString() != null){
			
	try {
		
		if(lp.getCf().getSnakeGame().getTheServer().login(lp.getTxt_uname().getText().toString(),lp.getTxt_pwd().getText().toString())){
			lp.getCf().getLSB().getIp().getLogin().setEnabled(false);
			lp.getCf().getLSB().getIp().getRegister().setEnabled(false);
			
		}
	} catch (RemoteException e) {

		e.printStackTrace();
	}
		}
	}
}
