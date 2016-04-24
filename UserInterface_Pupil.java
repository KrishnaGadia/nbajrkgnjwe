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

public class UserInterface_Pupil {
    static Graphics g;    
    static Color drawColor = Color.black; // deafult starting color is BLACK
    static int prevX,prevY,x,y;    
    static int brushsize;
    static boolean isActive=false;
    static int mode;
    static JPanel panel= new JPanel();
    
    
  public static void main(String args[]){
    JFrame frame= new JFrame("DISTRIBUTED WHITEBOARD");
    // Canvas Panel
    //JPanel panel= new JPanel();
    // Another JPanel for holding all the tools on the side
    JPanel sidepanel= new JPanel(); 
    
    // Spoke to Dr.Null on 04/04/16 and she said Square is not needed as we have Rectangle, Circle is not needed as we have Oval, and use POLYGON for Traingle
    
    // Red Color 
    JButton red_button=new JButton("");
    red_button.setForeground(Color.BLACK);
    red_button.setBackground(Color.RED);
    red_button.setPreferredSize(new Dimension(120,25));
    red_button.setEnabled(false);
    sidepanel.add(red_button);
    // Blue Color
    JButton blue_button=new JButton("");
    blue_button.setForeground(Color.BLUE);
    blue_button.setBackground(Color.BLUE);
    blue_button.setPreferredSize(new Dimension(120,25));
    blue_button.setEnabled(false);
    sidepanel.add(blue_button);
    // Yellow Color
    JButton yellow_button=new JButton("");
    yellow_button.setForeground(Color.YELLOW);
    yellow_button.setBackground(Color.YELLOW);
    yellow_button.setPreferredSize(new Dimension(120,25));
    yellow_button.setEnabled(false);
    sidepanel.add(yellow_button);
    // Green Color
    JButton green_button=new JButton("");
    green_button.setForeground(Color.GREEN);
    green_button.setBackground(Color.GREEN);
    green_button.setPreferredSize(new Dimension(120,25));
    green_button.setEnabled(false);
    sidepanel.add(green_button);
    // Black Color
    JButton black_button=new JButton("");
    black_button.setForeground(Color.BLACK);
    black_button.setBackground(Color.BLACK);
    black_button.setPreferredSize(new Dimension(120,25));
    black_button.setEnabled(false);
    sidepanel.add(black_button);
    // Pink Color
    JButton pink_button=new JButton("");
    pink_button.setForeground(Color.PINK);
    pink_button.setBackground(Color.PINK);
    pink_button.setPreferredSize(new Dimension(120,25));
    pink_button.setEnabled(false);
    sidepanel.add(pink_button);
    // White Color
    JButton white_button=new JButton("");
    white_button.setForeground(Color.WHITE);
    white_button.setBackground(Color.WHITE);
    white_button.setPreferredSize(new Dimension(120,25));
    white_button.setEnabled(false);
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
    
    // Fill Tool
    Icon fill_image = new ImageIcon("fill.gif");
    JButton fill=new JButton(fill_image);
    fill.setPreferredSize(new Dimension(120,25));
    sidepanel.add(fill);
    
    // Eraser Tool
    Icon eraser_image = new ImageIcon("eraser.gif");   
    JButton eraser_button=new JButton(eraser_image);    
    eraser_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(eraser_button);
    
    // Tiny Eraser
    Icon tiny_image = new ImageIcon("brush1.gif");
    JButton tiny=new JButton(tiny_image);
    tiny.setPreferredSize(new Dimension(120,25));
    sidepanel.add(tiny);
    
    // Big Eraser
    Icon big_image = new ImageIcon("brush2.gif");
    JButton big=new JButton(big_image);
    big.setPreferredSize(new Dimension(120,25));
    sidepanel.add(big);
    
    // Free-hand TOOL
    Icon pencil_image= new ImageIcon("pencil.gif");
    JButton freehand=new JButton(pencil_image);
    freehand.setPreferredSize(new Dimension(120,25));
    sidepanel.add(freehand);
    
    // Text-Box TOOL
    Icon textbox_image = new ImageIcon("textbox.gif");
    JButton textbox= new JButton(textbox_image);
    textbox.setPreferredSize(new Dimension(120,25));
    sidepanel.add(textbox);
    
    // Clear Button
    Icon clear_image = new ImageIcon("clear.gif");
    JButton clear=new JButton(clear_image);
    clear.setPreferredSize(new Dimension(120,25));
    sidepanel.add(clear);
    
    // Circle Button
    JButton circle_button = new JButton("CIRCLE");
    circle_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(circle_button);    
    
    // Rectangle Button
    JButton rectangle_button = new JButton("RECTANGLE");
    rectangle_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(rectangle_button);
    
    // Line Button
    JButton line_button = new JButton("LINE");
    line_button.setPreferredSize(new Dimension(120,25));
    sidepanel.add(line_button);    
         
    sidepanel.setPreferredSize(new Dimension(175,200));                      
    frame.add(sidepanel,BorderLayout.WEST);    
    
    
    JMenu file=new JMenu("File");    
   
    //JMenu interrupt = new JMenu("Interrupt");
        
    JMenuBar menubar=new JMenuBar();
    menubar.add(file);
    //menubar.add(interrupt);     
       
    // Menu Items for Menu File
    JMenuItem quit=new JMenuItem("Quit");
    quit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          
          int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "EXIT!!",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          if (response == JOptionPane.NO_OPTION) {
          } else if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
          } else if (response == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null,"JOptionPane closed");
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
    blue_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.blue;
      }
    });
    
    cyan_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.cyan;
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
    
    choose_color.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        Color c = JColorChooser.showDialog(null, "Color Palette",Color.white);      
        drawColor =c;
      }
    });      
    
    // LINE IMPLEMENTATION 
    line_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
        
      }
    });
    
    line_button.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){}
      
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=1;
      }
         
      public void mouseReleased(MouseEvent e){    
        isActive=true;
        mode=1;
      }
         
      public void mouseEntered(MouseEvent e){}
         
      public void mouseExited(MouseEvent e){}
         
    });
    
    // RECTANGLE Implementation    
    rectangle_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    rectangle_button.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){}
      
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=2;
      }
         
      public void mouseReleased(MouseEvent e){      
        isActive=true;
        mode=2;
      }
         
      public void mouseEntered(MouseEvent e){}
         
      public void mouseExited(MouseEvent e){}
         
    });
    
    // Circle Implementation    
    circle_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
       }
    });
    
    circle_button.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){}
      
      public void mouseClicked(MouseEvent e){
       isActive=true;
       mode=5;
      }
         
      public void mouseReleased(MouseEvent e){      
        isActive=true;
        mode=5;
      }
         
      public void mouseEntered(MouseEvent e){}
         
      public void mouseExited(MouseEvent e){}
         
    });
       
    // ERASER Implementation
    eraser_button.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){}
      
      public void mouseClicked(MouseEvent e){
        isActive=true;
        mode=4;
        }
         
      public void mouseReleased(MouseEvent e){
        isActive=true;
        mode=4;
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
          
          if(mode==3) // Mode=3 : FREE-HAND tool
          {
            g=panel.getGraphics();
            g.setColor(drawColor);
            g.drawLine(prevX,prevY,x,y);
            
            prevX=x;
            prevY=y;
          }         
          else if(mode==4) // Mode=4 : ERASER tool
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
        prevY= ev.getY();
       }
      }
      
      public void mouseClicked(MouseEvent e){}
         
      public void mouseReleased(MouseEvent e){
       if(isActive)
       {
        g=panel.getGraphics();
        g.setColor(drawColor);
        if(mode==1)   // Mode=1 : Shape - LINE
        {
          g.drawLine(prevX,prevY,x,y);
        }
        else if(mode==2) // Mode=2 : Shape - RECTANGLE
        {
          g.drawRect(prevX,prevY,x-prevX,y-prevY);
        }
        else if(mode==5) // Mode=5 : Shape - CIRCLE
        {
          g.drawOval(prevX,prevY,x-prevX,y-prevY);
        }
        else
          System.out.println("Wrong mode!");
       }       
      }
         
      public void mouseEntered(MouseEvent e){}
         
      public void mouseExited(MouseEvent e){}
         
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
    
    // FILL Implementation  
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
    frame.setSize(1000,700); // Set the size of the Frame to 1000,700
    frame.setVisible(true); 
    frame.setTitle("Distributed Whiteboard"); // Displays the title of the Frame as 'Distributed Whiteboard'
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default close operation of the Frame window
  }
  
 }
