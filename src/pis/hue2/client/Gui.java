package pis.hue2.client;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Gui {

	private JFrame frmClient;
	private JTextField tF_msg;
	private JTextField tF_serverip;
	private JTextField tF_port;
	private JTextField tF_username;
	
	private JList<String> list;
	private JTextArea textArea;

	public JList<String> getList() {
		return list;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
		frmClient.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.setTitle("Client by Johannes Mahn & Yannick Dreher");
		frmClient.setResizable(false);
		frmClient.getContentPane().setBackground(Color.WHITE);
		frmClient.setBounds(100, 100, 773, 653);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.getContentPane().setLayout(null);
		
		tF_msg = new JTextField();
		tF_msg.setBounds(0, 546, 601, 43);
		frmClient.getContentPane().add(tF_msg);
		tF_msg.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setBounds(0, 96, 562, 434);
		frmClient.getContentPane().add(textArea);
		
		tF_serverip = new JTextField();
		tF_serverip.setBounds(90, 0, 336, 29);
		frmClient.getContentPane().add(tF_serverip);
		tF_serverip.setColumns(10);
		
		JButton btnSend = new JButton("Senden");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSend.setBounds(618, 558, 131, 31);
		frmClient.getContentPane().add(btnSend);
		
		tF_port = new JTextField();
		tF_port.setBounds(583, 0, 166, 29);
		frmClient.getContentPane().add(tF_port);
		tF_port.setColumns(10);
		
		tF_username = new JTextField();
		tF_username.setBounds(100, 48, 182, 29);
		frmClient.getContentPane().add(tF_username);
		tF_username.setColumns(10);
		
		JTextPane txtpnServerip = new JTextPane();
		txtpnServerip.setText("ServerIP:");
		txtpnServerip.setBounds(0, 0, 84, 29);
		frmClient.getContentPane().add(txtpnServerip);
		
		JTextPane txtpnServerport = new JTextPane();
		txtpnServerport.setText("ServerPORT:");
		txtpnServerport.setBounds(445, 0, 113, 29);
		frmClient.getContentPane().add(txtpnServerport);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setText("Username:");
		txtpnUsername.setBounds(0, 48, 97, 29);
		frmClient.getContentPane().add(txtpnUsername);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaunchClient.getInstance().connect(tF_serverip.getText(), Integer.parseInt(tF_port.getText()), tF_username.getText());
			}
		});
		btnConnect.setBounds(331, 47, 131, 31);
		frmClient.getContentPane().add(btnConnect);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDisconnect.setBounds(470, 47, 131, 31);
		frmClient.getContentPane().add(btnDisconnect);
		
		list = new JList<String>();
		list.setBackground(SystemColor.controlHighlight);
		list.setBounds(561, 123, 188, 407);
		frmClient.getContentPane().add(list);
		
		JTextPane txtpnAngemeldeteNutzer = new JTextPane();
		txtpnAngemeldeteNutzer.setText("Angemeldete Nutzer");
		txtpnAngemeldeteNutzer.setBounds(561, 96, 188, 29);
		frmClient.getContentPane().add(txtpnAngemeldeteNutzer);
	}
}
