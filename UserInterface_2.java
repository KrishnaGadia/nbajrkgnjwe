package Mrin_Krish;  
import java.net.*;
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

public class UICood {
    static Graphics g;    
    static Color drawColor = Color.black; // deafult starting color is BLACK
    static int prevX,prevY,x,y;    
    static int brushsize;
    static boolean isActive=false;
    static int mode;
	static int portNo = 6788;
    static String record = "!";
	static int rptr = 1;
    
	static String hostName = "zeno";
  public static void main(String args[]){
	Socket echoSocket;
    PrintWriter outToServer;
	BufferedReader inFromServer;	
	BufferedReader stdIn;

	try{
	echoSocket = new Socket(hostName, portNo);
	outToServer = new PrintWriter(echoSocket.getOutputStream(), true);
	inFromServer = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
	stdIn = new BufferedReader( new InputStreamReader(System.in));
	System.out.println("Connected Server");
	
 	
	//Mrin'z code
	
    JFrame frame= new JFrame("DISTRIBUTED WHITEBOARD");
    // Canvas Panel
    JPanel panel= new JPanel();
    // Another JPanel for holding all the tools on the side
    JPanel sidepanel= new JPanel(); 
    
    //Red Color 
    JButton red_button=new JButton("");
    red_button.setForeground(Color.BLACK);
    red_button.setBackground(Color.RED);
    red_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(red_button);
    //Blue Color
    JButton blue_button=new JButton("");
    blue_button.setForeground(Color.BLUE);
    blue_button.setBackground(Color.BLUE);
    blue_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(blue_button);
    //Yellow Color
    JButton yellow_button=new JButton("");
    yellow_button.setForeground(Color.YELLOW);
    yellow_button.setBackground(Color.YELLOW);
    yellow_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(yellow_button);
    //Green Color
    JButton green_button=new JButton("");
    green_button.setForeground(Color.GREEN);
    green_button.setBackground(Color.GREEN);
    green_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(green_button);
    //Black Color
    JButton black_button=new JButton("");
    black_button.setForeground(Color.BLACK);
    black_button.setBackground(Color.BLACK);
    black_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(black_button);
    //Pink Color
    JButton pink_button=new JButton("");
    pink_button.setForeground(Color.PINK);
    pink_button.setBackground(Color.PINK);
    pink_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(pink_button);
    //White Color
    JButton white_button=new JButton("");
    white_button.setForeground(Color.WHITE);
    white_button.setBackground(Color.WHITE);
    white_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(white_button);
    
    //Fill Tool
    Icon fill_image = new ImageIcon("fill.gif");
    JButton fill=new JButton(fill_image);
    fill.setPreferredSize(new Dimension(100,25));
    sidepanel.add(fill);
    
    // Eraser Tool
    Icon eraser_image = new ImageIcon("eraser.gif");   
    JButton eraser_button=new JButton(eraser_image);    
    eraser_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(eraser_button);
    
    // Tiny Eraser
    Icon tiny_image = new ImageIcon("brush1.gif");
    JButton tiny=new JButton(tiny_image);
    tiny.setPreferredSize(new Dimension(100,25));
    sidepanel.add(tiny);
    
    //Big Eraser
    Icon big_image = new ImageIcon("brush2.gif");
    JButton big=new JButton(big_image);
    big.setPreferredSize(new Dimension(100,25));
    sidepanel.add(big);
    
    // Free-hand TOOL
    Icon pencil_image= new ImageIcon("pencil.gif");
    JButton freehand=new JButton(pencil_image);
    freehand.setPreferredSize(new Dimension(100,25));
    sidepanel.add(freehand);
    
    // Text-Box TOOL
    Icon textbox_image = new ImageIcon("textbox.gif");
    JButton textbox= new JButton(textbox_image);
    textbox.setPreferredSize(new Dimension(100,25));
    sidepanel.add(textbox);
    
    // Clear Button
    Icon clear_image = new ImageIcon("clear.gif");
    JButton clear=new JButton(clear_image);
    clear.setPreferredSize(new Dimension(100,25));
    sidepanel.add(clear);
    
    // Line Button
    JButton line_button = new JButton("LINE");
    line_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(line_button);
    
    // Circle Button
    JButton circle_button = new JButton("CIRCLE");
    circle_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(circle_button);
    
    // Square Button
    JButton square_button = new JButton("SQUARE");
    square_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(square_button);
    
        
    sidepanel.setPreferredSize(new Dimension(150,200));                      
    frame.add(sidepanel,BorderLayout.WEST);
    
    
    
    JMenu file=new JMenu("File");
    //JMenu colors=new JMenu("Colors");
    JMenu shapes=new JMenu("Shapes");
    JMenu interrupt = new JMenu("Interrupt");
    //JMenu eraser=new JMenu("Eraser");
    //JMenu freehand=new JMenu("Free Hand");
    
    JMenuBar menubar=new JMenuBar();
    menubar.add(file);
    menubar.add(interrupt);
    //menubar.add(colors);
    menubar.add(shapes);
    //menubar.add(eraser);
    //menubar.add(freehand);
       
    //Menu Items for Menu File
    JMenuItem quit=new JMenuItem("Quit");
    quit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          
          int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "EXIT!!",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          if (response == JOptionPane.NO_OPTION) {
          } else if (response == JOptionPane.YES_OPTION) {
	    UICood.writeToServer("#####",outToServer,inFromServer);
            System.exit(0);
          } 
//Connected Server

        }
    });
    file.add(quit);      
    
    /* // INTERRUPT Implementation
    interrupt.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null," BUZZ!! ");

      }
    });      
    */
    
    
    // TextBox IMPLEMENTATION
    textbox.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JTextArea textarea=new JTextArea(5,10);
        textarea.setEditable(true);
        //textarea.setEnable(true);
        panel.add(textarea,BorderLayout.WEST);
        frame.add(panel);
        frame.setVisible(true);
      }
    });

    // Clear IMPLEMENTATION
    // Works only after the WhiteBoard has something drawn/written on it
    clear.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        g=panel.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,1000,650);
      }
    });

    // Tiny Eraser - SIZE 1 Implementation
    tiny.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        brushsize=15;
      }
    });
    
    // Big Eraser - SIZE 2 Implementation
    big.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        brushsize=50;
      }
    });
   
    // Action Listener for the JButton- colors
    blue_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.blue;
      }
    });
    green_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){        
        drawColor = Color.green;
      }
    });
    
    white_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        drawColor = Color.white;
      }
    });
    
   
    pink_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.pink;
      }
    });
    
    
    black_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.black;
      }
    });
    
    
    red_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.red;
      }
    });
    
    yellow_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.yellow;
      }
    });
    
    //Menu Items for Menu Shapes
    JMenuItem circle=new JMenuItem("Circle");
    shapes.add(circle);
    
    JMenuItem square=new JMenuItem("Square");
    shapes.add(square);
    
    JMenuItem rectangle= new JMenuItem("Rectangle");
    shapes.add(rectangle);
    JMenuItem triangle=new JMenuItem("Triangle");
    shapes.add(triangle);
    JMenuItem line=new JMenuItem("Line");
    shapes.add(line);

	// Circle Implementation mode 1
    
    circle.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    circle.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){  }
      
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=1;
      }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=1;
      }
         
      public void mouseEntered(MouseEvent e){    }
      public void mouseExited(MouseEvent e){    }
         
    });
	
	// Square Implementation
    
    square.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    square.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){  }
      
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=2;
      }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=2;
      }
         
      public void mouseEntered(MouseEvent e){    }
      public void mouseExited(MouseEvent e){    }
         
    });
	
	// RECTANGLE Implementation
    
    rectangle.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    rectangle.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){
       }
      
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=3;
      }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=3;
      }
         
      public void mouseEntered(MouseEvent e){    }
         
      public void mouseExited(MouseEvent e){    }
         
    });
   

	// TRIANGLE Implementation
    
    triangle.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    triangle.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){
       }
      
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=4;
      }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=4;
      }
         
      public void mouseEntered(MouseEvent e){    }
         
      public void mouseExited(MouseEvent e){    }
         
    });

    // LINE IMPLEMENTATION 
    line.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
        
      }
    });
    
    line.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent ev){       }
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=5;
      }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=5;
      }
         
      public void mouseEntered(MouseEvent e){
            
       }
         
      public void mouseExited(MouseEvent e){
          
       }
         
    });
      
       
    // ERASER Implementation
    eraser_button.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){}
      
      public void mouseClicked(MouseEvent e){
        isActive=true;
        mode=10;
        }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=10;
        }
         
      public void mouseEntered(MouseEvent e){}
      public void mouseExited(MouseEvent e){}
         
    });
    
    eraser_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){}
    });
    
    // PANEL Implementation
    
    panel.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
        if(isActive){
          x = e.getX();  
          y = e.getY();   
          
          if(mode==6) //Line implemented
          {
            g=panel.getGraphics();
            g.setColor(drawColor);
            g.drawLine(prevX,prevY,x,y);
            
            prevX=x;
            prevY=y;
          }         
          else if(mode==10)	//Eraser was selected
          {
            Graphics2D g2=(Graphics2D)g;
            g=panel.getGraphics();
            g2.setColor(Color.white);
            
            g2.setStroke(new BasicStroke(brushsize));
            g2.drawLine(prevX,prevY,x,y);
            
            prevX=x;
            prevY=y;
          }
          
        }
      }
    });
    
    panel.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){
       if(isActive)
       {
        prevX= ev.getX();
        prevY=ev.getY();
       }
      }
      
      public void mouseClicked(MouseEvent e){     }
         
      public void mouseReleased(MouseEvent e){
       if(isActive)
       {
        g=panel.getGraphics();
        g.setColor(drawColor);
        int mx,my,dx,dy,r,b,gr;
		r = drawColor.getRed(); gr = drawColor.getGreen(); b = drawColor.getBlue();
		
		if(mode==1) //circle implemented
        {
		  mx = Math.min(x,prevX); my = Math.min(y,prevY);
		  dx = Math.abs(x-prevX); dy = Math.abs(y-prevY);
          g.drawOval(mx,my,dx,dy);
		  UICood.writeToServer(r+","+gr+","+b+",O,"+mx+","+my+","+dx+","+dy+",",outToServer,inFromServer);
		  
        }
        
		else if(mode==2) //Square implemented
        {
		  mx = Math.min(x,prevX); my = Math.min(y,prevY);
		  dx = Math.max(Math.abs(x-prevX), Math.abs(y-prevY));
		  g.drawRect(mx,my,dx,dx);
		  UICood.writeToServer(r+","+gr+","+b+",S,"+mx+","+my+","+dx+","+dx+",",outToServer,inFromServer);
        }

        else if(mode==3) //rectangle implemented
        {
		  mx = Math.min(x,prevX); my = Math.min(y,prevY);
		  dx = Math.abs(x-prevX); dy = Math.abs(y-prevY);
		  g.drawRect(mx,my,dx,dy);
		  UICood.writeToServer(r+","+gr+","+b+",R,"+mx+","+my+","+dx+","+dy+",",outToServer,inFromServer);
        }
		
		else if(mode == 4) //triangle implemented
		{
		g.drawLine(prevX,y,x,y);
		  UICood.writeToServer(r+","+gr+","+b+",L,"+prevX+","+y+","+x+","+y+",",outToServer,inFromServer);
		g.drawLine(prevX,y,(int)((x+prevX)/2),prevY);
		  UICood.writeToServer(r+","+gr+","+b+",L,"+prevX+","+y+","+(int)((x+prevX)/2)+","+prevY+",",outToServer,inFromServer);
		g.drawLine((int)((x+prevX)/2),prevY,x,y);
		  UICood.writeToServer(r+","+gr+","+b+",L,"+(int)((x+prevX)/2)+","+prevY+","+x+","+y+",",outToServer,inFromServer);			
		
		}
		
        else if(mode==5) //line implemented
        {
		  g.drawLine(prevX,prevY,x,y);
		  UICood.writeToServer(r+","+gr+","+b+",L,"+prevX+","+prevY+","+x+","+y+",",outToServer,inFromServer);
        }
        else
          System.out.println("Wrong mode!");
       }       
      }
         
      public void mouseEntered(MouseEvent e){  }
         
      public void mouseExited(MouseEvent e){ }
         
    });
    

                              
    // FREE HAND IMPLEMENTATION
    freehand.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){}
      public void mouseClicked(MouseEvent e){
        isActive=true;
        mode=3;
        }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=3;
        }
         
      public void mouseEntered(MouseEvent e){}
      public void mouseExited(MouseEvent e){}
         
    });
    
    freehand.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){}
    });
    
    //FILL Implementation  
    fill.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        g=panel.getGraphics();
        g.setColor(drawColor);
        g.fillRect(0,0,1000,650);
        drawColor = Color.black; // setting it back to default color - BLACK
      }
    });
    
    // Add the MenuBar to the Frame window
    frame.setJMenuBar(menubar);
    menubar.setVisible(true);

    // Set the default background of the Panel to WHITE
    panel.setBackground(Color.white);
    // Add the Panel to the Frame window
    frame.add(panel);
    frame.setSize(1000,650); // Set the size of the Frame to 1000,650
    frame.setVisible(true); 
    frame.setTitle("Distributed Whiteboard"); // Displays the title of the Frame as 'Distributed Whiteboard'
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default close operation of the Frame window

	} catch (UnknownHostException e) {
        System.err.println("Don't know about host " + hostName);
        System.exit(1);
    } catch (IOException e) {
        System.err.println("Couldn't get I/O for the connection to " +
            hostName);
        System.exit(1);
    } 
  }
	  
	public static void writeToServer(String set, PrintWriter outToServer,BufferedReader inFromServer) {
		try{
		record = record.concat(set);
		record = record.concat("!");	//signifies end of one instruction
		outToServer.println(set+"!");
		String ServerInput = inFromServer.readLine();
		System.out.println("server sent: " + ServerInput);
	
		} catch (UnknownHostException e) {
        System.err.println("Don't know about host " + hostName);
        System.exit(1);
    } catch (IOException e) {
        System.err.println("Couldn't get I/O for the connection to " +
            hostName);
        System.exit(1);
    }
	}

	public static String getRecord(){
	/*if(rptr == record.length()){
		return null;}
	int s = record.indexOf('!',rptr-1);
	int e = record.indexOf('!',s+1);
	System.out.println(s+" "+e+" "+rptr); 
	String sub = record.substring(s+1,e);
	rptr = e;
	return sub;*/
	return record;


}
 }
