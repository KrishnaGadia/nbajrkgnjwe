package Mrin_Krish;
import java.net.*;
import java.io.*;
import java.util.*;

class DrawServer 
{
    
	static int portNo = 6788;
	static Socket cs,ps;
	static PrintWriter outToPupil;
	static boolean isPupil = false;
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
			outToPupil = new PrintWriter(ps.getOutputStream(),true);			
			BufferedReader inFromClient 
					= new BufferedReader(new InputStreamReader(cs.getInputStream()));

			String inputLine;
		    while ((inputLine = inFromClient.readLine()) != null ) {
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
			record.concat("#");
		
			cs.close();
			ss.close();
		
				}
			}	
		catch( Exception e){
			System.out.println(e.toString()+" Is the EXCEPTION");
		}
		
	}
	
	/*public static void connectPup(ServerSocket ss)throws IOException{
		Socket ps = ss.accept();
		System.out.println("Pupil accepted");
		outToPupil = new PrintWriter(ps.getOutputStream(),true);
		isPupil = true;	
	}
	public static void printPup(String inputLine){
		if(!ps.isClosed()){
			outToPupil.println(inputLine);
		}
	}*/
}

