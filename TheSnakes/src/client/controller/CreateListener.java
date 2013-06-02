package client.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

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
		Point p = new Point();
		p.x = Integer.parseInt(cp.getCbx_dimensions().getSelectedItem().toString());
		p.y = Integer.parseInt(cp.getCbx_dimensions().getSelectedItem().toString());
		cp.setDisabled();
		cp.getClientFrame().getLSB().getJp().setVisible(true);
		try {
			try {
				cp.getClientFrame().getSnakeGame().CreateServer(cp.getPlayers(),p, Integer.parseInt(cp.getCbx_ports().getSelectedItem().toString()));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (AlreadyBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cp.getClientFrame().setCreate(true);

		//cb.getClientFrame().
	}

}
