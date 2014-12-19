
// Fetch and display records from a table using field index
// Fetch and display records from a table using Result set metadata.

import java.io.*;
import java.sql.*;

class ViewSalesPeople 
{
	public static void main(String[] args) 
	{
		try 
		{			
			// Load and register the driver
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			}
			catch(Exception e) {e.printStackTrace();}
			
			// Establish the connection to the database server
			Connection cn = DriverManager.getConnection("jdbc:odbc:SalesDB");
			
			// Create a statement
			Statement st = cn.createStatement();
			
			ResultSet rs;
			ResultSetMetaData rsmd;
						
			// Execute the statement           
			rs = st.executeQuery("select * from SALESPEOPLE");
			// Create Meta Data object
			rsmd = rs.getMetaData();
			
           System.out.println ("Column No \t\t Column Name \t Precision \t Type");
           System.out.println ("--------- \t\t ----------- \t --------- \t -------");
           // Get Column Details
           for(int i=1;i<=rsmd.getColumnCount();i++)
	           System.out.println("Column"+i+": \t\t "+rsmd.getColumnName(i)+" \t\t "+rsmd.getPrecision(i)+" \t\t "+rsmd.getColumnTypeName(i));

			System.out.println ();
			System.out.println (rsmd.getTableName(1) + " : " );
			System.out.println ("--------\t--------\t--------\t------");
			System.out.println( rsmd.getColumnName(1) + "\t\t" + 
								rsmd.getColumnName(2) + "\t\t" + 
								rsmd.getColumnName(3) + "\t\t" + 
								rsmd.getColumnName(4) );
			System.out.println ("--------\t--------\t--------\t------");
			// Retrieve the results
			while(rs.next())
			{
				System.out.println( rs.getInt(1) 	+ "\t\t" + 
									rs.getString(2)	+ "\t\t" + 
									rs.getString(3)	+ "\t\t" + 
									rs.getFloat(4) );
			}			
			// Close the statement and connection
			st.close();
			cn.close();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}


