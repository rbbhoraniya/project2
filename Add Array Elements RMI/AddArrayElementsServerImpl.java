import java.rmi.*;
import java.rmi.server.*;

public class AddArrayElementsServerImpl 
	extends UnicastRemoteObject 
		implements AddArrayElementsInterface 
{
	public AddArrayElementsServerImpl() throws RemoteException 
	{
	}
	
	public int addArrayElements(String[] arr) throws RemoteException 
	{
		int sum=0;
		for(int i=0;i<arr.length;i++)
		{
			int d = Integer.parseInt(arr[i]);
			sum=sum+d;
		}
		return sum;
	}
}