import java.sql.*;
import java.io.*;

class ScrollableRecordset
{
	public static void main(String[] args) throws SQLException,ClassNotFoundException
	{
		System.out.println("\n\tPROGRAM TO DISPLAY RECORDS OF DATABASE USING SCROLLABLE RECORDSET.\n");
		String query = "SELECT Stu_ID,Stu_Name,Stu_Age FROM Student_Info";
		//String query1 = "INSERT INTO Table1 values (7,'PQR',32000)";
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection("jdbc:odbc:StudentDB");
			System.out.println("Database Connected");
			
		Statement smnt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet res = smnt.executeQuery(query);
		res.last();
		int rows = res.getRow();
		System.out.println("Number of rows retrieved = "+rows + "\n");
		ResultSetMetaData meta = res.getMetaData();
		int columns = meta.getColumnCount();
		res.beforeFirst();

		for(int i=1;i<=columns;i++)
		{
			System.out.printf("%-10s",meta.getColumnName(i));
		}
		System.out.println();
		while (res.next())
		{
			for(int i=1;i<=columns;i++)
			{
				System.out.printf("%-10s",res.getString(i));
			}
			System.out.println();
		}	
	}

}
