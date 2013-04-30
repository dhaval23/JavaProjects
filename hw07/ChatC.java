/**
 * Chat Client
 * Dhaval Doshi
 * CS 9053
 */
import java.net.Socket; 
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * class ChatC
 * Client class containing the client methods and fields
 *
 */
class ChatC extends JFrame implements ActionListener { 
	
	/**
	 * connectClient()
	 * Contains the socket connection string & reader,writer objects.
	 * @param hostname
	 * @param portnumber
	 */
	public void connectClient(String hostname, int portnumber)
	{
		try {
			clientSocket = new Socket(hostname, portnumber);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (Exception ex) {
			System.out.println("Connection Refused");
			System.exit(0);
		}
	}
	
	/**
	 * ChatC()
	 * Constructor that creates the GUI for Client and contains methods called when action is performed on a element
	 * @param username
	 */
	public ChatC(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		setTitle("Client - " + username);
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
					clientSocket.close();      
				} catch (IOException ex) {
					System.out.println("Could not send/receive data..");
				} catch (NullPointerException ne) {}
			}
		}
				);
	}
	
	/**
	 * main()
	 * connects to the socket and creates the GUI 
	 * @param args
	 */
	public static void main(String[] args)  {

		try {
			if(args.length==1)
				hostname = args[0];
			if(args.length==2)
			{
				hostname = args[0];
				portnumber = Integer.parseInt(args[1]);
			}
			if(args.length==3)
			{
				hostname = args[0];
				portnumber = Integer.parseInt(args[1]);
				name = args[2];
			}
		} catch (ArrayIndexOutOfBoundsException ae) {
			//Do nothing
		}
		ChatC client = new ChatC(name);
		client.connectClient(hostname, portnumber);
		client.displayChatMessages();

	}
	
	/**
	 * actionPerformed()
	 * Function called when a action is performed in any GUI element, receives message from server
	 */
	public void actionPerformed(ActionEvent e) {
		out.println(username + ": " + text1.getText());
		textArea.append("Me: " + text1.getText() + "\n");
		text1.setText("");
		text1.requestFocusInWindow();
	}
	
	/**
	 * displayChatMessages()
	 * Sends the message to the Server and also displays it to the client in textArea
	 */
	public void displayChatMessages() {
		String sendMsg;
		try {
			while ((sendMsg = in.readLine()) != null) {
				textArea.append(sendMsg + "\n");
			}
			out.close();
			in.close();
			clientSocket.close();
		} catch (Exception e) { 
		}
		System.out.println("Connection closed.");
	}

	private static String hostname="localhost" ;
	private static String name="Default Client";
	private static int portnumber=8000;
	private JTextArea textArea=new JTextArea(60,60);
	private JTextField text1=new JTextField(50);
	private JButton send=new JButton("SEND");
	private Socket clientSocket=null;
	private PrintWriter out=null; 
	private BufferedReader in=null;
	private String username;

}
