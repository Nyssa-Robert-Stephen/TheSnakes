package client.view.LeftSideBarPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.JoinListener;
import client.controller.MoveListener;
import client.model.SnakeGame;
import client.view.ClientFrame;

public class JoinPanel extends JPanel implements shared.controller.SnakeGameInterface{
	private JLabel lbl_ip = new JLabel("Enter IP: ");
	private JLabel lbl_port = new JLabel("Select Port: ");
	private JTextField text_ip = new JTextField(10);
	private JComboBox cbx_port = new JComboBox(PORTS);
	private JLabel lbl_join = new JLabel("Attempt to join: ");
	private JButton join = new JButton("Join");
	private JLabel lbl_pos = new JLabel("Pick a position:");
	private JComboBox cbx_pos = new JComboBox(POSITIONS);
	private ClientFrame cf;
	public JoinPanel(ClientFrame cf2)
	{
		this.cf = cf2;
		this.setLayout(new FlowLayout());
		text_ip.setText("localhost");
		this.add(lbl_ip);
		this.add(text_ip);
		this.add(lbl_port);
		this.add(cbx_port);
		this.add(lbl_pos);
		this.add(cbx_pos);
		this.add(lbl_join);
		this.add(join);
		join.addActionListener(new JoinListener(this));
		join.addKeyListener(new MoveListener(cf2));
		this.setLayout(new GridLayout(5,2));
	}
	public JTextField getIp() {
		return text_ip;
	}
	public JComboBox getCbx_port() {
		return cbx_port;
	}
	public JComboBox getCbx_pos() {
		return cbx_pos;
	}
	public ClientFrame getClientFrame() {
		// TODO Auto-generated method stub
		return cf;
	}
	public void disableAll() {
		text_ip.setEnabled(false);
		cbx_port.setEnabled(false);
		cbx_pos.setEnabled(false);
		
	}
	
	
}
