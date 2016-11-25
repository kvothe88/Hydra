import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
	
	public static void main(String[] args) {
		
		AtomicInteger numThreads = new AtomicInteger(0); // 
		
		// the list of threads  is kept in a linked list
		ArrayList<Thread> list= new ArrayList<Thread>();
		
		try {
			
			// listen for incoming connections on port 15432
			ServerSocket socket = new ServerSocket(15432);
			System.out.println("Server listening on port 15432");
			
			// loop until program is stopped
			while(true){
				
				// accept a new connection
				Socket client = socket.accept();
				
				// start a new ServerThread to handle the connection and send output to the client
				Thread thrd = new Thread(new ServerThread(client));
				
				list.add(thrd); // add the thread to the list
				
				thrd.start(); // start the thread
				
				numThreads.incrementAndGet(); // increment by one the value on numThread.
				System.out.println("Thread " + numThreads.get() + " started");
			}
		
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}
