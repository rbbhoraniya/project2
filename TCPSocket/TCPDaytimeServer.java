
import java.net.*;
import java.io.*;
// Chapter 6, Listing 2
public class TCPDaytimeServer
{
public static final int SERVICE_PORT = 13;

public static void main(String args[])
{
	try
	{
		// Bind to the service port, to grant clients
		// access to the TCP daytime service
		ServerSocket server = new ServerSocket(SERVICE_PORT);
		System.out.println ("Daytime Server started.");
		// Loop indefinitely, accepting clients
		while(true)
		{
			System.out.println ("\nWaiting for Client...");
			// Get the next TCP client
			Socket nextClient = server.accept();
			// Display connection details
			System.out.println ("Received request from " 
						+ nextClient.getInetAddress() + ":" 
						+ nextClient.getPort() );
			// Don't read, just write the message
			OutputStream out = nextClient.getOutputStream();
			PrintStream pout = new PrintStream (out);
			// Write the current date out to the user
			pout.print( new java.util.Date() );
			// Flush unsent bytes
			out.flush();
			// Close stream
			out.close();
			// Close the connection
			nextClient.close();
		}
	}
	catch (BindException be)
	{
		System.err.println ("BindException: "  + 
			"Service already running on port " + SERVICE_PORT );
	}
	catch (IOException ioe)
	{
		System.err.println ("I/O error :- " + ioe);
	}
}
}