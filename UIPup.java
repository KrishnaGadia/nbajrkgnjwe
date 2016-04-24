import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.lang.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.*;
import java.net.*;


public class UIPup {
    static Graphics g;
    static Color drawColor = Color.BLUE;
    static int prevX,prevY,x,y;

  public static void main(String args[]){
  int portNo = 6788;					// The port used for communication
	String hostName = "zeno";
	PrintWriter outToServer;
	BufferedReader inFromServer ;
	try		
	 {
		Socket echoSocket = new Socket(hostName, portNo);
		outToServer = new PrintWriter(echoSocket.getOutputStream(), true);
		inFromServer =
		new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		
	

	System.out.println("Waiting for co-ordinator");
    String ServerInput="";
	String Splits[] ;    
	int v1=0,v2=0,v3=0,v4=0;
	char type;
	while ((ServerInput = inFromServer.readLine()) != null ) {
        //System.out.println("Co-od: " + ServerInput);
		if(ServerInput.charAt(0) == '#' && ServerInput.charAt(4)=='#')
			System.exit(0);
		Splits = ServerInput.split(",");
		/*r = Integer.parseInt(Splits[0]);
		g = Integer.parseInt(Splits[1]);
		b = Integer.parseInt(Splits[2]);
		*/
		type = Splits[0].charAt(0);
		v1 = Integer.parseInt(Splits[1]);
		System.out.println(Splits.length);
		
		v2 = Integer.parseInt(Splits[2]);
		v3 = Integer.parseInt(Splits[3]);
		v4 = Integer.parseInt(Splits[4]);
		
		System.out.println(" Type "+type+" Vars "+v1+" ,"+v2+" ,"+v3+" ,"+v4+" ");

    }
    System.out.println(" The co-ordinator entered \".\", hence session ended ");

	} catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1);
  } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to " + hostName);
      System.exit(1);
  } 

 }
}
