import java.io.*;
import java.net.*;

public class UDPDatagramServer
{
public static void main(String args[]) throws Exception
{
	DatagramSocket serverSocket = new DatagramSocket(9999);
	byte[] receiveData = new byte[100];
	byte[] sendData = new byte[100];
	while(true)
	{
		System.out.println ("Waiting for Client packet to arrive...");
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		serverSocket.receive(receivePacket);
		
		String clientString = new String( receivePacket.getData());
		System.out.println("Data Received from Client side: " + clientString);
		
		InetAddress IPAddress = receivePacket.getAddress();
		int port = receivePacket.getPort();
		sendData = clientString.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		serverSocket.send(sendPacket);
	}
}
}