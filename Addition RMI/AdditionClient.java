import java.rmi.*;

public class AdditionClient 
{
	public static void main(String args[]) 
	{
		try 
		{
			String addServerURL = "rmi://" + args[0] + "/AddServer";
			AdditionInterface addServerIntf = (AdditionInterface)Naming.lookup(addServerURL);
			
			System.out.println("The first number is: " + args[1]);
			double d1 = Double.valueOf(args[1]).doubleValue();
			
			System.out.println("The second number is: " + args[2]);
			double d2 = Double.valueOf(args[2]).doubleValue();
			
			System.out.println("The sum is: " + addServerIntf.add(d1, d2));
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException : " + e.getMessage());
			System.out.println ("Please enter two numbers for addition.");
			System.out.println ("Usage Syntax example: \t java AdditionClient localhost 1 4");
		}
		catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}

