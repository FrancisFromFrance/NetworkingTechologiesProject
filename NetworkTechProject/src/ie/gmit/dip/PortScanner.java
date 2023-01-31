package ie.gmit.dip;
import java.net.*;
import java.io.*;
//Scanning a range of ports on the machiene to see if theres any server application listening


public class PortScanner {
	public static final int MAX_PORT = 1024; 
	
	public void ScanPort(String[] args ) {
	
		String hostname = args.length > 0 ? args[0] : "localhost";
	
		
		System.out.println("\n Checking available ports....");
		
		for(int port = 1; port < MAX_PORT; port++) {
			try(Socket socket = new Socket()) { 	
				System.out.println("Attempting  to conect to port " + port + " on host " + hostname );
				
				socket.connect(new InetSocketAddress(hostname, port), 1); //Create a new socketAddress to connect , and specify the timeout in milliseconds
				System.out.println("there is a server on port " + port + " of " + hostname);
				socket.close();	//Close the socket
			} catch(UnknownHostException ex) {
				System.err.println(ex);
				break;
			}catch (IOException ex) {
				System.out.println("No server on this port");
			}
			
		}
		System.out.println("Finsihed scan");
	}
	
}
