import java.rmi.*;

public interface AdditionInterface extends Remote 
{
	double add(double d1, double d2) throws RemoteException;
}