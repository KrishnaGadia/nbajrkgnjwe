package Mrin_Krish;
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
    static int portNo = 6788;					// The port used for communication

  public static void main(String args[]){
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
    while ((ServerInput = inFromServer.readLine()) != null ) {
        System.out.println("Co-od: " + ServerInput);
    }
    System.out.println(" The co-ordinator entered \".\", hence session ended ");

	} catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } 

	//Mrin'z code
    JFrame frame= new JFrame("DISTRIBUTED WHITEBOARD");
    final JPanel panel= new JPanel();

    
    JMenu file=new JMenu("File");
    JMenu colors=new JMenu("Colors");
    JMenu shapes=new JMenu("Shapes");
    //JButton eraser=new JButton("Eraser");
    JMenu eraser=new JMenu("Eraser");
    JMenu freehand=new JMenu("Free Hand");
    
    JMenuBar menubar=new JMenuBar();
    menubar.add(file);
    menubar.add(colors);
    menubar.add(shapes);
    menubar.add(eraser);
    menubar.add(freehand);
    //UserInterface ui = new UserInterface();
    
   	g = panel.getGraphics();
	//g.setColor(Color.blue);
	g.drawLine(20,20,100,100);
    
    // Add the MenuBar to the Frame window
    frame.setJMenuBar(menubar);
    menubar.setVisible(true);

    // Set the background of the Panel to WHITE
    panel.setBackground(Color.white);
    // Add the Panel to the Frame window
    frame.add(panel);
    frame.setSize(1000,1000); // Set the size of the Frame to 500,500
    frame.setVisible(true); 
    frame.setTitle("Distributed Whiteboard"); // Displays the title of the Frame as 'Distributed Whiteboard'
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default close operation of the Frame window
  }
  
 }

