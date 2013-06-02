package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.LeftSideBar;
import client.view.LeftSideBarPackage.InitialPanel;

public class InitialListener implements ActionListener {
	private InitialPanel ip;
	private LeftSideBar lsb;
	
	public InitialListener(InitialPanel ip,LeftSideBar lsb)
	{
		this.ip = ip;
		this.lsb = lsb;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!ip.getPlayerName().isEmpty() && ip.getPlayerName().matches("[a-zA-Z]+")){
			System.out.println(arg0.getActionCommand());
			System.out.println(ip.getRegister().getText());
		if(arg0.getActionCommand() == ip.getJoin().getText()){
			lsb.setPName(ip.getPlayerName());
			lsb.getCf().setName(ip.getName());
			lsb.setJoinPanel();
			ip.disableAll();

		}
		else if(arg0.getActionCommand() == ip.getCreate().getText()){
			lsb.setPName(ip.getPlayerName());
			lsb.setCreatePanel();
			ip.disableAll();
		}

		else if(arg0.getActionCommand() == ip.getLogin().getText()){
			lsb.setPName(ip.getPlayerName());
			lsb.setLoginPanel();
			ip.disableAll();
			
		}
		else if(arg0.getActionCommand() == ip.getRegister().getText()){
			lsb.setPName(ip.getPlayerName());
			lsb.setRegisterPanel();
			ip.disableAll();
			
		}
		else
			System.out.println("No button?!");
		}


	}

}
