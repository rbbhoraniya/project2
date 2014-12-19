import java.rmi.*;

public interface AddArrayElementsInterface extends Remote 
{
	int addArrayElements(String[] arr) throws RemoteException;
}