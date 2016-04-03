import java.net.*;
import java.io.*;
import java.util.*;

class DrawClient
{
	static int portNo = 6789;					// The port used for communication
	public static void main(String args[]) throws IOException
	{
		if (args.length != 1) {					// the server name needs to be speciied
		    System.err.println(
		        "Usage: java EchoClient <host name>");
		    System.exit(1);
	        }
		String hostName = args[0];				//hostname is the first name
        
		try(
			Socket echoSocket = new Socket(hostName, portNo);
		    PrintWriter outToServer =
			new PrintWriter(echoSocket.getOutputStream(), true);
		    BufferedReader inFromServer =
			new BufferedReader(
			    new InputStreamReader(echoSocket.getInputStream()));
		    BufferedReader stdIn =
			new BufferedReader(
			    new InputStreamReader(System.in));
		) {
            System.out.println("Please Enter \n1 if you are Coordinator \n2 if you are reciever");
            int choice = Integer.parseInt(stdIn.readLine());
        
            if(choice == 1){						// coordinator
                String userInput;
                String ServerInput="";
                while ((userInput = stdIn.readLine()) != null ) {
                    outToServer.println(userInput);
                    ServerInput = inFromServer.readLine();
                    System.out.println("echo: " + ServerInput);
                    if(ServerInput.charAt(0)=='.')
                        break;
                }
                System.out.println(" You entered \".\", hence session ended ");
            }
            else{							// pupil
                String ServerInput="";
                while ((ServerInput = inFromServer.readLine()) != null ) {
                    System.out.println("echo: " + ServerInput);
                    if(ServerInput.charAt(0)=='.')
                        break;
                }
                System.out.println(" The co-ordinator entered \".\", hence session ended ");
            
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}
