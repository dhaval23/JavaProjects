/**
 * Chat Server
 * Dhaval Doshi
 * CS 9053
 */
import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class ChatS extends JFrame implements ActionListener
{ 
	/**
	 * connectServer()
	 * Contains the socket accept string & reader,writer objects.
	 * @param hostname
	 * @param portnumber
	 */
	public void connectServer(int portnumber)
	{
		try {
			serverSocket = new ServerSocket(portnumber);
			connectionSocket = serverSocket.accept();
			out = new PrintWriter(connectionSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		} catch (Exception ex) {
			System.out.println("Connection refused.");
			System.exit(0);
		}
	}
	/**
	 * ChatS()
	 * Constructor that creates the GUI for Server and contains methods called when action is performed on a element
	 * @param user
	 */
	public ChatS(String user) {
		username = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		setTitle("Server - " + username);
		JPanel panel1=new JPanel();
		panel1.add(send,BorderLayout.EAST);
		panel1.add(text1,BorderLayout.WEST);
		textArea.setEditable(false);
		add(panel1,BorderLayout.SOUTH);
		add(textArea,BorderLayout.CENTER);
		text1.addActionListener(this);
		send.addActionListener(this);
		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					out.close();
					in.close();
					connectionSocket.close();      
				} catch (IOException ex) {
					System.out.println(ex);
				} catch (NullPointerException ne) {}
			}
		}
				);
		
	}

	/**
	 * main()
	 * accepts to the socket and creates the GUI 
	 * @param args
	 */
	public static void main(String[] args) {

		 
		try {
			if(args.length==1)
				portnumber = Integer.parseInt(args[0]);
			if(args.length==2)
			{
				portnumber = Integer.parseInt(args[0]);
				name = args[1];
			}
		} catch (ArrayIndexOutOfBoundsException ae) {
			//Do nothing
		}

		ChatS server = new ChatS(name);
		server.connectServer(portnumber);
		server.display();
	}

	/**
	 * actionPerformed()
	 * Function called when a action is performed in any GUI element, receives message from client
	 */
	public void actionPerformed(ActionEvent e) {
		out.println(username + ": " + text1.getText());
		textArea.append("Me: " + text1.getText() + "\n");
		text1.setText("");
		text1.requestFocusInWindow();
	}

	/**
	 * display()
	 * Sends the message to the client and also displays it to the server in textArea
	 */
	public void display() {
		String receivedData;
		try {
			while ((receivedData = in.readLine()) != null) {
				textArea.append(receivedData + "\n");

			}
			out.close();
			in.close();
			connectionSocket.close();
		} catch (Exception e) { 
		}
		System.out.println("Connection closed.");
	}

	private JTextArea textArea=new JTextArea(60,60);
	private JTextField text1=new JTextField(50);
	private JButton send=new JButton("SEND");
	private ServerSocket serverSocket;
	private Socket connectionSocket = null;
	private PrintWriter out; 
	private BufferedReader in;
	private String username;
	private static int portnumber=8000;
	private static String name="Default Server";

}



