import java.net.*;
import java.io.*;
import java.util.*;
public class MultiServer {
		static int portNo = 6788;
    public static void main(String[] args) throws IOException {
        
        try (ServerSocket serverSocket = new ServerSocket(portNo)) { 
            while (true) {
            	Socket s1 = serverSocket.accept();
            	System.out.println("Co ordinator accepted");
            	Socket s2 = serverSocket.accept();
            	System.out.println("Pupil accepted");
            	int threadId = 5342;
            	System.out.println(" the Thread id starting is"+threadId);
	            new KKMultiServerThread(s1,s2).start();
	            threadId += 7;
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNo);
            System.exit(-1);
        }
    }
}
