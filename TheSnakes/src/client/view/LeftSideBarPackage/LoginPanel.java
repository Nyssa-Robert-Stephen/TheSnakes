package client.view.LeftSideBarPackage;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.LoginListener;
import client.view.ClientFrame;

public class LoginPanel extends JPanel{
	ClientFrame cf;
	private JLabel lbl_uname = new JLabel("Enter Username: ");
	private JLabel lbl_pwd = new JLabel("Enter Password: ");
	
	private JTextField txt_uname = new JTextField(20);
	private JTextField txt_pwd = new JTextField(20);
	
	private JButton btn_ok = new JButton("OK");
	private JButton btn_cancel = new JButton("Cancel");
	
	
	public LoginPanel(ClientFrame cf){
		this.cf = cf;
		this.add(lbl_uname);
		this.add(txt_uname);
		this.add(lbl_pwd);
		this.add(txt_pwd);
		this.add(btn_ok);
		this.add(btn_cancel);
		btn_ok.addActionListener(new LoginListener(this));
		
		this.setLayout(new GridLayout(5,4));
		
		
	}


	public JTextField getTxt_uname() {
		return txt_uname;
	}


	public JTextField getTxt_pwd() {
		return txt_pwd;
	}


	public ClientFrame getCf() {
		return cf;
	}


	public JButton getBtn_ok() {
		return btn_ok;
	}
	
}
