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

public class ChatClient {
  
      // Main method
    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient(); 
        client.chat_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting the default close operation of the JFrame
        client.chat_frame.setVisible(true); // Set the JFrame visible
				client.chat_frame.setSize(600,500); // Set the JFrame size to 600,500
        client.run();
    }

    BufferedReader in;
    PrintWriter out;
    JFrame chat_frame = new JFrame("DISTRIBUTED WHITEBOARD - CHAT APPLICATION"); // Title of JFrame - DISTRIBUTED WHITEBOARD - CHAT APPLICATION
    JTextField text_field1 = new JTextField(50); // declare JTextField of size 50
    JTextArea text_area = new JTextArea(10, 50); // declare JTextArea with 10 rows and 50 columns
    
    public ChatClient() {
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
    }

    private String getClientName() { // function to get the Client name
        return JOptionPane.showInputDialog(chat_frame,"Please enter your name: ","Get Client Name",JOptionPane.PLAIN_MESSAGE);
    }

    private void run() throws IOException {
        //String serverAddress = getServerAddress(); // gets the IP Address of the Server from the user
        Socket socket = new Socket("zeno", 6008); // port_number = 6008 , default server = zeno
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server according to the protocol
        while (true) {
            String line = in.readLine();
            if (line.startsWith("GET_NAME")) { // Does not accept two clients with same name - case sensitive
                out.println(getClientName());
            } else if (line.startsWith("ACCEPTED")) { //Once the name has been accepted, enable the JTextField so that the user can type
                text_field1.setEditable(true);
            } else if (line.startsWith("CHAT_MESSAGE")) { // Add the message to the JTextArea
                text_area.append(line.substring(13) + "\n");
            } 							
        }
    }
}
