package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.LeftSideBarPackage.JoinPanel;

public class JoinListener implements ActionListener{
	private JoinPanel jp;
	
	public JoinListener(JoinPanel jp) {
		this.jp = jp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			if(!jp.getIp().getText().isEmpty()){
		jp.getClientFrame().getLSB().setJoinPort(Integer.parseInt(jp.getCbx_port().getSelectedItem().toString()));
		jp.getClientFrame().getLSB().setPosition(Integer.parseInt(jp.getCbx_pos().getSelectedItem().toString()));
		jp.getClientFrame().getLSB().setIp_address(jp.getIp().getText());
		
		if(jp.getClientFrame().getSnakeGame().JoinServer(jp.getClientFrame().getLSB().getPName(),jp.getClientFrame().getLSB().getPosition(),jp.getIp().getText().toString())){
		jp.disableAll();
		jp.getClientFrame().getSp().setLbl_curPlayer(jp.getClientFrame().getLSB().getPName());
		
		jp.getButton().addKeyListener(new MoveListener(jp.getClientFrame()));
		}
			}
	}

}
