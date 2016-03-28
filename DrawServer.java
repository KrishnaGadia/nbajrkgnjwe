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
        String record = "";//Keeps a record of the things obtained till now
		try{
		
		ServerSocket ss = new ServerSocket(portNo);
		Socket cs = ss.accept();
		Socket ps = ss.accept();
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
		break;
		}
	    }

		System.out.println(record); 
		}

		catch( Exception e){
			System.out.println(e.toString()+" is the error");
        }
	}
}

