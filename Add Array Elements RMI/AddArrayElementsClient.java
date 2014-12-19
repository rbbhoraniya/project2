import java.rmi.*;
import java.lang.*;

public class AddArrayElementsClient 
{

	public static void main(String args[]) 
	{

		try 
		{
			String addServerURL = "rmi://" + args[0] + "/AddServer";
			AddArrayElementsInterface addServerIntf = (AddArrayElementsInterface)Naming.lookup(addServerURL);
			int n=Integer.parseInt(args[1]);
			String[] arr=new String[n];
			for(int i=0;i<n;i++)
			{
				arr[i] = args[i+2];
			}			
			System.out.println("The sum is: " + addServerIntf.addArrayElements(arr));
			}
		catch(Exception e) 
		{
			System.out.println("Exception: " + e);
			System.out.println("\n Syntax usage example: " +
								"\n\t java AddArrayElementsClient localhost  3  12  9  31 \n");
		}
	}
}
