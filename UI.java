/* Distributed Whiteboard User Interface - Coordinator Implementation
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
	static Color drawColor = Color.black; // default starting color is BLACK
	static int prevX, prevY, x, y;
	static int brushsize;
	static boolean isActive = false;
	static boolean listen = true;
	static boolean token = true;
	static int mode;
	static int portNo = 6789;
	static String record = "!";
	static int rptr = 1;
	static String username;
	static JButton red_button, blue_button, yellow_button, black_button,
			white_button, green_button, pink_button, cyan_button, choose_color,
			fill, tiny, big, eraser_button, textbox, clear, freehand,
			line_button, rectangle_button, circle_button, triangle_button,
			square_button, token_button;
	static JPanel panel;
	static String hostName = "zeno";
	static Socket echoSocket;
	static PrintWriter outToServer;
	static BufferedReader inFromServer;
	static BufferedReader stdIn;

	public static void main(String args[]) {
		try {
			echoSocket = new Socket(hostName, portNo);
			outToServer = new PrintWriter(echoSocket.getOutputStream(), true);
			inFromServer = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Connected Server");

			// Main JFrame
			JFrame frame = new JFrame("DISTRIBUTED WHITEBOARD");
			// Canvas Panel
			panel = new JPanel();
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

			// Text-Box TOOL
			Icon textbox_image = new ImageIcon("textbox.png");
			textbox = new JButton(textbox_image);
			textbox.setPreferredSize(new Dimension(120, 25));
			textbox.setEnabled(false);
			sidepanel.add(textbox);

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
			sidepanel.add(token_button);

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

			// Token IMPLEMENTATION
			token_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						// username =
						// JOptionPane.showInputDialog(null,"Please enter your name: ","Get Username",JOptionPane.PLAIN_MESSAGE);
						UI.writeToServer("T,0,0,0,0,", outToServer,
								inFromServer);
						echoSocket = new Socket(hostName, 6791);
						outToServer = new PrintWriter(echoSocket
								.getOutputStream(), true);
						inFromServer = new BufferedReader(
								new InputStreamReader(echoSocket
										.getInputStream()));
						stdIn = new BufferedReader(new InputStreamReader(
								System.in));
						System.out.println("Connected Server");
					} catch (Exception er) {
					}
				}
			});

			// TextBox IMPLEMENTATION
			textbox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTextArea textarea = new JTextArea(5, 10);
					textarea.setEditable(true);
					// textarea.setEnable(true);
					panel.add(textarea, BorderLayout.WEST);
					frame.add(panel);
					frame.setVisible(true);
				}
			});

			// Clear IMPLEMENTATION
			clear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (token) {
						g = panel.getGraphics();
						g.setColor(Color.white);
						g.fillRect(0, 0, 1000, 700);

						UI.writeToServer("X,0,0,0,0,", outToServer,
								inFromServer);
					}
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
					if (token) {
						drawColor = Color.blue;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
				}
			});

			// Green Implementation
			green_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					if (token) {
						drawColor = Color.green;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
				}
			});

			// White Implementation
			white_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (token) {
						drawColor = Color.white;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
				}
			});

			// Pink Implementation
			pink_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					if (token) {
						drawColor = Color.pink;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
				}
			});

			// Black Implementation
			black_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					if (token) {
						drawColor = Color.black;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
				}
			});

			// Red Implementaion
			red_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					if (token) {
						drawColor = Color.red;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
				}
			});

			// Yellow Implementation
			yellow_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					if (token) {
						drawColor = Color.yellow;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
				}
			});

			// Cyan Implementation
			cyan_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					if (token) {
						drawColor = Color.cyan;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
				}
			});

			// Color Palette Implementation
			choose_color.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (token) {
						Color c = JColorChooser.showDialog(null,
								"Color Palette", Color.white);
						drawColor = c;
						UI.writeToServer("C," + drawColor.getRGB() + ",0,0,0,",
								outToServer, inFromServer);
					}
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

							prevX = x;
							prevY = y;
						} else if (mode == 10) // Eraser was selected
						{
							Graphics2D g2 = (Graphics2D) g;
							g = panel.getGraphics();
							g2.setColor(Color.white);

							g2.setStroke(new BasicStroke(brushsize));
							g2.drawLine(prevX, prevY, x, y);

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
					if (isActive && token) {
						g = panel.getGraphics();
						g.setColor(drawColor);
						int mx, my, dx, dy;

						if (mode == 1) // Line
						{
							g.drawLine(prevX, prevY, x, y);
							UI.writeToServer("L," + prevX + "," + prevY + ","
									+ x + "," + y + ",", outToServer,
									inFromServer);

						}

						else if (mode == 2) // Circle
						{
							mx = Math.min(x, prevX);
							my = Math.min(y, prevY);
							dx = Math.abs(x - prevX);
							dy = Math.abs(y - prevY);
							g.drawOval(mx, my, dx, dy);
							UI.writeToServer("O," + mx + "," + my + "," + dx
									+ "," + dy + ",", outToServer, inFromServer);
						}

						else if (mode == 3) // Square
						{
							mx = Math.min(x, prevX);
							my = Math.min(y, prevY);
							dx = Math.max(Math.abs(x - prevX),
									Math.abs(y - prevY));
							g.drawRect(mx, my, dx, dx);
							UI.writeToServer("R," + mx + "," + my + "," + dx
									+ "," + dx + ",", outToServer, inFromServer);

						}

						else if (mode == 4) // Rectangle
						{
							mx = Math.min(x, prevX);
							my = Math.min(y, prevY);
							dx = Math.abs(x - prevX);
							dy = Math.abs(y - prevY);
							g.drawRect(mx, my, dx, dy);
							UI.writeToServer("R," + mx + "," + my + "," + dx
									+ "," + dy + ",", outToServer, inFromServer);

						}

						else if (mode == 5) // Triangle
						{
							g.drawLine(prevX, y, x, y);
							g.drawLine(prevX, y, (int) ((x + prevX) / 2), prevY);
							g.drawLine((int) ((x + prevX) / 2), prevY, x, y);
							UI.writeToServer("L," + prevX + "," + y + "," + x
									+ "," + y + ",", outToServer, inFromServer);
							UI.writeToServer("L," + prevX + "," + y + ","
									+ (int) ((x + prevX) / 2) + "," + prevY
									+ ",", outToServer, inFromServer);
							UI.writeToServer("L," + (int) ((x + prevX) / 2)
									+ "," + prevY + "," + x + "," + y + ",",
									outToServer, inFromServer);

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
					if (token) {
						g = panel.getGraphics();
						g.setColor(drawColor);
						g.fillRect(0, 0, 1000, 700);
						UI.writeToServer("F,0,0,0,0,", outToServer,
								inFromServer);
						drawColor = Color.black; // setting it back to default
													// color - BLACK
						UI.writeToServer("C," + Color.black.getRGB()
								+ ",0,0,0,", outToServer, inFromServer);
					}
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
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}
	}

	public static void writeToServer(String set, PrintWriter outToServer,
			BufferedReader inFromServer) {

		try {
			record = record.concat(set);
			record = record.concat("!"); // signifies end of one instruction
			outToServer.println(set + "!");
			String ServerInput = inFromServer.readLine();
			System.out.println("server sent: " + ServerInput);
			if (ServerInput.charAt(0) == 'T') {
				token = false; // Coordinator has no rights to draw/write on the
								// whiteboard as it is giving the token to the
								// pupil who requested the token

				// Disabling the buttons - Coordinator will not have the rights
				// to draw/write on the Whiteboard
				blue_button.setEnabled(false);
				red_button.setEnabled(false);
				green_button.setEnabled(false);
				black_button.setEnabled(false);
				yellow_button.setEnabled(false);
				pink_button.setEnabled(false);
				white_button.setEnabled(false);
				cyan_button.setEnabled(false);
				choose_color.setEnabled(false);

				// Disabling the drawing tools
				square_button.setEnabled(false);
				line_button.setEnabled(false);
				rectangle_button.setEnabled(false);
				circle_button.setEnabled(false);
				triangle_button.setEnabled(false);
				eraser_button.setEnabled(false);
				fill.setEnabled(false);
				tiny.setEnabled(false);
				big.setEnabled(false);
				freehand.setEnabled(false);
				clear.setEnabled(false);
				token_button.setEnabled(false);
				// Coordinator acts as a Pupil
				panel.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						try {
							g = panel.getGraphics();
							String ServerInput = "";
							String Splits[];
							int v1 = 0, v2 = 0, v3 = 0, v4 = 0;
							char type;
							int ctr = 5;
							while ((ServerInput = inFromServer.readLine()) != null) {
								// System.out.println("Co-od: " + ServerInput);
								if (ServerInput.charAt(0) == '#'
										&& ServerInput.charAt(3) != '#')
									System.exit(0);
								Splits = ServerInput.split(",");
								type = Splits[0].charAt(0);
								v1 = Integer.parseInt(Splits[1]);
								v2 = Integer.parseInt(Splits[2]);
								v3 = Integer.parseInt(Splits[3]);
								v4 = Integer.parseInt(Splits[4]);

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
								case 'Z':
									token = true;
									return;

								}

							}
						} catch (IOException ex) {
							System.err
									.println("Couldn't get I/O for the connection to "
											+ hostName);
							System.exit(1);
						}
					}
				});

			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}
	}

	public static String getRecord() {
		return record;

	}
}
