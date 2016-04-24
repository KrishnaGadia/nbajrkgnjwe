import java.net.*;
import java.io.*;
import java.util.*;
public class KKMultiServerThread extends Thread {
    
    private Socket cs = null;
    private Socket ps = null;
    private String record = null;
    private int loc ;
 
    public KKMultiServerThread(Socket socket1,Socket socket2) {
        //super("MultiServerThread");
        this.cs = socket1;
        this.ps = socket2;
        this.record = "#";
        this.loc = 0;
        System.out.println("New thread is running");
    }
    //public void start(){ this.run();}
    public void run() {
 				
        try (
            PrintWriter outToClient 
									= new PrintWriter(cs.getOutputStream(),true);
						PrintWriter outToPupil = new PrintWriter(this.ps.getOutputStream(),true);			
						BufferedReader inFromClient 
								= new BufferedReader(new InputStreamReader(this.cs.getInputStream()));
        ) {
            String inputLine;
						 System.out.println("Waiting for input");
             while ((inputLine = inFromClient.readLine()) != null ) {
							/*if(inputLine.charAt(0)=='#' && inputLine.charAt(3)=='#' ){
								outToPupil.println(inputLine);
								break;
								}*/
							System.out.println(inputLine+" got");
							record = record.concat(inputLine+"\n");
							System.out.println(inputLine+" got");
							if(inputLine!=null){
								outToClient.println(inputLine);
								outToPupil.println(inputLine);
								}			
				
							}
						
						System.out.println("One Session has Ended");
						record.concat("#");
		
						cs.close();
						ps.close();
		
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




/*

import java.net.*;
import java.io.*;
import java.util.*;

class DrawServer 
{
    
	static int portNo = 6788;
	static Socket cs,ps;
	public static void main(String args[]) 
	{	
		
		InputStream is ;
		OutputStream os ;
		     	
		String record = "#";//Keeps a record of the things obtained till now
		int loc=0;		
		try{
			while(true){
			
			ServerSocket ss = new ServerSocket(portNo);
			Socket cs = ss.accept();
			System.out.println("Co ordinator accepted");
			Socket ps = ss.accept();
			System.out.println("Pupil accepted");
			
			System.out.println("Came here");
			PrintWriter outToClient 
					= new PrintWriter(cs.getOutputStream(),true);
			PrintWriter outToPupil = new PrintWriter(ps.getOutputStream(),true);			
			BufferedReader inFromClient 
					= new BufferedReader(new InputStreamReader(cs.getInputStream()));

			String inputLine;
		    while ((inputLine = inFromClient.readLine()) != null ) {
				if(inputLine.charAt(0)=='#' && inputLine.charAt(3)=='#' ){
					outToPupil.println(inputLine);
					break;
					}
				record = record.concat(inputLine+"\n");
				System.out.println(inputLine+" got");
				if(inputLine!=null){
					outToClient.println(inputLine);
					outToPupil.println(inputLine);
					}			
				
				}
			
			loc = record.lastIndexOf("#");
			for(int i = loc;i<record.length();i++){
				System.out.print(record.charAt(i));			
				} 	//Print contents of this session
			System.out.println("One Session has Ended");
			record.concat("#");
		
			cs.close();
			ps.close();
			ss.close();
		
				}
			}	
		catch( Exception e){
			System.out.println(e.toString()+" Is the EXCEPTION");
		}
		
	}
}
*/
