package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectedThreads extends Thread {
		private static Socket ThreadSocket;
	    private static ChatServer server;
	    private PrintWriter writer;
	    private  String clientMessage;
	 
	    public ConnectedThreads(Socket socket, ChatServer server) { //Constructor
	    	ConnectedThreads.ThreadSocket = socket;
	    	ConnectedThreads.server = server;
	    }
	    
	    void sendMessage(String message) { 	//Send message
	        writer.println(message);
	    }
	    
	    public void startServer() {
	    	try {
	    		 InputStream in = ThreadSocket.getInputStream();	
	             BufferedReader br = new BufferedReader(new InputStreamReader(in));		//Read in from the socket
	             
	             OutputStream out = ThreadSocket.getOutputStream();
	             writer = new PrintWriter(out, true);		//Write out from the socket
	             
	             printUsers();
	             
	             String userName = br.readLine();
	             server.addUserName(userName);	//Add username
	  
	             String serverMessage = "New user connected: " + userName;
	             server.broadcast(serverMessage, this);		//Broadcast the server maessage to show username
	             
	             do {
	            	 clientMessage = br.readLine();
	            	 serverMessage = userName + clientMessage;
	            	 server.broadcast(serverMessage, this);	//Broadcast the server message to show username 
	            	 
	             } while(!clientMessage.equals("q"));
	             
	             server.removeUser(userName, this);			//Remove user
	             ThreadSocket.close();
	             
	             serverMessage = userName + " has quitted.";
	             server.broadcast(serverMessage, this);
	  
	             } catch (IOException e) {
					System.out.println("Error in Connected Thread");
					e.printStackTrace();
					
				}
	  
	    	
	    }
	    public void printUsers() {
   		 if (server.hasUsers()) {			//If theres users
   		 writer.println("Connected User :" + server.getUserNames());		//Print out user names
   		 } else {
   			 System.out.println("No users found");
   		 }
   	}
}

