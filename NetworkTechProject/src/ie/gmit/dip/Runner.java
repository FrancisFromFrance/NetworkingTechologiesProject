package ie.gmit.dip;

//Runner class used to start the classes. Not needed for the command prompt
public class Runner {
public static void main(String[] args) {
	PortScanner ps = new PortScanner();
	ps.ScanPort(args);			//Start Port SCanner
	
	ChatClient cc = new ChatClient(null, 0);
	cc.ClientConnect(args);		//Start ChatClient
		
	ChatServer cs = new ChatServer();
	cs.Server(args);			//Start ChatSErver
	
	
}
}
