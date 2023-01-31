package ie.gmit.dip;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

//Writes data to the clients
public class WriteThread extends Thread {
	 private PrintWriter writer;
	 private Socket socket;
	 private ChatClient client;
	 
	  public WriteThread(Socket socket, ChatClient client) {	//Constructor
	        this.socket = socket;
	        this.client = client;
	 try {
		 OutputStream output = socket.getOutputStream();
         writer = new PrintWriter(output, true);		//Write from teh output stream
	 } catch (IOException ex) {
         System.out.println("Error getting output stream");
         ex.printStackTrace();
     }
	 }
	   
	  public void run() {
		  Console console = System.console();
		  String userName = System.console().readLine("\nEnter your name: ");
		  System.out.println("Type q to exit");
	        client.setUserName(userName);
	        writer.println(userName);		//Used to enter the username into the console
	 
	        String text;
	        
	        do {
	        	text = console.readLine(userName);
	        	writer.println(text);
	        } while (!text.equalsIgnoreCase("q"));	//If you type q it exits the application
	        
	        try {
	        	socket.close();
	        }catch (IOException e) {
				System.out.println("Error writing to server");
				e.printStackTrace();
			}
	        }
	  }


	    
	    
	    
	    
	    
	    
	    


