import java.net.*;
import java.io.*;
import java.util.*;

class DrawServer 
{
    
	static int portNo = 6789;
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
			PrintWriter outToClient = new PrintWriter(cs.getOutputStream(),true);
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			PrintWriter outToPupil = new PrintWriter(ps.getOutputStream(),true);
		

			String inputLine;
		    while ((inputLine = inFromClient.readLine()) != null ) {
				record = record.concat(inputLine+"\n");
				System.out.println(inputLine+" got");
				outToClient.println(inputLine);
				outToPupil.println(inputLine);
				if(inputLine.charAt(0)=='.'){
					outToPupil.println("Co ordinator ended your session");   
					cs.close();
					ps.close();
					ss.close();                 
					break;
					}
				}
		
			loc = record.lastIndexOf("#");
			for(int i = loc;i<record.length();i++){
				System.out.print(record.charAt(i));
				} 	//Print contents of this session
			record.concat("#");

		
				}
			}
		
		catch( Exception e){
			System.out.println(e.toString()+" is the error");
		}
		
	}
}

