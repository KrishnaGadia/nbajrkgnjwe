/* Distributed Whiteboard CHAT CLIENT Implementation
 * KRISHNA GADIA
 * MRINALINI VASANTHI
 * COMP 512
 * SPRING 2016
*/

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class ChatDrawClient {
  
  
		String hostName;
      // Main method
    public static void main(String[] args) throws Exception {
        ChatDrawClient client = new ChatDrawClient(); 
        client.chat_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting the default close operation of the JFrame
        client.chat_frame.setVisible(true); // Set the JFrame visible
				client.chat_frame.setSize(600,500); // Set the JFrame size to 600,500
        client.run();
    }
    public class MKPanel extends JPanel{
    	public void paintComponent(Graphics g){
		  	super.paintComponent(g);
		  	System.out.println(" I am tRYING TO draw ");
				for(String shape : shapeList){
				//System.out.println(" I am drawing these shapes "+shape);
					String[] Splits = shape.split(",");
					char type = Splits[1].charAt(0);
					int v1 = Integer.parseInt(Splits[2]);
					int v2 = Integer.parseInt(Splits[3]);
					int v3 = Integer.parseInt(Splits[4]);
					int v4 = Integer.parseInt(Splits[5]);

					System.out.println(" Type " + type + " Vars "
							+ v1 + " ," + v2 + " ," + v3 + " ,"
	
							+ v4 + " ");
					switch (type) {
					case 'R':
						g.drawRect(v1, v2, v3, v4);
						break;
					case 'O':
						g.drawOval(v1, v2, v3, v4);
						break;
					case 'L':
						g.drawLine(v1, v2, v3, v4);
						break;
					case 'P':
						g.drawLine(v1, v2, v3, v4);
						break;
					case 'C':
						g.setColor(new Color(v1));
						break;
					case 'X':
						Color tem = g.getColor();
						g.setColor(Color.white);
						g.fillRect(0, 0, 1000, 700);
						g.setColor(tem);
						break;
					case 'F':
						Color t = g.getColor();
						g.fillRect(0, 0, 1000, 700);
						g.setColor(t);
						break; 
					case 'E':
						tem = g.getColor();
						Graphics2D g2 = (Graphics2D) g;
						g2.setColor(Color.white);
						int v5 = Integer.parseInt(Splits[6]);
						g2.setStroke(new BasicStroke(v5));
						g2.drawLine(v1,v2,v3,v4);
						g2.setStroke(new BasicStroke(1));
						g.setColor(tem);
						break;
					   	
					}//switch 
			}
		  	   
		  }
    }//class
    public ArrayList<String> shapeList = new ArrayList<String>();
		MouseAdapter ma1;
    BufferedReader in;
    PrintWriter out;
    JFrame chat_frame = new JFrame("DISTRIBUTED WHITEBOARD - CHAT APPLICATION"); // Title of JFrame - DISTRIBUTED WHITEBOARD - CHAT APPLICATION
    JTextField text_field1 = new JTextField(50); // declare JTextField of size 50
    JTextArea text_area = new JTextArea(10, 50); // declare JTextArea with 10 rows and 50 columns
    Graphics g;
		Color drawColor = Color.black; // default starting color is BLACK
		int prevX, prevY, x, y;
		int brushsize;
		boolean isActive = false;
		int mode;
		int portNoDraw = 6789;
		JButton red_button, blue_button, yellow_button, black_button,
				white_button, green_button, pink_button, cyan_button, choose_color,
				fill, tiny, big, eraser_button, clear, freehand,
				line_button, rectangle_button, circle_button, triangle_button,
				square_button, token_button;
		MKPanel panel;
		String line;
		 
    public ChatDrawClient() {
    hostName ="zeno.hbg.psu.edu";
    //try{
        // Disable editing property of JTextField
        text_field1.setEditable(false);
        // Disable editing property of JTextArea
        text_area.setEditable(false);
        // Add the JTextField to the bottom - South
        chat_frame.getContentPane().add(text_field1, "South");
        // Add JScrollPane to the JTextArea
        chat_frame.getContentPane().add(new JScrollPane(text_area), "Center");
       
        // Add ActionListener to the JTextField - press enter to send the message to the JTextArea
        text_field1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(text_field1.getText()); 
                text_field1.setText(""); // Empty the JTextField 
            }
        });
        //String ServerInput = ""        
        
        	JFrame frame = new JFrame("DISTRIBUTED WHITEBOARD");
					// Canvas Panel
					panel = new MKPanel();
					// Another JPanel for holding all the tools on the side
					JPanel sidepanel = new JPanel();

					// Red Color
					red_button = new JButton("");
					red_button.setForeground(Color.BLACK);
					red_button.setBackground(Color.RED);
					red_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(red_button);
					// Blue Color
					blue_button = new JButton("");
					blue_button.setForeground(Color.BLUE);
					blue_button.setBackground(Color.BLUE);
					blue_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(blue_button);
					// Yellow Color
					yellow_button = new JButton("");
					yellow_button.setForeground(Color.YELLOW);
					yellow_button.setBackground(Color.YELLOW);
					yellow_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(yellow_button);
					// Green Color
					green_button = new JButton("");
					green_button.setForeground(Color.GREEN);
					green_button.setBackground(Color.GREEN);
					green_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(green_button);
					// Black Color
					black_button = new JButton("");
					black_button.setForeground(Color.BLACK);
					black_button.setBackground(Color.BLACK);
					black_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(black_button);
					// Pink Color
					pink_button = new JButton("");
					pink_button.setForeground(Color.PINK);
					pink_button.setBackground(Color.PINK);
					pink_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(pink_button);
					// White Color
					white_button = new JButton("");
					white_button.setForeground(Color.WHITE);
					white_button.setBackground(Color.WHITE);
					white_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(white_button);
					// Cyan Color
					cyan_button = new JButton("");
					cyan_button.setForeground(Color.CYAN);
					cyan_button.setBackground(Color.CYAN);
					cyan_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(cyan_button);
					// Choose Color
					choose_color = new JButton("PALETTE");
					choose_color.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(choose_color);

					// Fill Tool
					Icon fill_image = new ImageIcon("fill.png");
					fill = new JButton(fill_image);
					fill.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(fill);

					// Eraser Tool
					Icon eraser_image = new ImageIcon("eraser.png");
					eraser_button = new JButton(eraser_image);
					eraser_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(eraser_button);

					// Tiny Eraser
					Icon tiny_image = new ImageIcon("brush1.png");
					tiny = new JButton(tiny_image);
					tiny.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(tiny);

					// Big Eraser
					Icon big_image = new ImageIcon("brush2.png");
					big = new JButton(big_image);
					big.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(big);

					// Free-hand TOOL
					Icon pencil_image = new ImageIcon("pencil.png");
					freehand = new JButton(pencil_image);
					freehand.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(freehand);

					// Clear Button
					Icon clear_image = new ImageIcon("clear.gif");
					clear = new JButton(clear_image);
					clear.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(clear);

					// Line Button
					line_button = new JButton("LINE");
					line_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(line_button);

					// Circle Button
					circle_button = new JButton("CIRCLE");
					circle_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(circle_button);

					// Square Button
					square_button = new JButton("SQUARE");
					square_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(square_button);

					// Rectangle Button
					rectangle_button = new JButton("RECTANGLE");
					rectangle_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(rectangle_button);

					// Triangle Button
					triangle_button = new JButton("TRIANGLE");
					triangle_button.setPreferredSize(new Dimension(120, 25));
					sidepanel.add(triangle_button);

					// Token Button
					token_button = new JButton("TOKEN");
					token_button.setPreferredSize(new Dimension(120, 25));
					//sidepanel.add(token_button);

					// Set the preferred size of the SidePanel to 150,200
					sidepanel.setPreferredSize(new Dimension(150, 200));
					// Add the SidePanel to WEST in the main Frame
					frame.add(sidepanel, BorderLayout.WEST);

					// Create a Menu - File
					JMenu file = new JMenu("File");
					// Create the main Menu Bar
					JMenuBar menubar = new JMenuBar();
					// Add the Menu to the Menu Bar
					menubar.add(file);

					// Menu Items for Menu File
					JMenuItem quit = new JMenuItem("Quit");
					quit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ev) {

							int response = JOptionPane.showConfirmDialog(null,
									"Are you sure you want to exit?", "EXIT!!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (response == JOptionPane.NO_OPTION) {
							} else if (response == JOptionPane.YES_OPTION) {
								System.exit(0);
							}
						}
					});
					file.add(quit);

					// Clear IMPLEMENTATION
					clear.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
								g = panel.getGraphics();
								g.setColor(Color.white);
								g.fillRect(0, 0, 1000, 700);

								writeToServer("X,0,0,0,0,", out,
										in);
							
						}
					});

					// Tiny Eraser - SIZE 1 Implementation
					tiny.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							brushsize = 15;
						}
					});

					// Big Eraser - SIZE 2 Implementation
					big.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							brushsize = 50;
						}
					});

					// Action Listener for the JButton- colors
					// Blue Implementation
					blue_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ev) {
								drawColor = Color.blue;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
						}
					});

					// Green Implementation
					green_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ev) {
								drawColor = Color.green;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
							
						}
					});

					// White Implementation
					white_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								drawColor = Color.white;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
							
						}
					});

					// Pink Implementation
					pink_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ev) {
								drawColor = Color.pink;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
							
						}
					});

					// Black Implementation
					black_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ev) {
								drawColor = Color.black;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
						}
					});

					// Red Implementaion
					red_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ev) {
								drawColor = Color.red;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
						}
					});

					// Yellow Implementation
					yellow_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ev) {
								drawColor = Color.yellow;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
						}
					});

					// Cyan Implementation
					cyan_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ev) {
								drawColor = Color.cyan;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
							
						}
					});

					// Color Palette Implementation
					choose_color.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								Color c = JColorChooser.showDialog(null,
										"Color Palette", Color.white);
								drawColor = c;
								writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
										out, in);
							
						}
					});

					// Circle Implementation mode 2

					circle_button.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e) {
						}
					});

					circle_button.addMouseListener(new MouseAdapter() {

						public void mousePressed(MouseEvent ev) {
						}

						public void mouseClicked(MouseEvent e) {
							isActive = true;
							mode = 2;
						}

						public void mouseReleased(MouseEvent e) {
							isActive = true;

							mode = 2;
						}

						public void mouseEntered(MouseEvent e) {
						}

						public void mouseExited(MouseEvent e) {
						}

					});

					// Square Implementation - Mode :3

					square_button.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e) {
						}
					});

					square_button.addMouseListener(new MouseAdapter() {

						public void mousePressed(MouseEvent ev) {
						}

						public void mouseClicked(MouseEvent e) {
							isActive = true;
							mode = 3;
						}

						public void mouseReleased(MouseEvent e) {
							isActive = true;
							mode = 3;
						}

						public void mouseEntered(MouseEvent e) {
						}

						public void mouseExited(MouseEvent e) {
						}

					});

					// RECTANGLE Implementation : Mode - 4

					rectangle_button.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e) {
						}
					});

					rectangle_button.addMouseListener(new MouseAdapter() {

						public void mousePressed(MouseEvent ev) {
						}

						public void mouseClicked(MouseEvent e) {
							isActive = true;
							mode = 4;
						}

						public void mouseReleased(MouseEvent e) {
							isActive = true;
							mode = 4;
						}

						public void mouseEntered(MouseEvent e) {
						}

						public void mouseExited(MouseEvent e) {
						}

					});

					// TRIANGLE Implementation : Mode -5

					triangle_button.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e) {
						}
					});

					triangle_button.addMouseListener(new MouseAdapter() {

						public void mousePressed(MouseEvent ev) {
						}

						public void mouseClicked(MouseEvent e) {
							isActive = true;
							mode = 5;
						}

						public void mouseReleased(MouseEvent e) {
							isActive = true;
							mode = 5;
						}

						public void mouseEntered(MouseEvent e) {
						}

						public void mouseExited(MouseEvent e) {
						}

					});

					// LINE IMPLEMENTATION : Mode -1
					line_button.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e) {

						}
					});

					line_button.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent ev) {
						}

						public void mouseClicked(MouseEvent e) {
							isActive = true;
							mode = 1;
						}

						public void mouseReleased(MouseEvent e) {
							isActive = true;
							mode = 1;
						}

						public void mouseEntered(MouseEvent e) {

						}

						public void mouseExited(MouseEvent e) {

						}

					});

					// ERASER Implementation : Mode -10
					eraser_button.addMouseListener(new MouseAdapter() {

						public void mousePressed(MouseEvent ev) {
						}

						public void mouseClicked(MouseEvent e) {
							isActive = true;
							mode = 10;
						}

						public void mouseReleased(MouseEvent e) {
							isActive = true;
							mode = 10;
						}

						public void mouseEntered(MouseEvent e) {
						}

						public void mouseExited(MouseEvent e) {
						}

					});

					eraser_button.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e) {
						}
					});

					// PANEL Implementation

					panel.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e) {
							if (isActive) {
								x = e.getX();
								y = e.getY();

								if (mode == 6) // Pencil implemented
								{
									g = panel.getGraphics();
									g.setColor(drawColor);
									g.drawLine(prevX, prevY, x, y);
									writeToServer("P," + prevX	+ "," + prevY + "," + x + "," + y + ",",out, in);
									prevX = x;
									prevY = y;
								} else if (mode == 10) // Eraser was selected
								{
									Graphics2D g2 = (Graphics2D) g;
									g = panel.getGraphics();
									g2.setColor(Color.white);

									g2.setStroke(new BasicStroke(brushsize));
									g2.drawLine(prevX, prevY, x, y);
							
									writeToServer("E," + prevX	+ "," + prevY + "," + x + "," + y + ","+brushsize+",",out, in);
									prevX = x;
									prevY = y;
								}

							}
						}
					});

					panel.addMouseListener(new MouseAdapter() {

						public void mousePressed(MouseEvent ev) {
							if (isActive) {
								prevX = ev.getX();
								prevY = ev.getY();
							}
						}

						public void mouseClicked(MouseEvent e) {
						}

						public void mouseReleased(MouseEvent e) {
							if (isActive) {
								g = panel.getGraphics();
								g.setColor(drawColor);
								int mx, my, dx, dy;

								if (mode == 1) // Line
								{
									g.drawLine(prevX, prevY, x, y);
									writeToServer("L," + prevX + "," + prevY + ","
											+ x + "," + y + ",", out,
											in);

								}

								else if (mode == 2) // Circle
								{
									mx = Math.min(x, prevX);
									my = Math.min(y, prevY);
									dx = Math.abs(x - prevX);
									dy = Math.abs(y - prevY);
									g.drawOval(mx, my, dx, dy);
									writeToServer("O," + mx + "," + my + "," + dx
											+ "," + dy + ",", out, in);
								}

								else if (mode == 3) // Square
								{
									mx = Math.min(x, prevX);
									my = Math.min(y, prevY);
									dx = Math.max(Math.abs(x - prevX),
											Math.abs(y - prevY));
									g.drawRect(mx, my, dx, dx);
									writeToServer("R," + mx + "," + my + "," + dx
											+ "," + dx + ",", out, in);

								}

								else if (mode == 4) // Rectangle
								{
									mx = Math.min(x, prevX);
									my = Math.min(y, prevY);
									dx = Math.abs(x - prevX);
									dy = Math.abs(y - prevY);
									g.drawRect(mx, my, dx, dy);
									writeToServer("R," + mx + "," + my + "," + dx
											+ "," + dy + ",", out, in);

								}

								else if (mode == 5) // Triangle
								{
									g.drawLine(prevX, y, x, y);
									g.drawLine(prevX, y, (int) ((x + prevX) / 2), prevY);
									g.drawLine((int) ((x + prevX) / 2), prevY, x, y);
									writeToServer("L," + prevX + "," + y + "," + x
											+ "," + y + ",", out, in);
									writeToServer("L," + prevX + "," + y + ","
											+ (int) ((x + prevX) / 2) + "," + prevY
											+ ",", out, in);
									writeToServer("L," + (int) ((x + prevX) / 2)
											+ "," + prevY + "," + x + "," + y + ",",
											out, in);

								}
								else if (mode == 6 || mode == 10) // 
								
								{
									writeToServer("L," + x + "," + x + "," + x	+ "," + x + ",", out, in);
									
								} 

								else
									System.out.println("Wrong mode!");
							}
							isActive = false;
						}

						public void mouseEntered(MouseEvent e) {
						}

						public void mouseExited(MouseEvent e) {
						}

					});

					// FREE HAND IMPLEMENTATION
					freehand.addMouseListener(new MouseAdapter() {

						public void mousePressed(MouseEvent ev) {
						}

						public void mouseClicked(MouseEvent e) {
							isActive = true;
							mode = 6;
						}

						public void mouseReleased(MouseEvent e) {
							isActive = true;
							mode = 6;
						}

						public void mouseEntered(MouseEvent e) {
						}

						public void mouseExited(MouseEvent e) {
						}

					});

					freehand.addMouseMotionListener(new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent e) {
						}
					});

					// FILL Implementation
					fill.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							g = panel.getGraphics();
							g.setColor(drawColor);
							g.fillRect(0, 0, 1000, 700);
							writeToServer("F,0,0,0,0,", out,
									in);
							drawColor = Color.black; // setting it back to default
														// color - BLACK
							writeToServer("C," + Color.black.getRGB()
									+ ",0,0,0,", out, in);
						}
					});

					// Add the MenuBar to the Frame window
					frame.setJMenuBar(menubar);
					menubar.setVisible(true);

					// Set the default background of the Panel to WHITE
					panel.setBackground(Color.white);
					// Add the Panel to the Frame window
					frame.add(panel);
					frame.setSize(1000, 700); // Set the size of the Frame to 1000,700
					frame.setVisible(true);
					frame.setTitle("Distributed Whiteboard"); // Displays the title of
																// the Frame as
																// 'Distributed
																// Whiteboard'
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				/*} catch (UnknownHostException e) {
					System.err.println("Don't know about host " + hostName);
					System.exit(1);
				} catch (IOException e) {
					System.err.println("Couldn't get I/O for the connection to "
							+ hostName);
					System.exit(1);
				} */       
    }

    private String getClientName() { // function to get the Client name
        return JOptionPane.showInputDialog(chat_frame,"Please enter your name: ","Get Client Name",JOptionPane.PLAIN_MESSAGE);
    }

    private void run() throws IOException {
        //String serverAddress = getServerAddress(); // gets the IP Address of the Server from the user
        Socket socket = new Socket(hostName, 6008); // port_number = 6008 , default server = zeno
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server according to the protocol
        while (true) {
            line = in.readLine();
            if (line.startsWith("UI_MESSAGE")) { // Add the message to the JTextArea
                System.out.println("Obtained " + line);
                
								System.out.println("Entered CTD");
								
								String[] Splits = line.split(",");
								char type = Splits[1].charAt(0);
								System.out.println(" Type is "+ type);
								if(type!='X' || type!='F')
									shapeList.add(line);
								else
									shapeList = new ArrayList<String>();
									
								if(type!='P' && type!='E')	
									panel.repaint();
				
								System.out.println("Done Drawing");
		
                }
            else if (line.startsWith("GET_NAME")) { // Does not accept two clients with same name - case sensitive
                out.println(getClientName());
            } else if (line.startsWith("ACCEPTED")) { //Once the name has been accepted, enable the JTextField so that the user can type
                text_field1.setEditable(true);
            } else if (line.startsWith("CHAT_MESSAGE")) { // Add the message to the JTextArea
                text_area.append(line.substring(13) + "\n");
            } 							
        }
    }
    
    
	public void writeToServer(String set, PrintWriter out,
			BufferedReader in) {

			System.out.println("Writing to Server :"+"UI_MESSAGE,"+ set + "!");
			out.println("UI_MESSAGE,"+ set + "!");
			//String ServerInput = in.readLine();
			//System.out.println("server sent: " + ServerInput);
	}
}





//--------------------------------------------------------------------------------------------------------------------------------------------------------//








