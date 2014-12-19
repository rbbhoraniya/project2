
// using prepared statement and execute update
// using commit and set auto commit

import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

class InsertRecord 
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
		
		strStuData = JOptionPane.showInputDialog(null,"Enter Student ID:", "Insert Student", 1);
		
		stu_id =  ( strStuData==null ? -1 : Integer.parseInt(strStuData) );	
		if (stu_id < 0)
		{
			strMessage = "Invalid Student ID.";
			throw new Exception(strMessage);
		}	
		stu_name = JOptionPane.showInputDialog(null,"Enter Student Name:", "Insert Student", 1);
		if (stu_name == null)
		{
			strMessage = "Invalid Student Name.";
			throw new Exception(strMessage);
		}	
		strStuData = null;
		strStuData = JOptionPane.showInputDialog(null,"Enter Student Age:", "Insert Student", 1);
		stu_age =  ( strStuData==null ? -1 : Integer.parseInt(strStuData) );	
		if (stu_age <= 0)
		{
			strMessage = "Invalid Student Age.";
			throw new Exception(strMessage);
		}		
		con = DriverManager.getConnection("jdbc:odbc:studentDB");
		con.setAutoCommit(false);
		if (con.isReadOnly())
		{
			strMessage = "Cannnot write on Connection, it is read only...";
			throw new Exception(strMessage);
		}	
		q = "insert into student_info(Stu_ID,Stu_Name,Stu_Age) values (?,?,?)";
						//"values (" + stu_id + ",'" + stu_name + "'," + stu_age + ")";
						
		pst = con.prepareStatement(q);
	    pst.setInt(1, stu_id);
	    pst.setString(2, stu_name);
	    pst.setInt(3, stu_age);
		
		System.out.println("\n\nRecord inserted Successfully : " + pst.executeUpdate() + " \n\n");
		//con.close();
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
