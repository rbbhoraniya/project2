// Display database properties using Database metadata

import java.io.*;
import java.sql.*;

class ViewDatabaseMetadata 
{
public static void main(String[] args) throws Exception 
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	viewMetaData("studentDB");
	System.out.println ("\n -------------------------------------------------\n");
	viewMetaData("salesDB");
}

public static void viewMetaData(String strDB) throws Exception
{
	Connection cn = DriverManager.getConnection("jdbc:odbc:" + strDB);
	
	DatabaseMetaData dbmd = cn.getMetaData();
	
	System.out.println(" Database name: " + strDB);
	System.out.println(" Database Product Name: "+ dbmd.getDatabaseProductName());
	System.out.println(" Database Product Version: "+ dbmd.getDatabaseProductVersion());
	System.out.println();
	System.out.println(" URL : " + dbmd.getURL()) ;
	System.out.println(" User : " + dbmd.getUserName());
	System.out.println(" Driver : " + dbmd.getDriverName());
	System.out.println(" Driver Version : " + dbmd.getDriverVersion());
	System.out.println();
	System.out.println(" ANSI Entry Level : " + dbmd.supportsANSI92EntryLevelSQL());
	System.out.println(" ANSI Full Level : "+dbmd.supportsANSI92FullSQL());
	System.out.println(" Union : " + dbmd.supportsUnion());
	System.out.println(" Subqueries in INs : " + dbmd.supportsSubqueriesInIns());
	System.out.println(" Stored Procedures : " + dbmd.supportsStoredProcedures());
	System.out.println();
	System.out.println(" No. of connections : " + dbmd.getMaxConnections());
	System.out.println(" ColumnName Max Length : "+dbmd.getMaxColumnNameLength());
	System.out.println(" No. of columns in table : "+dbmd.getMaxColumnsInTable());
	cn.close(); 
}
}


