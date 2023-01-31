package ie.gmit.dip;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

//This is how you read data from a socket connection
public class ChatClient {
	public static int port = 0;
	private static String localhost;
	private String userName;

	public ChatClient(String hostname, int port) { // Constructor to use the functionality of the class
		ChatClient.localhost = hostname;
		ChatClient.port = port;
	}

	void setUserName(String userName) { //Set username
		this.userName = userName;
	}

	String getUserName() {			//Get username
		return this.userName;
	}

	public void startClients() { //Method to start the Thread classes
		try {Socket socket = new Socket(localhost, port);
		  new ReadThread(socket, this).start();
          new WriteThread(socket, this).start();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ClientConnect(String[] args) {

		String hostname = args.length > 0 ? args[0] : localhost;

		try (Socket socket = new Socket(hostname, port)) { // Create a new socket
			System.out.println("Connected to Server on host " + hostname);
			socket.setSoTimeout(15000);		//Set timeout to be 15000 milliseconds
			InputStream input = socket.getInputStream(); //Read in a stream of bytes from the socket
			byte[] inputBytes = input.readAllBytes();
			String time = new String(inputBytes, StandardCharsets.US_ASCII);
			System.out.println(time);
		} catch (UnknownHostException e) {
			System.out.println("Can't connect to the host");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
