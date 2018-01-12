package pis.hue2.client;

import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.PacketHandler;
import pis.hue2.common.PacketManager;
import pis.hue2.common.PacketType;

/**
 * Startet die Clientconnection und die Gui und stellt den PacketHandler für den
 * Client
 * 
 * @author Johannes Mahn, Yannick Dreher
 *
 */
public class LaunchClient {

	private static LaunchClient instance;
	private Gui window;
	private ServerConnection connection = null;
	private Ausgabe guiAusgabe;
	private PacketManager packetManager = new PacketManager();

	/**
	 * Konstuktor, staten der Gui
	 */
	public LaunchClient() {
		initPacketHandler();
		window = new Gui();
		guiAusgabe = new Ausgabe(window);
	}

	/**
	 * Packethandler, der für die Handhabung der PacketType's zuständig ist
	 */
	private void initPacketHandler() {
		packetManager.registerPacketHandler(PacketType.connect, new PacketHandler() {

			@Override
			public boolean handlePacket(Connection con, String packet) {
				guiAusgabe.zeigeNachricht("Connected!");
				connection.setConnected();
				return true;
			}
		});
		packetManager.registerPacketHandler(PacketType.message, new PacketHandler() {

			@Override
			public boolean handlePacket(Connection con, String packet) {
				guiAusgabe.zeigeNachricht(packet);
				return true;
			}
		});
		packetManager.registerPacketHandler(PacketType.namelist, new PacketHandler() {

			@Override
			public boolean handlePacket(Connection con, String packet) {
				guiAusgabe.zeigeListe(packet.split(":"));
				return true;
			}
		});
		packetManager.registerPacketHandler(PacketType.refused, new PacketHandler() {

			@Override
			public boolean handlePacket(Connection con, String packet) {
				guiAusgabe.zeigeNachricht("Kicked: " + packet);
				return false;
			}
		});
		packetManager.registerPacketHandler(PacketType.disconnect, new PacketHandler() {

			@Override
			public boolean handlePacket(Connection con, String packet) {
				guiAusgabe.zeigeNachricht("Disconnect: " + packet);
				return false;
			}
		});
	}

	/**
	 * Sendet Falls der Client zum Server connectet ist, eine Nachricht
	 * 
	 * @param type
	 *            PacketType
	 * @param message
	 *            Nachricht
	 */
	public void sendPacktet(PacketType type, String message) {
		if (isConnected())
			connection.sendPacket(type, message);
	}

	/**
	 * testet ob der Client mit dem Server verbunden ist
	 * 
	 * @return boolean
	 */
	public boolean isConnected() {
		return connection != null && connection.isConnected();
	}

	/**
	 * Gibt die Instanz zurück
	 * 
	 * @return instance
	 */
	public static LaunchClient getInstance() {
		return instance;
	}

	/**
	 * Schnittstelle zur GUI
	 * 
	 */
	public Ausgabe getGuiAusgabe() {
		return guiAusgabe;
	}

	private boolean connecting = false;

	/**
	 * Startet die Connection zum Server in einem neuen Thread
	 * 
	 * @param ip
	 *            ServerIP
	 * @param port
	 *            ServerPORT
	 * @param username
	 *            username
	 */
	public void connect(String ip, int port, String username) {
		if (connecting)
			return;
		connecting = true;
		Thread t = new Thread(() -> {
			if (isConnected()) {
				guiAusgabe.zeigeNachricht("Already connected!");
				connecting = false;
				return;
			}
			try {
				connection = new ServerConnection(new Socket(ip, port), packetManager);
				connection.sendPacket(PacketType.connect, username);
			} catch (Exception ex) {
				guiAusgabe.zeigeNachricht("Error connecting to server!");
			}
			connecting = false;
		});
		t.start();
	}

	/**
	 * startet den Client
	 */
	public static void main(String[] args) {
		instance = new LaunchClient();
	}

}
