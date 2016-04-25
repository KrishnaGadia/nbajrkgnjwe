/* Distributed Whiteboard - CHAT SERVER Implementation
 * KRISHNA GADIA
 * MRINALINI VASANTHI
 * COMP 512
 * SPRING 2016
*/

import java.io.*;
import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.awt.event.*;
import java.applet.*;

public class ChatServer {
    private static final int port_number = 6008; // port_number = 6008
    private static HashSet<String> client_names = new HashSet<String>();
    private static HashSet<PrintWriter> print_writer = new HashSet<PrintWriter>();
    
    // Main Method
    public static void main(String[] args) throws Exception {
        System.out.println("SERVER IS RUNNING!"); // Display to the console that server is running successfully 
        ServerSocket server_socket = new ServerSocket(port_number); 
        try {
            while (true) {
                new Client_Thread(server_socket.accept()).start();
            }
           } 
            finally {
              server_socket.close();
            }
    }

    private static class Client_Thread extends Thread {
        private String username;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        
        // constructor
        public Client_Thread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
		// define a protocol
                while(true) {
                    out.println("GET_NAME");
                    username = in.readLine();
                    if (username == null) {
                        return;
                    }
                    synchronized(client_names) {
                        if (!client_names.contains(username)) { // doesn't accept two users with same name - case sensitive
                            client_names.add(username); // if username doesn't exist in HashSet, add username to HashSet
                            break;
                        }
                    }
                }

                out.println("ACCEPTED");
                print_writer.add(out);
              
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
		    if(input.equalsIgnoreCase("Interrupt")){  // if client types interrupt(case insensitive) then a message is sent to everyone saying that that particular client needs access to draw/write
		    	input = "Please give me ACCESS to draw/write";
		    }
                    for (PrintWriter writer : print_writer) { 
                        writer.println("CHAT_MESSAGE " + username + " : " + input);
		  }
                }
            }
              catch (IOException e) {
                System.out.println(e);
               } 
              finally {
                        if (username != null) {
                        client_names.remove(username);
                        }
                        
                if (out != null) {
                    print_writer.remove(out);
                }
                
                try {
                    socket.close();
                } catch (IOException e) {
                } // close catch block
            } // close finally block
        }
    }
} // program ends
