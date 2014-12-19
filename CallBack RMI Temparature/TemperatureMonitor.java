import java.rmi.*;
import java.rmi.server.*;
// Chapter 11, Listing 5
public class TemperatureMonitor 
	extends UnicastRemoteObject
		implements TemperatureListener
{
	// Default constructor throws a RemoteException
	public TemperatureMonitor() throws RemoteException
	{	/* no code req'd */	}
	public static void main(String args[])
	{
		System.out.println ("Looking for temperature sensor");
		// Only required for dynamic class loading
		//System.setSecurityManager(new RMISecurityManager());
		try
		{
			// Check to see if a registry was specified
			String registry = "localhost";
			if (args.length >=1)
			{
				registry = args[0];
			}
			// Registration format
			//registry_hostname :port/service
			// Note the :port field is optional
			String registration = "rmi://" + registry +	"/TemperatureSensor";
			// Lookup the service in the registry, and obtain
			// a remote service
			Remote remoteService = Naming.lookup ( registration );
			// Cast to a TemperatureSensor interface
			TemperatureSensor sensor = (TemperatureSensor)
			remoteService;
			// Get and display current temperature
			double reading = sensor.getTemperature();
			System.out.println ("Original temp : " + reading);
			// Create a new monitor and register it as a
			// listener with remote sensor
			TemperatureMonitor monitor = new TemperatureMonitor();
			sensor.addTemperatureListener(monitor);
		}catch (NotBoundException nbe) {
			System.out.println ("No sensors available");
		}catch (RemoteException re) {
			System.out.println ("RMI Error - " + re);
		}catch (Exception e) {
			System.out.println ("Error - " + e);
		}
	}
	public void temperatureChanged(double temperature)
		throws java.rmi.RemoteException
	{
		System.out.println ("Temperature change event : " +	temperature);
	}
}