package pis.hue2.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import pis.hue2.common.PacketType;

/**
 * Stellt die User-GUI zur verfügung
 * 
 * @author Johannes Mahn, Yannick Dreher
 */
public class Gui {

	private JFrame frmClient;
	private JTextField tF_msg;
	private JTextField tF_serverip;
	private JTextField tF_port;
	private JTextField tF_username;
	private JList<String> list;
	private JTextArea txtrWillkommen;

	/**
	 * Gib die User Listeneintraege zurück
	 * 
	 * @return list
	 */
	public JList<String> getList() {
		return list;
	}

	/**
	 * Gibt den Ihnalt des TextArea's aus
	 * @return String
	 */
	public JTextArea getTextArea() {
		return txtrWillkommen;
	}

	/**
	 * Konstruktor der GUI
	 */
	public Gui() {
		initialize();
		frmClient.setVisible(true);
	}

	/**
	 * Initializiert die GUI
	 */
	private void initialize() {
		//Das Frame
		frmClient = new JFrame();
		frmClient.setTitle("Client by Johannes Mahn & Yannick Dreher");
		frmClient.setResizable(false);
		frmClient.getContentPane().setBackground(Color.WHITE);
		frmClient.setBounds(100, 100, 773, 653);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.getContentPane().setLayout(null);
		//TextField zu sendene Nachricht
		tF_msg = new JTextField();
		tF_msg.setBounds(0, 546, 601, 43);
		frmClient.getContentPane().add(tF_msg);
		tF_msg.setColumns(10);
		//TextArea Die gesendetet Meldungen und Nachrichten
		txtrWillkommen = new JTextArea();
		txtrWillkommen.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		txtrWillkommen.setLineWrap(true);
		txtrWillkommen.setText("Willkommen\r\n");
		txtrWillkommen.setEditable(false);
		txtrWillkommen.setBackground(SystemColor.controlHighlight);
		txtrWillkommen.setBounds(0, 96, 528, 434);
		frmClient.getContentPane().add(txtrWillkommen);
		//TextField die Serverip, standart ist localhost
		tF_serverip = new JTextField();
		tF_serverip.setText("localhost");
		tF_serverip.setBounds(90, 0, 336, 29);
		frmClient.getContentPane().add(tF_serverip);
		tF_serverip.setColumns(10);
		//TextField der Serverport, standart ist 25565
		tF_port = new JTextField();
		tF_port.setText("25565");
		tF_port.setBounds(583, 0, 166, 29);
		frmClient.getContentPane().add(tF_port);
		tF_port.setColumns(10);
		//TextField Der Username
		tF_username = new JTextField();
		tF_username.setBounds(100, 48, 182, 29);
		frmClient.getContentPane().add(tF_username);
		tF_username.setColumns(10);
		//List aktuelle Nutzerliste
		list = new JList<String>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 10));
		list.setValueIsAdjusting(true);
		list.setBackground(SystemColor.controlHighlight);
		list.setBounds(561, 123, 188, 407);
		frmClient.getContentPane().add(list);
		//ScrollPane Scrollbar zum scollen der Nachrichten
		JScrollPane scrollPane = new JScrollPane(txtrWillkommen);
		scrollPane.setBounds(0, 96, 555, 434);
		frmClient.getContentPane().add(scrollPane);
		//Textpane
		JTextPane txtpnServerip = new JTextPane();
		txtpnServerip.setEditable(false);
		txtpnServerip.setText("ServerIP:");
		txtpnServerip.setBounds(0, 0, 84, 29);
		frmClient.getContentPane().add(txtpnServerip);
		//Textpane
		JTextPane txtpnServerport = new JTextPane();
		txtpnServerport.setEditable(false);
		txtpnServerport.setText("ServerPORT:");
		txtpnServerport.setBounds(445, 0, 113, 29);
		frmClient.getContentPane().add(txtpnServerport);
		//Textpane
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setEditable(false);
		txtpnUsername.setText("Username:");
		txtpnUsername.setBounds(0, 48, 97, 29);
		frmClient.getContentPane().add(txtpnUsername);
		//Textpane
		JTextPane txtpnAngemeldeteNutzer = new JTextPane();
		txtpnAngemeldeteNutzer.setEditable(false);
		txtpnAngemeldeteNutzer.setText("Angemeldete Nutzer");
		txtpnAngemeldeteNutzer.setBounds(561, 96, 188, 29);
		frmClient.getContentPane().add(txtpnAngemeldeteNutzer);
		//Button Connect, initialisiert den Connectvorgang
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaunchClient.getInstance().connect(tF_serverip.getText(), Integer.parseInt(tF_port.getText()),
						tF_username.getText());
			}
		});
		btnConnect.setBounds(331, 47, 131, 31);
		frmClient.getContentPane().add(btnConnect);
		//Button Disconnect, beendet die Verbindung
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaunchClient.getInstance().sendPacktet(PacketType.disconnect, "");
			}
		});
		btnDisconnect.setBounds(470, 47, 131, 31);
		frmClient.getContentPane().add(btnDisconnect);
		//Button Sendet, sendet falls eine Connection besteht die nachicht aus dem zu denden Feld
		JButton btnSend = new JButton("Senden");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LaunchClient.getInstance().sendPacktet(PacketType.message, tF_msg.getText());
				tF_msg.setText("");
			}
		});
		btnSend.setBounds(618, 558, 131, 31);
		frmClient.getContentPane().add(btnSend);
	}
}
