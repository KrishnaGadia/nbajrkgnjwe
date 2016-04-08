/* Distributed Whiteboard User Interface Implementation
 * KRISHNA GADIA
 * MRINALINI VASANTHI
 * COMP 512
 * SPRING 2016
*/
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

public class UI {
    static Graphics g;    
    static Color drawColor = Color.black; // deafult starting color is BLACK
    static int prevX,prevY,x,y;    
    static int brushsize;
    static boolean isActive=false;
    static int mode;
    
    public static void main(String[] args){
    // Main JFrame
    JFrame frame= new JFrame("DISTRIBUTED WHITEBOARD");
    // Canvas Panel
    JPanel panel= new JPanel();
    // Another JPanel for holding all the tools on the side
    JPanel sidepanel= new JPanel(); 
    
    //Red Color 
    JButton red_button=new JButton("");
    red_button.setForeground(Color.BLACK);
    red_button.setBackground(Color.RED);
    red_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(red_button);
    //Blue Color
    JButton blue_button=new JButton("");
    blue_button.setForeground(Color.BLUE);
    blue_button.setBackground(Color.BLUE);
    blue_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(blue_button);
    //Yellow Color
    JButton yellow_button=new JButton("");
    yellow_button.setForeground(Color.YELLOW);
    yellow_button.setBackground(Color.YELLOW);
    yellow_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(yellow_button);
    //Green Color
    JButton green_button=new JButton("");
    green_button.setForeground(Color.GREEN);
    green_button.setBackground(Color.GREEN);
    green_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(green_button);
    //Black Color
    JButton black_button=new JButton("");
    black_button.setForeground(Color.BLACK);
    black_button.setBackground(Color.BLACK);
    black_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(black_button);
    //Pink Color
    JButton pink_button=new JButton("");
    pink_button.setForeground(Color.PINK);
    pink_button.setBackground(Color.PINK);
    pink_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(pink_button);
    //White Color
    JButton white_button=new JButton("");
    white_button.setForeground(Color.WHITE);
    white_button.setBackground(Color.WHITE);
    white_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(white_button);
    // Cyan Color
    JButton cyan_button=new JButton("");
    cyan_button.setForeground(Color.CYAN);
    cyan_button.setBackground(Color.CYAN);
    cyan_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(cyan_button);
    // Choose Color
    JButton choose_color=new JButton("Palette");
    choose_color.setPreferredSize(new Dimension(120,25));
    sidepanel.add(choose_color);
    
    //Fill Tool
    Icon fill_image = new ImageIcon("fill.png");
    JButton fill=new JButton(fill_image);
    fill.setPreferredSize(new Dimension(120,25));
    sidepanel.add(fill);
    
    // Eraser Tool
    Icon eraser_image = new ImageIcon("eraser.png");   
    JButton eraser_button=new JButton(eraser_image);    
    eraser_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(eraser_button);
    
    // Tiny Eraser
    Icon tiny_image = new ImageIcon("brush1.png");
    JButton tiny=new JButton(tiny_image);
    tiny.setPreferredSize(new Dimension(120,25));
    sidepanel.add(tiny);
    
    //Big Eraser
    Icon big_image = new ImageIcon("brush2.png");
    JButton big=new JButton(big_image);
    big.setPreferredSize(new Dimension(120,25));
    sidepanel.add(big);
    
    // Free-hand TOOL
    Icon pencil_image= new ImageIcon("pencil.png");
    JButton freehand=new JButton(pencil_image);
    freehand.setPreferredSize(new Dimension(120,25));
    sidepanel.add(freehand);
    
    // Text-Box TOOL
    Icon textbox_image = new ImageIcon("textbox.png");
    JButton textbox= new JButton(textbox_image);
    textbox.setPreferredSize(new Dimension(120,25));
    textbox.setEnabled(false);
    sidepanel.add(textbox);
    
    // Clear Button
    Icon clear_image = new ImageIcon("clear.gif");
    JButton clear=new JButton(clear_image);
    clear.setPreferredSize(new Dimension(120,25));
    sidepanel.add(clear);
    
    // Line Button
    JButton line_button = new JButton("LINE");
    line_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(line_button);
    
    // Circle Button
    JButton circle_button = new JButton("CIRCLE");
    circle_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(circle_button);
    
    // Square Button
    JButton square_button = new JButton("SQUARE");
    square_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(square_button);
        
    // Rectangle Button
    JButton rectangle_button = new JButton("RECTANGLE");
    rectangle_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(rectangle_button);
    
    // Triangle Button
    JButton triangle_button = new JButton("TRIANGLE");
    triangle_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(triangle_button);    
    
    // Set the preferred size of the SidePanel to 150,200    
    sidepanel.setPreferredSize(new Dimension(150,200));  
    // Add the SidePanel to WEST in the main Frame
    frame.add(sidepanel,BorderLayout.WEST); 
    
    // Create a Menu - File
    JMenu file=new JMenu("File");
    // Create the main Menu Bar   
    JMenuBar menubar=new JMenuBar();
    // Add the Menu to the Menu Bar
    menubar.add(file);
               
    //Menu Items for Menu File
    JMenuItem quit=new JMenuItem("Quit");
    quit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          
          int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "EXIT!!",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          if (response == JOptionPane.NO_OPTION) {
          } else if (response == JOptionPane.YES_OPTION) {  
            System.exit(0);
          } 
        }
    });
    file.add(quit);      
    
   
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
    // Blue Implementation
    blue_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.blue;
      }
    });
    
    // Green Implementation
    green_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){        
        drawColor = Color.green;
      }
    });
    
    // White Implementation
    white_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        drawColor = Color.white;
      }
    });
    
   // Pink Implementation
    pink_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.pink;
      }
    });
    
    // Black Implementation
    black_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.black;
      }
    });
    
    // Red Implementaion
    red_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.red;
      }
    });
    
    // Yellow Implementation
    yellow_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.yellow;
      }
    });
    
     // Cyan Implementation
     cyan_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.cyan;
      }
    });              
    
     // Color Palette Implementation
    choose_color.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        Color c = JColorChooser.showDialog(null, "Color Palette",Color.white);      
        drawColor =c;
      }
    });      
    
    // Circle Implementation mode 2
    
    circle_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    circle_button.addMouseListener(new MouseAdapter(){
      
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
 
 // Square Implementation - Mode :3
    
    square_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    square_button.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){  }
      
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
 
 // RECTANGLE Implementation : Mode - 4
    
    rectangle_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    rectangle_button.addMouseListener(new MouseAdapter(){
      
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
   

 // TRIANGLE Implementation : Mode -5
    
    triangle_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    triangle_button.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){
       }
      
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=5;
      }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=5;
      }
         
      public void mouseEntered(MouseEvent e){    }
         
      public void mouseExited(MouseEvent e){    }
         
    });

    // LINE IMPLEMENTATION : Mode -1
    line_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
        
      }
    });
    
    line_button.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent ev){       }
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=1;
      }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=1;
      }
         
      public void mouseEntered(MouseEvent e){
            
       }
         
      public void mouseExited(MouseEvent e){
          
       }
         
    });
      
       
    // ERASER Implementation : Mode -10
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
          else if(mode==10) //Eraser was selected
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
      
      public void mouseClicked(MouseEvent e){}
         
      public void mouseReleased(MouseEvent e){
       if(isActive)
       {
        g=panel.getGraphics();
        g.setColor(drawColor);
        int mx,my,dx,dy,r,b,gr;
        r = drawColor.getRed(); 
        gr = drawColor.getGreen(); 
        b = drawColor.getBlue();
  
      if(mode==2) //Circle
        {
        mx = Math.min(x,prevX); 
        my = Math.min(y,prevY);
        dx = Math.abs(x-prevX); 
        dy = Math.abs(y-prevY);
        g.drawOval(mx,my,dx,dy);
           
        }
        
      else if(mode==3) //Square
        {
          mx = Math.min(x,prevX); 
          my = Math.min(y,prevY);
          dx = Math.max(Math.abs(x-prevX), Math.abs(y-prevY));
          g.drawRect(mx,my,dx,dx);

        }

        else if(mode==4) //Rectangle
        {
          mx = Math.min(x,prevX); 
          my = Math.min(y,prevY);
          dx = Math.abs(x-prevX); 
          dy = Math.abs(y-prevY);
          g.drawRect(mx,my,dx,dy);

        }
  
        else if(mode == 5) // Triangle
        {
          g.drawLine(prevX,y,x,y);     
          g.drawLine(prevX,y,(int)((x+prevX)/2),prevY);    
          g.drawLine((int)((x+prevX)/2),prevY,x,y);    
  
        }
  
        else if(mode==1) //Line
        {
          g.drawLine(prevX,prevY,x,y);

        }
        else
          System.out.println("Wrong mode!");
       }       
       isActive=false;
      }
         
      public void mouseEntered(MouseEvent e){  }
         
      public void mouseExited(MouseEvent e){ }
         
    });
    

                              
    // FREE HAND IMPLEMENTATION
    freehand.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){}
      public void mouseClicked(MouseEvent e){
        isActive=true;
        mode=6;
        }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=6;
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
    frame.setSize(1000,700); // Set the size of the Frame to 1000,650
    frame.setVisible(true); 
    frame.setTitle("Distributed Whiteboard"); // Displays the title of the Frame as 'Distributed Whiteboard'
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}