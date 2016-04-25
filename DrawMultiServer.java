/* Distributed Whiteboard - CHAT SERVER Implementation
 * KRISHNA GADIA
 * MRINALINI VASANTHI
 * COMP 512
 * SPRING 2016
*/

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
class DrawMultiServer {
    private static final int portNo = 6778; // port_number = 6788
    final static boolean[] client_id = new boolean[20];
    private static HashSet<PrintWriter> print_writer = new HashSet<PrintWriter>();
    static int id = 0;
    // Main Method
    public static void main(String[] args) throws Exception {
        System.out.println("SERVER IS RUNNING!"); // Display to the console that server is running successfully 
        ServerSocket server_socket = new ServerSocket(portNo); 
        
        try {        			
        	 new Client_Thread(server_socket.accept(),true,id++).start();
            while (true) {
                new Client_Thread(server_socket.accept(),false,id++).start();
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
        private boolean isCood;
        private int ID;
        
        // constructor
        public Client_Thread(Socket socket, Boolean Cood, int ID) {
            this.socket = socket;
            this.isCood = Cood;
            this.ID = ID;
        }

        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                client_id [ID] = true;
                print_writer.add(out);
								System.out.println("Connected User with id "+ID);
              	if(isCood){
              	String inputLine = "";
		              while(( inputLine= in.readLine()) != null ) {
		                  System.out.println(" Got " + inputLine +" from "+this.ID);
		                  for (PrintWriter writer : print_writer) {
                        writer.println(inputLine);
                      }//for ends
                      
		                  if(inputLine.charAt(0)=='#' && inputLine.charAt(3)=='#' ){
													break;
													}
		              }//while ends
                }// if ends
                else{
                	this.join();
                }
            }// try ends
              catch (Exception e) {
                System.out.println(e);
               } 
              finally {
                 client_id [ID] = false;
                
                try {
                    socket.close();
                } catch (IOException e) {
                } // close catch block
            } // close finally block
        }
    }
} // program ends
