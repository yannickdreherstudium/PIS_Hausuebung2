package pis.hue2.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

	public class ClientGUI extends JFrame implements ActionListener{

	    private JTextArea chatArea;
	    private JTextField chatField, nameField, serverField, portField;
	    private JButton bconnect;
	    private LaunchClient launchClient;
	    private int port;
	    private String server;

	    public static void main(String[] args){
	        ClientGUI cGUI = new ClientGUI("localhost",25565);
	        cGUI.start();
	    }

	    public ClientGUI(String server, int port) {
			this.server = server;
			this.port = port;
		}

		private void start(){

	        serverField = new JTextField(server);
	        portField = new JTextField(""+port);
	        nameField = new JTextField("");

	        chatArea = new JTextArea();
	        chatArea.setEditable(false);
	        chatField = new JTextField();
	        bconnect = new JButton("Login");
	        bconnect.addActionListener(this);


	        JPanel p1 = new JPanel((new GridLayout(4, 2)));
	        p1.add(new JLabel("Serverip:"));
	        p1.add(serverField);
	        p1.add(new JLabel("Port:"));
	        p1.add(portField);
	        p1.add(new JLabel("Username:"));
	        p1.add(nameField);
	        p1.add(bconnect);
	        add(p1,BorderLayout.NORTH);

	        JPanel p2 = new JPanel(new GridLayout(2, 1, 10, 10));
	        p2.add(chatArea);
	        p2.add(chatField);
	        add(p2,BorderLayout.CENTER);

	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(600,500);
	        setResizable(false);
	        setVisible(true);
	    }

	    /**
	     * Eventhandler für Login-, Logoutbutton und das Texteingabefeld
	     *
	     * @param e
	     */
	    public void actionPerformed(ActionEvent e){
//	        Object object = e.getSource();
//
//	        if(object == logoutBtn){
////	            launchClient.sendMsg(new Message(Message.OFFLINE, ""));
//	            launchClent.sendPacket(d)
//	            loginBtn.setEnabled(true);
//	            logoutBtn.setEnabled(false);
//	            connected = false;
//	            return;
//	        }
//
//	        if(connected){
//	            launchClient.sendMsg(new Message(Message.MESSAGE, chatField.getText()));
//	            chatField.setText("");
//	            return;
//	        }
//
//	        if(object == loginBtn){
//	            String username = usernameTF.getText();
//	            String server = serverTF.getText();
//	            int port = Integer.parseInt(portTF.getText());
//
//	            launchClient = new LaunchClient(server, port, username, this);
//	            if(!launchClient.start()) return;
//	            connected = true;
//
//	            loginBtn.setEnabled(false);
//	            logoutBtn.setEnabled(true);
//
//	            serverTF.setEditable(false);
//	            portTF.setEditable(false);
//	            usernameTF.setEditable(false);
//
//	            chatField.addActionListener(this);
//	        }
	    }

	    public void systemMsg (String msg){
	        chatArea.append(msg);
	    }

	}

