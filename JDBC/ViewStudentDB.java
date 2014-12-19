
// Fetch and display records from a table using field index
// Fetch and display records from a table using Result set metadata.

import java.io.*;
import java.sql.*;

class ViewStudentDB
{
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData rsmd;

public static void main(String[] args) 
{
	ViewStudentDB stuDB = new ViewStudentDB();
	stuDB.viewTableInfo("StudentDB","Student_Info");
}

public boolean viewTableInfo(String strDB, String strTable)
{
	try 
	{
		// Load and register the driver
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(Exception e) {e.printStackTrace();}
		
		// Establish the connection to the StudentDB database
		// Here we use the ODBC Microsoft Access Driver & Database
		cn = DriverManager.getConnection("jdbc:odbc:" + strDB);
		
		// Create a statement
		st = cn.createStatement();
		
		// Execute the statement
		rs = st.executeQuery("select * from " + strTable);
		
		// Create Meta Data object
		rsmd = rs.getMetaData();
		
		System.out.println ("Table name: " + rsmd.getTableName(1));
		System.out.println ("\nTable Structure: ");
		System.out.println ("Column No \t\t Column Name \t\t Precision \t Type");
		System.out.println ("--------- \t\t ----------- \t\t --------- \t -------");
		
		// Get Column Details
		for(int i=1;i<=rsmd.getColumnCount();i++)
		System.out.println("Column" + i + ": \t\t "
				+ rsmd.getColumnName(i) + " \t\t " 
				+ rsmd.getPrecision(i)  + " \t\t " 
				+ rsmd.getColumnTypeName(i) );
		
		System.out.println ();
		System.out.println ("\nTable Data: ");
		System.out.println ("Student ID \tName \t\tAge");
		System.out.println ("---------- \t----- \t\t----");
		// Retrieve the results
		while(rs.next())
		System.out.println( rs.getInt(1) 	+ "\t\t" +
							rs.getString(2)	+ "\t\t" +
							rs.getString(3) );
		
		// Close the statement and connection
		st.close();
		cn.close();
		return true;
	}
	catch(SQLException e) {
		System.out.println(e.getMessage());
		return false;
	}	
}
}

       
