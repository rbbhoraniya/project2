import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
// Chapter 11, Listing 7
public class TemperatureSensorServer 
	extends UnicastRemoteObject
		implements TemperatureSensor, Runnable
{
	private volatile double temp;
	private Vector list = new Vector();
	
	public TemperatureSensorServer() throws java.rmi.RemoteException{
		// Assign a default setting for the temperature
		temp = 98.0;
	}
	public double getTemperature() throws java.rmi.RemoteException {
		return temp;
	}
	public void addTemperatureListener ( TemperatureListener listener )
		throws java.rmi.RemoteException
	{
		System.out.println ("adding listener -" + listener);
		list.add (listener);
	}
	public void removeTemperatureListener ( TemperatureListener	listener )
		throws java.rmi.RemoteException
	{
		System.out.println ("removing listener -" + listener);
		list.remove (listener);
	} 
	public void run() {
		Random r = new Random();
		for (;;)
		{
			try
			{
				// Sleep for a random amount of time
				int duration = r.nextInt() % 10000 + 2000;
				// Check to see if negative, if so, reverse
				if (duration < 0) duration = duration * -1;
				Thread.sleep(duration);
			}
			catch (InterruptedException ie) 
			{ System.out.println (ie.getMessage());}
			// Get a number, to see if temp goes up or down
			int num = r.nextInt();
			if (num < 0)
				temp += 0.5;
			else
				temp -= 0.5;
			// Notify registered listeners
			notifyListeners();
		}
	}
	private void notifyListeners()
	{
		// Notify every listener in the registered list
		for (Enumeration e = list.elements(); e.hasMoreElements(); )
		{
			TemperatureListener listener = (TemperatureListener)
			e.nextElement();
			// Notify, if possible a listener
			try{
				listener.temperatureChanged (temp);
			}catch (RemoteException re)	{
				System.out.println ("removing listener -" +	listener);
				// Remove the listener
				list.remove( listener );
			}
		}
	}
	public static void main(String args[])
	{
		System.out.println ("Loading temperature service");
		// Only required for dynamic class loading
		//System.setSecurityManager ( new RMISecurityManager() );
		try	{
			// Load the service
			TemperatureSensorServer sensor = new
			TemperatureSensorServer();
			// Check to see if a registry was specified
			String registry = "localhost";
			if (args.length >=1)
			{
			registry = args[0];
			}
			// Registration format //registry_hostname:port/service
			// Note the :port field is optional
			String registration = "rmi://" + registry +
			"/TemperatureSensor";
			// Register with service so that clients can
			// find us
			Naming.rebind( registration, sensor );
			// Create a thread, and pass the sensor server.
			// This will activate the run() method, and
			// trigger regular temperature changes.
			Thread thread = new Thread (sensor);
			thread.start();
		}catch (RemoteException re) {
			System.err.println ("Remote Error - " + re);
		}catch (Exception e) {
			System.err.println ("Error - " + e);
		}
	}
}