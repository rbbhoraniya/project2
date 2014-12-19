import java.net.*;
import java.rmi.*;

public class AdditionServer
{
	public static void main(String args[]) 
	{
		try 
		{
			AdditionInterface addServerImpl = new AdditionServerImpl();
			Naming.rebind("AddServer", addServerImpl);
		}
		catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}