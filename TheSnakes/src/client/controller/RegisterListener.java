package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.LeftSideBarPackage.RegisterPanel;

public class RegisterListener implements ActionListener {

	RegisterPanel rp;
	public RegisterListener(RegisterPanel registerPanel) {
		this.rp = registerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//ensure all register stuff is filled
		if(true){
			System.out.println("Register");
			if(rp.getCf().getSnakeGame().Register())
				System.out.println("Success!");
		}
	}

}
