
// using prepared statement and execute update
// using commit and set auto commit

import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

class DeleteRecord 
{
static String strDB;

public static void main(String[] args) 
{
	int stu_id, stu_age;
	String strStuData;
	String stu_name;
	String strMessage;
	Connection con;
	PreparedStatement pst;
	String q;
	ViewStudentDB stuDB;
	
	stuDB = new ViewStudentDB();
	stuDB.viewTableInfo("StudentDB","Student_Info");
	
	try 
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		strMessage = "";		
		strStuData = null;
		
		stu_name = JOptionPane.showInputDialog(null,"Enter Student Name to be deleted:", "Delete Student", 1);
		if (stu_name == null)
		{
			strMessage = "Invalid Student Name.";
			throw new Exception(strMessage);
		}	
		con = DriverManager.getConnection("jdbc:odbc:studentDB");
		con.setAutoCommit(false);
		if (con.isReadOnly())
		{
			strMessage = "Cannnot write on Connection, it is read only...";
			throw new Exception(strMessage);
		}	
		q = "delete from student_info where Stu_Name = ?";
						
		pst = con.prepareStatement(q);
	    pst.setString(1, stu_name);
		
		System.out.println("\n\nRecord deleted Succefully : " + pst.executeUpdate() + " \n\n");
		con.commit();
	}
	catch(Exception e) 
	{ 
		System.out.println(e.getMessage()); 
		System.exit(0);
	}
	stuDB.viewTableInfo("StudentDB","Student_Info");
}
}
