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
    static Color drawColor = Color.black; // deafult starting color is BLACK
    static int prevX,prevY,x,y;    
    static int brushsize;
    
  public static void main(String args[]){
    JFrame frame= new JFrame("DISTRIBUTED WHITEBOARD");
    // Canvas Panel
    final JPanel panel= new JPanel();
    // Another JPanel for holding all the tools on the side
    final JPanel sidepanel= new JPanel(); 
    
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
    JButton fill=new JButton("FILL");
    fill.setPreferredSize(new Dimension(100,25));
    sidepanel.add(fill);
    
    // Eraser Tool
    JButton eraser_button=new JButton("ERASER");
    eraser_button.setPreferredSize(new Dimension(100,25));
    sidepanel.add(eraser_button);
    
    // Tiny Eraser
    JButton tiny=new JButton("Size1");
    tiny.setPreferredSize(new Dimension(100,25));
    sidepanel.add(tiny);
    
    //Big Eraser
    JButton big=new JButton("Size2");
    big.setPreferredSize(new Dimension(100,25));
    sidepanel.add(big);
    
    // Free-hand TOOL
    JButton freehand=new JButton("PENCIL");
    freehand.setPreferredSize(new Dimension(100,25));
    sidepanel.add(freehand);
    
    // Text-Box TOOL
    JButton textbox= new JButton("Text-Box");
    textbox.setPreferredSize(new Dimension(100,25));
    sidepanel.add(textbox);
    
    // Clear Button
    JButton clear=new JButton("CLEAR");
    clear.setPreferredSize(new Dimension(100,25));
    sidepanel.add(clear);
    
        
    sidepanel.setPreferredSize(new Dimension(150,200));                      
    frame.add(sidepanel,BorderLayout.WEST);
    
    
    
    JMenu file=new JMenu("File");
    //JMenu colors=new JMenu("Colors");
    JMenu shapes=new JMenu("Shapes");

    //JMenu eraser=new JMenu("Eraser");
    //JMenu freehand=new JMenu("Free Hand");
    
    JMenuBar menubar=new JMenuBar();
    menubar.add(file);
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
            System.exit(0);
          } else if (response == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null,"JOptionPane closed");
          }

        }
    });
    file.add(quit);      
    
    
    // ERASER Implementation
    eraser_button.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
        g=panel.getGraphics();
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(brushsize));
        g2.setColor(Color.black);
        x=e.getX();
        y=e.getY();
        
        g2.drawLine(prevX,prevY,x,y);
        prevX=x;
        prevY=y;
        //g.setColor(drawColor);
      }
    });
    
    eraser_button.addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent e){
        eraser_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        g=panel.getGraphics();
        prevX=e.getX();
        prevY=e.getY();

        //g.setColor(drawColor);
      }
      
      public void mouseClicked(MouseEvent e){
          
      }
         
      public void mouseReleased(MouseEvent e){           
       
      }
         
      public void mouseEntered(MouseEvent e){
            
       }
         
      public void mouseExited(MouseEvent e){
          
       }
      
    });
    
    // TextBox IMPLEMENTATION
    textbox.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JTextArea textarea=new JTextArea(5,10);

        panel.add(textarea);
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
    circle.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ev){
        //Graphics g;
        //g=panel.getGraphics();
        g=panel.getGraphics();
        
        g.setColor(drawColor);
        g.drawOval(100,100,200,200);
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
    
    // LINE IMPLEMENTATION 
    line.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
         x = e.getX();  
         y = e.getY();   
                         
         //g.drawLine(prevX, prevY, x, y);  // Draw the line.
         
         //prevX = x;  
         //prevY = y;
      }
    });
    
    line.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){
       
        prevX= ev.getX();
        prevY=ev.getY();
      
        //g=panel.getGraphics();
        //g.setColor(drawColor);
      
      }
      
      public void mouseClicked(MouseEvent e){
          
      }
         
      public void mouseReleased(MouseEvent e){
        g=panel.getGraphics();   
        g.drawLine(prevX,prevY,x,y);
      }
         
      public void mouseEntered(MouseEvent e){
            
       }
         
      public void mouseExited(MouseEvent e){
          
       }
         
    });
    
    
                              
    // FREE HAND IMPLEMENTATION
    freehand.addMouseListener(new MouseAdapter(){
      
      public void mousePressed(MouseEvent ev){
        prevX= ev.getX();
        prevY=ev.getY();
        
        g=panel.getGraphics();
        g.setColor(drawColor);
        
      }
      
      public void mouseClicked(MouseEvent e){
        }
         
      public void mouseReleased(MouseEvent e){
        }
         
      public void mouseEntered(MouseEvent e){
      }
         
      public void mouseExited(MouseEvent e){
       }
         
    });
    
    freehand.addMouseMotionListener(new MouseMotionAdapter(){
      public void mouseDragged(MouseEvent e){
         x = e.getX();  
         y = e.getY();   
         g.setColor(drawColor);                
         g.drawLine(prevX, prevY, x, y);  // Draw the line.
         
         prevX = x;  
         prevY = y;
      }
    });
    
    //FILL Implementation
    
    fill.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        g=panel.getGraphics();
        g.setColor(drawColor);
        g.fillRect(0,0,1000,650);
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
  }
  
 }
