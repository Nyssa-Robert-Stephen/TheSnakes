package client.view.LeftSideBarPackage;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.controller.CreateListener;
import client.view.ClientFrame;

public class CreatePanel extends JPanel implements shared.controller.SnakeGameInterface{
	private JLabel lbl_dimensions = new JLabel("Size of grid?");
	private JComboBox cbx_dimensions = new JComboBox(GRID_SIZES);
	private JLabel lbl_port = new JLabel("Select Port: ");
	private JComboBox cbx_ports = new JComboBox(PORTS);
	private JLabel lbl_start = new JLabel("Start? ");
	private JButton btn_start = new JButton("START");
	private JLabel lbl_players = new JLabel("Players? ");
	private String players[] = {"2","3","4"};
	private JComboBox cbx_players = new JComboBox(players);
	private ClientFrame cf;
	
	public CreatePanel(ClientFrame cf)
	{
		this.cf = cf;
		this.add(lbl_dimensions);
		this.add(cbx_dimensions);
		this.add(lbl_port);
		this.add(cbx_ports);
		this.add(lbl_players);
		this.add(cbx_players);
		this.add(lbl_start);
		this.add(btn_start);
		btn_start.addActionListener(new CreateListener(this));

		this.setLayout(new GridLayout(5,2));
	}

	public int getPlayers() {
		return Integer.parseInt(cbx_players.getSelectedItem().toString());
		
	}
	
	public int getDimensions(){
		return Integer.parseInt(cbx_dimensions.getSelectedItem().toString());
	}

	public ClientFrame getClientFrame() {
		// TODO Auto-generated method stub
		return cf;
	}

	public JComboBox getCbx_dimensions() {
		return cbx_dimensions;
	}

	public JComboBox getCbx_ports() {
		return cbx_ports;
	}

	public JComboBox getCbx_players() {
		return cbx_players;
	}

	public void setDisabled() {
		cbx_players.setEnabled(false);
		cbx_dimensions.setEnabled(false);
		cbx_ports.setEnabled(false);
		btn_start.setEnabled(false);
		
	}

}
