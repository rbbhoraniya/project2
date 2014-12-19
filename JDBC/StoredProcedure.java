import java.io.*;
import java.sql.*;

class StoredProcedure {	
	public static void main(String[] args) {	
		String procedureName = "";
		String sq = "";
		try {
			procedureName = args[0];		
			sq = "create proc " + procedureName + " as select * from Student_Info";
			try 
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");			}
			catch(Exception e) { System.out.println (e.getMessage());   }
			
			Connection cn = DriverManager.getConnection("jdbc:odbc:StudentDB");
			
			Statement st = cn.createStatement();
			
			//st.executeUpdate(sq);
			
			//System.out.println ("\n " + procedureName + " Procedure created.");
			
			CallableStatement cs = cn.prepareCall("{call " + procedureName + "()}");
			ResultSet rs = cs.executeQuery();
			
			System.out.println ("\n {call " + procedureName + "()} result: ");
			
			while(rs.next())
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
			st.close();
			cn.close();
		}
		catch(Exception e) 
		{
			if ( procedureName.equals("") )
				System.out.println ("Exception: Please enter a Procedure Name.");
			e.printStackTrace();
		}
	
	}
}
