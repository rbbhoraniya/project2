import java.rmi.*;
import java.rmi.server.*;

public class AdditionServerImpl 
		extends UnicastRemoteObject 
			implements AdditionInterface 
{
	public AdditionServerImpl() throws RemoteException 
	{
	}
	
	public double add(double d1,double d2) throws RemoteException 
	{	
		System.out.println("The first number is: " + d1);
		System.out.println("The second number is: " + d2);
		return d1+d2;
	}
}