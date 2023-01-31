package ie.gmit.dip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

//Accepting connections and returning data
public class ChatServer {
	private Set<String> ConnectedUsers = new HashSet<>(); // Set to keep track of names of users connected
	private Set<ConnectedThreads> ConnectedThreads = new HashSet<>(); // Set to keep track of connected threads

	public static int Port = 0;

	public void getPort(int Port) {	//Get port
		ChatServer.Port = Port;
	}

	public void Server(String[] args) {
		try (ServerSocket serv1 = new ServerSocket(13)) { // Only has to listen on host its currently on, port 13
			while (true) {
				System.out.println("Listening for connection on port " + Port);
				try (Socket connection = serv1.accept()) {	//Create a new socket and start accept function
					System.out.println("Client connected from host" + connection.getInetAddress() + ", port "
							+ connection.getPort());
					ConnectedThreads newUser = ConnectedThreads(connection, this);	//Pass in arguments for newUSer
					ConnectedThreads.add(newUser);
					newUser.start();		
					Writer output = new OutputStreamWriter(connection.getOutputStream()); // Return output stream
					output.write("\r\n");// Carraige return to get a new line, equivilant of enter
					output.flush();		//Only have to flush becaus eof try with resources
				} catch (IOException e) {
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		System.out.println("Finished Listening...");

	}

	void removeUser(String userName, ConnectedThreads aUser) {
		boolean removed = ConnectedUsers.remove(userName);
		if (removed) {
			ConnectedThreads.remove(aUser);
			System.out.println("The user " + userName + " quitted");
		}
	}

	private ConnectedThreads ConnectedThreads(Socket connection, ChatServer chatServer) {
		return null;

	}

	Set<String> getUserNames() {	
		return this.ConnectedUsers;
	}

	public boolean hasUsers() {

		if (this.ConnectedUsers.isEmpty()) {

		}
		return false;

	}

	public void addUserName(String userName) {
		ConnectedUsers.add(userName);

	}

	void broadcast(String message, ConnectedThreads excludeUser) {
		for (ConnectedThreads User1 : ConnectedThreads) { 	// For the first user in the collection of Threads
			if (User1 != excludeUser) { 					// if User1 doesn't equal exclude user
				User1.sendMessage(message);					 // Send the message
			}
		}
	}
}
