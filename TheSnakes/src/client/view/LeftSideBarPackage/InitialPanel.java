package client.view.LeftSideBarPackage;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.InitialListener;
import client.view.LeftSideBar;


public class InitialPanel extends JPanel{
	
	private LeftSideBar lsb;
	
	private JLabel lbl_name = new JLabel("Enter Name(no spaces!):");
	private JLabel lbl_join = new JLabel("Join a server:");
	private JLabel lbl_create = new JLabel("Create a server:");
	private JTextField name = new JTextField(20);
	private JButton btn_login = new JButton("Login");
	private JButton btn_register = new JButton("Register");
	private JButton btn_join = new JButton("Join");
	private JButton btn_create= new JButton("Create");
	
	public InitialPanel(LeftSideBar lsb){
		this.lsb = lsb;
		this.add(lbl_name);
		this.add(name);

		///btn_login.setEnabled(false);
		//btn_register.setEnabled(false);
		this.add(lbl_create);
		this.add(btn_create);
		this.add(lbl_join);
		this.add(btn_join);
		this.add(btn_login);
		this.add(btn_register);
		btn_login.addActionListener(new InitialListener(this,lsb));
		btn_register.addActionListener(new InitialListener(this,lsb));
		btn_join.addActionListener(new InitialListener(this,lsb));
		btn_create.addActionListener(new InitialListener(this,lsb));
		this.setLayout(new GridLayout(5,2));
		
	}
	public JButton getJoin() {
		// TODO Auto-generated method stub
		return btn_join;
	}
	
	public JButton getCreate() {
		// TODO Auto-generated method stub
		return btn_create;
	}
	
	public JButton getLogin() {
		// TODO Auto-generated method stub
		return btn_login;
	}
	public JButton getRegister() {
		// TODO Auto-generated method stub
		return btn_register;
	}

	public void disableAll(){
		btn_join.setEnabled(false);
		btn_create.setEnabled(false);
		name.setEnabled(false);
		btn_register.setEnabled(false);
		btn_login.setEnabled(false);
	}
	
	public void enableJoin(){
		btn_join.setEnabled(true);
	}
	public String getPlayerName() {
		return name.getText();
	}
	
}
