package client.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import client.view.LeftSideBarPackage.CreatePanel;
import client.view.LeftSideBarPackage.InitialPanel;
import client.view.LeftSideBarPackage.JoinPanel;
import client.view.LeftSideBarPackage.LoginPanel;
import client.view.LeftSideBarPackage.RegisterPanel;

public class LeftSideBar extends JPanel{
	private ClientFrame cf;
	private InitialPanel ip = new InitialPanel(this);
	private CreatePanel cp;
	private JoinPanel jp; 
	private RegisterPanel rp;
	private LoginPanel lp;
	
	private String p_name;
	private int dimensions;
	private int players;
	private int host_port;
	private int join_port;
	private String ip_address;
	private int position;
	
		public LeftSideBar(ClientFrame clientFrame)
		{
			this.cf = clientFrame;
			jp = new JoinPanel(cf);
			cp = new CreatePanel(cf);
			rp = new RegisterPanel(cf);
			lp = new LoginPanel(cf);

			this.add(ip);
			this.add(cp);
			this.add(jp);
			this.add(lp);
			this.add(rp);
			this.setLayout(new GridLayout(5,1));
			
			cp.setVisible(false);
			jp.setVisible(false);
			lp.setVisible(false);
			rp.setVisible(false);
			this.setSize(30, 300);
		}
		public void setJoinPanel()
		{
			jp.setVisible(true);
		}
		
		public void setCreatePanel()
		{
			cp.setVisible(true);
		}
		public void setLoginPanel() {
			lp.setVisible(true);
			
		}
		public void setRegisterPanel(){
			rp.setVisible(true);
		}
		
		public InitialPanel getIp() {
			// TODO Auto-generated method stub
			return ip;
		}
		public CreatePanel getCp() {
			// TODO Auto-generated method stub
			return cp;
		}
		public JoinPanel getJp(){
			return jp;
		}
		public String getPName() {
			return p_name;
		}
		public void setPName(String name) {
			this.p_name = name;
		}
		public int getDimensions() {
			return dimensions;
		}
		public void setDimensions(int dimensions) {
			this.dimensions = dimensions;
		}
		public int getPlayers() {
			return players;
		}
		public void setPlayers(int players) {
			this.players = players;
		}
		public int getHostPort() {
			return host_port;
		}
		public void setHostPort(int port) {
			this.host_port = port;
		}
		public int getJoinPort() {
			return join_port;
		}
		public void setJoinPort(int port) {
			this.join_port = port;
		}
		public String getIp_address() {
			return ip_address;
		}
		public void setIp_address(String ip_address) {
			this.ip_address = ip_address;
		}
		public int getPosition() {
			return position;
		}
		public void setPosition(int object) {
			this.position = object;
		}



}
