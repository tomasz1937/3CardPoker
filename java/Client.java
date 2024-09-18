import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread{
	
	public Boolean isRunning = true;

	public boolean isRunning() {
	    return isRunning;
	}
	
	String Ip = null;
	int port = 0;
	
	public void connect(String Ip, int port){
		this.Ip = Ip;
		this.port = port;
	}
	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	private Consumer<PokerInfo> callback;
	
	Client(Consumer<PokerInfo> call){
	
		callback = call;
	}
	
	public void run() {
		
		try {
		socketClient = new Socket(Ip,port);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {
			isRunning = false;
		}
		
		while(true) {
			 
			try {
				String data = in.readObject().toString();
		    	PokerInfo y = new PokerInfo(data);
		    	callback.accept(y);;
			}
			catch(Exception e) {}
		}
	
    }
	
	public void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
