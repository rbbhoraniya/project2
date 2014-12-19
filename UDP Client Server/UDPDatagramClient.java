import java.io.*;
import java.net.*;

public class UDPDatagramClient
{
public static void main(String args[]) throws Exception
{
  BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
  DatagramSocket clientSocket = new DatagramSocket();
  InetAddress IPAddress = InetAddress.getByName("localhost");
  byte[] sendData = new byte[100];
  byte[] receiveData = new byte[100];
  
  System.out.print("\nEnter some Data: ");
  String sentence = inFromUser.readLine();
  sendData = sentence.getBytes();
  
  // Create Datagaram packeet sontaining the data in byte format
  // along with destination IP Address and port number
  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9999);
  clientSocket.send(sendPacket);
  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
  clientSocket.receive(receivePacket);
  String echoString = new String(receivePacket.getData());
  
  System.out.println("Data Echoed back from Server: " + echoString);
  clientSocket.close();
}
}