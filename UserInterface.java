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

public class UserInterface {
    static Graphics g;
    static Color drawColor = Color.BLUE;
    static int prevX,prevY,x,y;
  public static void main(String args[]){
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
    

    
    //Menu Items for Menu File
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
    
    eraser.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
        
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(30));
        g.setColor(Color.gray);
        x=e.getX();
        y=e.getY();
        
        g2.drawLine(prevX,prevY,x,y);
        prevX=x;
        prevY=y;
        g.setColor(drawColor);
      }
    });
    
    eraser.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent e){
        eraser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //Graphics2D g2=(Graphics2D)g;
        //g2.setStroke(new BasicStroke(20));
        //g.setColor(Color.white);
        prevX=e.getX();
        prevY=e.getY();
        //g2.drawLine(prevX,prevY,x,y);
        g.setColor(drawColor);
      }
    });

    //Menu Items for Menu Colors
    JMenuItem blue=new JMenuItem("Blue");
    colors.add(blue);
    // Action Listener for the JMenuItem Blue color
    blue.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.blue;
      }
    });
                           
    JMenuItem green=new JMenuItem("Green");
    colors.add(green);
    green.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        //panel.setBackground(Color.green);
        //Graphics g;
        UserInterface.g=panel.getGraphics();
        drawColor = Color.GREEN;
      }
    });
    
    JMenuItem pink=new JMenuItem("Pink");
    colors.add(pink);
    pink.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.pink;
      }
    });
    
    JMenuItem white=new JMenuItem("White");
    colors.add(white);
    white.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        //panel.setBackground(Color.white);
        drawColor = Color.white;
      }
    });
    
    JMenuItem red=new JMenuItem("Red");
    colors.add(red);
    red.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.red;
        
      }
    });
    
    JMenuItem black=new JMenuItem("Black");
    colors.add(black);
    black.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        drawColor = Color.black;
      }
    });
    
    //Menu Items for Menu Shapes
    JMenuItem circle=new JMenuItem("Circle");
    shapes.add(circle);
    circle.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        //Graphics g;
        //g=panel.getGraphics();
        //g=panel.getGraphics();
        g.setColor(drawColor);
        g.drawOval(100,100,200,200);
        //g.setColor(Color.BLUE);
        //g.drawOval(40,50,500,500);
        //g.setColor(Color.GREEN);
        //g.drawOval(75,70,300,300);
               
      }
    });
    JMenuItem square=new JMenuItem("Square");
    shapes.add(square);
    square.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        g=panel.getGraphics();
        g.setColor(drawColor);
        g.drawRect(200,200,200,200);
      }
    });
    JMenuItem rectangle= new JMenuItem("Rectangle");
    shapes.add(rectangle);
    JMenuItem triangle=new JMenuItem("Triangle");
    shapes.add(triangle);
    JMenuItem line=new JMenuItem("Line");
    shapes.add(line);
    
    //final int prevX,prevY,x,y;
    line.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){
        System.out.println("Pressed");
        //Graphics g;
        
        prevX= ev.getX();
        prevY=ev.getY();
        System.out.println(prevX);
        System.out.println(prevY);
        g=panel.getGraphics();
        g.setColor(drawColor);
        //g.drawLine(prevX,prevY,x,y);
      }
      
      public void mouseClicked(MouseEvent e){
          System.out.println("Clicked");
      }
         
         public void mouseReleased(MouseEvent e){
           System.out.println("Released");
           g.drawLine(prevX,prevY,x,y);
         }
         
         public void mouseEntered(MouseEvent e){
             System.out.println("Entered");
         }
         
         public void mouseExited(MouseEvent e){
           System.out.println("Exited");

         }
         
    });
    
    line.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
         x = e.getX();  
         y = e.getY();   
         System.out.println("x is "+x);
         System.out.println(y);
                
         //g.drawLine(prevX, prevY, x, y);  // Draw the line.
         
         //prevX = x;  
         //prevY = y;
      }
    });
                              
    //panel.add(eraser);
    
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
