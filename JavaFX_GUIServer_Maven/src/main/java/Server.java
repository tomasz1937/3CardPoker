import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<PokerInfo> callback;
	
	public Boolean isRunning = true;
	
	Server(Consumer<PokerInfo> call){
	
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	int port = 0;
	
	public void connect(int port) {
		this.port = port;
	}
	
	public boolean isRunning() {
	    return isRunning;
	}
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(port);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				PokerInfo x= new PokerInfo("client has connected to server: " + "client #" + count);
				callback.accept(x);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					isRunning = false;
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
		
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
			}
			
			public void send(String message, int cNum) {
				
					ClientThread t = clients.get(cNum - 1);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
					}
			
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
				 updateClients("new client on server: client #"+count);
					
				 while(true) {
					    try {
					    	String data = in.readObject().toString();
					    	PokerInfo y = new PokerInfo(data);
					    	callback.accept(y);
					    	
					    	if(y.getMessage().equals("Dealer Cards")) {
					    		
					    		DeckOfCards deck = new DeckOfCards();
					    		deck.shuffle();
					    		
					    		String toSend = deck.dealTopCard().getFilename();
					    		send(toSend,1);
					    		
					    		toSend = deck.dealTopCard().getFilename();
					    		send(toSend,1);
					    		
					    		toSend = deck.dealTopCard().getFilename();
					    		send(toSend,1);
					    	}
					    					    	
					    	if(y.getMessage().charAt(0) == 'a') {
					    		
					    		DeckOfCards deck = new DeckOfCards();
					    		deck.shuffle();
					    		
					    			String toSend = deck.dealTopCard().getFilename();
					    			send(toSend,1);
					    		
					    			toSend = deck.dealTopCard().getFilename();
					    			send(toSend,1);
					    		
					    			toSend = deck.dealTopCard().getFilename();
					    			send(toSend,1);
					    			
					    	}
					    		
					    	if(y.getMessage().charAt(0) == 'p') {
					    		
					    		DeckOfCards deck = new DeckOfCards();
					    		deck.shuffle();
						    		
					    			String toSend = deck.dealTopCard().getFilename();
					    			send(toSend,1);
					    		
					    			toSend = deck.dealTopCard().getFilename();
					    			send(toSend,1);
					    		
					    			toSend = deck.dealTopCard().getFilename();
					    			send(toSend,1);
					    		}
					    	}    	
					    
					    catch(Exception e) {
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run	
			
		}//end of client thread
}
	
	

	
