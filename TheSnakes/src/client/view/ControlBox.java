package client.view;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlBox extends JPanel{
	private ClientFrame tc;
	private JLabel lbl1 = new JLabel("Enter Name:");
	private JTextField name = new JTextField(20);
	private JLabel lbl3 = new JLabel("Players");
	private String players[] = {"2","3","4"};
	private JComboBox dbx = new JComboBox(players);



	public ControlBox(ClientFrame theClient){
		this.tc = theClient;
		this.setLayout(new FlowLayout());
		this.add(lbl1);
		this.add(name);
		this.add(lbl3);
		this.add(dbx);
		
		
	}

		public String getPlayerName(){
			return name.getText();
		}
		public String getPlayers(){
			return dbx.getSelectedItem().toString();
		}
}
