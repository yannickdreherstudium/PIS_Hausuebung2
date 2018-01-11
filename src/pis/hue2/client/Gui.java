package pis.hue2.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Color;
import java.awt.SystemColor;

public class Gui {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 773, 653);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 546, 601, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setBounds(0, 96, 562, 434);
		frame.getContentPane().add(textArea);
		
		textField_1 = new JTextField();
		textField_1.setBounds(90, 0, 336, 29);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Senden");
		btnNewButton.setBounds(618, 558, 131, 31);
		frame.getContentPane().add(btnNewButton);
		
		textField_2 = new JTextField();
		textField_2.setBounds(583, 0, 166, 29);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(100, 48, 182, 29);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JTextPane txtpnServerip = new JTextPane();
		txtpnServerip.setText("ServerIP:");
		txtpnServerip.setBounds(0, 0, 84, 29);
		frame.getContentPane().add(txtpnServerip);
		
		JTextPane txtpnServerport = new JTextPane();
		txtpnServerport.setText("ServerPORT:");
		txtpnServerport.setBounds(445, 0, 113, 29);
		frame.getContentPane().add(txtpnServerport);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setText("Username:");
		txtpnUsername.setBounds(0, 48, 97, 29);
		frame.getContentPane().add(txtpnUsername);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(331, 47, 131, 31);
		frame.getContentPane().add(btnConnect);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(470, 47, 131, 31);
		frame.getContentPane().add(btnDisconnect);
		
		JList list = new JList();
		list.setBackground(SystemColor.controlHighlight);
		list.setBounds(561, 123, 188, 404);
		frame.getContentPane().add(list);
		
		JTextPane txtpnAngemeldeteNutzer = new JTextPane();
		txtpnAngemeldeteNutzer.setText("Angemeldete Nutzer");
		txtpnAngemeldeteNutzer.setBounds(561, 96, 188, 29);
		frame.getContentPane().add(txtpnAngemeldeteNutzer);
	}
}
