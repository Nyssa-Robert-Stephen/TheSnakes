package client.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.LeftSideBarPackage.CreatePanel;

public class CreateListener implements ActionListener {
	private CreatePanel cp;
	
	public CreateListener(CreatePanel cp) {
		this.cp = cp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cp.getClientFrame().getLSB().setPlayers(cp.getPlayers());
		cp.getClientFrame().getLSB().setDimensions(Integer.parseInt(cp.getCbx_dimensions().getSelectedItem().toString()));
		cp.getClientFrame().getLSB().setHostPort(Integer.parseInt(cp.getCbx_ports().getSelectedItem().toString()));

		cp.setDisabled();
		cp.getClientFrame().getLSB().getJp().setVisible(true);
		cp.getClientFrame().getSnakeGame().CreateServer(4, new Point(50,50), 11985);
		cp.getClientFrame().setCreate(true);

		//cb.getClientFrame().
	}

}
