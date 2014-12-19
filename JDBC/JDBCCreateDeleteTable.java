// JDBC Programming showing Creation and Deletion of Tables

import java.sql.*;
import javax.swing.JOptionPane;

public class JDBCCreateDeleteTable
{
	static String userid="root", password = "root";
	static String url = "jdbc:mysql://localhost/";
	static Statement stmt = null;
	static Connection con = null;
	static String strDB = null;

	public static void main(String args[])
	{
		JOptionPane.showMessageDialog(null,"JDBC Programming showing Creation and Deletion of Tables");
		int mainChoice = -1;
		int subChoice = -1;	
		
		strDB = JOptionPane.showInputDialog(null,"Enter Database name:", "Database", 1);
		if (strDB == null)
		{
			JOptionPane.showMessageDialog(null,"Exiting application");
			System.exit(0);
		}
		do
		{
			try
			{
				mainChoice = getChoice();
				switch (mainChoice)
				{
					case 1:
						subChoice = getTableCreationChoice();
						createSelectedTable(subChoice);
						break;
					case 2:
						subChoice = getTableDeletionChoice();
						deleteSelectedTable(subChoice);
						break;
					case 0:
						JOptionPane.showMessageDialog(null,"Exiting application");
						System.exit(0);
					default:
						 JOptionPane.showMessageDialog(null,"Enter proper choice.");						
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Exception: " + ex.getMessage(), "Exiting application", 1);
				System.exit(0);
			}
		}
		while ( mainChoice !=  0);
	}


	public static int getChoice()
	{
		String choice;
		int ch;
		choice = JOptionPane.showInputDialog(null,
			"1. Create Tables\n"+
			"2. Delete Tables\n"+
			"0. Exit\n\n"+
			"Enter your choice");
		if (choice != null)
			ch = Integer.parseInt(choice);
		else
			ch = 0;
		return ch;
	}



	public static int getTableCreationChoice()
	{
		String choice;
		int ch;
		choice = JOptionPane.showInputDialog(null,
			"1. Create Employees Table\n"+
			"2. Create Products Table\n"+
			"0. Exit\n\n"+
			"Enter your choice");
		if (choice != null)
			ch = Integer.parseInt(choice);
		else
			ch = 0;
		return ch;
	}



	public static int getTableDeletionChoice()
	{
		String choice;
		int ch;
		choice = JOptionPane.showInputDialog(null,
			"1. Delete Employees Table\n"+
			"2. Delete Products Table\n"+
			"0. Exit \n\n"+
			"Enter your choice");
		if (choice != null)
			ch = Integer.parseInt(choice);
		else
			ch = 0;
		return ch;
	}

	public static void createSelectedTable(int subChoice)
	{
		String createString;		
		boolean result = false;
	
		if(subChoice==1)
		{					
			createString = "create table Employees (" +
								"Employee_ID INTEGER, " +
								"Name VARCHAR(30))";
			result = executeStatement(createString);
		}
		if(subChoice==2)
		{
			createString = "create table Orders (" +
					"Prod_ID INTEGER, " +
					"ProductName VARCHAR(20), "+
					"Employee_ID INTEGER )";
			result = executeStatement(createString);			
		}
		if (result)
			JOptionPane.showMessageDialog(null,"Table Created Successfully.");
	}


	public static void deleteSelectedTable(int subChoice)
	{
		String deleteString;		
		boolean result = false;
	
		if(subChoice==1)
		{					
			deleteString = "drop table Employees;";
			result = executeStatement(deleteString);
		}
		if(subChoice==2)
		{
			deleteString = "drop table Orders;";
			result = executeStatement(deleteString);			
		}
		if (result)
			JOptionPane.showMessageDialog(null,"Table Deleted Successfully.");
	}


	public static Connection getConnection(String strDB)
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch(java.lang.ClassNotFoundException e) 
		{
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
	
		try 
		{
			con = DriverManager.getConnection( url + strDB, userid, password);
		} 
		catch(SQLException ex) 
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		
		return con;
	}


	public static boolean executeStatement(String createString)
	{
		Connection con = getConnection(strDB);
	
		try {
			stmt = con.createStatement();
	   		stmt.executeUpdate(createString);
			stmt.close();
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"SQLException: " + ex.getMessage());
			return false;
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Exception: " + ex.getMessage());
			return false;
		}
		return true;
	}	
}//End of class