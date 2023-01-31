package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
//Reading input from the server and printing it to the console
public class ReadThread extends Thread {
	private BufferedReader br;
    private static ChatClient client;
    
    public ReadThread(Socket socket, ChatClient client) {
        ReadThread.client = client;
 
        try {
            InputStream input = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(input));		//Read in the inputstream from the socket
        } catch (IOException ex) {
        	System.out.println("Error getting input stream");
        	ex.printStackTrace();
        }
    }
    public void run() {		
    	while(true) {
    		try {
				System.out.println(br.readLine());
			
    		
    	if (client.getUserName() !=null) {				//If there is a client
			System.out.println(client.getUserName());	//Print clients name
    	}
    	} catch (IOException e) {
			System.out.println("Error getting client name");
			e.printStackTrace();
		}
    	}
    }
}