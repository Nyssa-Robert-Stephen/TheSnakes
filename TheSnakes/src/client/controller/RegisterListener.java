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
			if(rp.getTxt_address().getText() != null && rp.getTxt_fname().getText() != null &&
					rp.getTxt_lname().getText() != null	&& rp.getTxt_uname().getText() != null 
					&& rp.getTxt_pwd().getText() != null && rp.getTxt_phoneno().getText() != null)
				
			if(rp.getCf().getSnakeGame().Register(rp.getTxt_uname().getText(),rp.getTxt_pwd().getText()
					,rp.getTxt_fname().getText(),rp.getTxt_lname().getText()
					,rp.getTxt_address().getText(),rp.getTxt_phoneno().getText())){
				System.out.println("Success!");
				rp.getCf().getLSB().getIp().getLogin().setEnabled(false);
				rp.getCf().getLSB().getIp().getRegister().setEnabled(false);
			}
		}
	}

}
