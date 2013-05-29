package server.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
	
	private Connection connection;
	
	public boolean openDatabase()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/snakes";
			
			connection = DriverManager.getConnection(url, "root", "");
			
			return true;
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			return false;
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean closeDatabase()
	{
		try {
			connection.close();
			
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkDatabase(String username, String password)
	{
		try {
			Statement statement = connection.createStatement();
			
			String query = "SELECT * FROM players WHERE name = '" + username + "';";
			
			ResultSet rs = statement.executeQuery(query);
			
			//If username is found
			if(rs.next())
			{
				//Check password
				if(rs.getString(2).compareTo(password)==0)
				{
					System.out.println("Your password is correct!");
					return true;
				}
				//password is incorrect
				else
				{
					System.out.println("Your password is incorrect!");
					return false;
				}
			}
			//else return false;
			else
			{
				System.out.println("Username couldn't be found!");
				return false;
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean inputIntoDatabase(String username, String password, String firstname,
			String lastname, String address, String ph_number)
	{
		try {			
			PreparedStatement pst = connection.prepareStatement("INSERT INTO players(NAME,PASSWORD) values(?,?)");	
			
			pst.setString(1, username);
			pst.setString(2, password);
			
			pst.execute();
			
			return true;
		}
		catch(SQLException e)
		{
			//e.printStackTrace();
			return false;
		}
	}
}
