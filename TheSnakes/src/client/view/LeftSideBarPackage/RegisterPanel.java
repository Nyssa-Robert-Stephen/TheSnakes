package client.view.LeftSideBarPackage;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.RegisterListener;
import client.view.ClientFrame;

public class RegisterPanel extends JPanel{
	private ClientFrame cf;
	JLabel lbl_fname = new JLabel("Enter First Name: ");
	JLabel lbl_lname = new JLabel("Enter Last Name:");
	JLabel lbl_address = new JLabel("Enter address: ");
	JLabel lbl_phoneno = new JLabel("Enter Phone no:");
	JLabel lbl_uname = new JLabel("Enter UserName: ");
	JLabel lbl_pwd = new JLabel("Enter Password:");
	
	JTextField txt_fname = new JTextField(20);
	JTextField txt_lname = new JTextField(20);
	JTextField txt_address = new JTextField(20);
	JTextField txt_phoneno = new JTextField(20);
	JTextField txt_uname = new JTextField(20);
	JTextField txt_pwd = new JTextField(20);
	
	JButton btn_ok = new JButton("Submit");
	JButton btn_cancel = new JButton("Cancel");
	
	public RegisterPanel(ClientFrame cf){
		this.cf = cf;
		this.add(lbl_fname);
		this.add(txt_fname);
		this.add(lbl_lname);
		this.add(txt_lname);
		this.add(lbl_address);
		this.add(txt_address);
		this.add(lbl_phoneno);
		this.add(txt_phoneno);
		this.add(lbl_uname);
		this.add(txt_uname);
		this.add(lbl_pwd);
		this.add(txt_pwd);
		this.add(btn_ok);
		this.add(btn_cancel);
		btn_ok.addActionListener(new RegisterListener(this));
		this.setLayout(new GridLayout(5,2));
		
	}
	public JButton getBtn_ok(){
		return btn_ok;
	}
	public ClientFrame getCf() {
		return cf;
	}

	public JTextField getTxt_fname() {
		return txt_fname;
	}

	public JTextField getTxt_lname() {
		return txt_lname;
	}

	public JTextField getTxt_address() {
		return txt_address;
	}

	public JTextField getTxt_phoneno() {
		return txt_phoneno;
	}

	public JTextField getTxt_uname() {
		return txt_uname;
	}

	public JTextField getTxt_pwd() {
		return txt_pwd;
	}
	
}
