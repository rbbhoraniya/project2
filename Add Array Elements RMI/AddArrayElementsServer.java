import java.net.*;
import java.rmi.*;

public class AddArrayElementsServer 
{
	public static void main(String args[]) 
	{
		try 
		{
			AddArrayElementsServerImpl addServerImpl = new AddArrayElementsServerImpl();
			Naming.rebind("AddServer", addServerImpl);
		}
		catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}