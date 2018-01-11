package pis.hue2.client;

import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.PacketHandler;
import pis.hue2.common.PacketManager;
import pis.hue2.common.PacketType;

public class LaunchClient {

	private static LaunchClient instance;
	private Gui window;
	private ServerConnection connection = null;
	private Ausgabe guiAusgabe;
	private PacketManager packetManager = new PacketManager();

	public LaunchClient(){
		initPacketHandler();
		window = new Gui();
		guiAusgabe = new Ausgabe(window);
	}

	private void initPacketHandler(){
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
	
	public void sendPacktet(PacketType type, String message){
		if(isConnected())
			connection.sendPacket(type, message);
	}

	public boolean isConnected(){
		return connection != null && connection.isConnected();
	}

	public static LaunchClient getInstance() {
		return instance;
	}

	public Ausgabe getGuiAusgabe() {
		return guiAusgabe;
	}

	public void connect(String ip, int port, String username){
		if(isConnected()){
			guiAusgabe.zeigeNachricht("Already connected!");
			return;
		}
		try{
			connection = new ServerConnection(new Socket(ip, port), packetManager);
			connection.sendPacket(PacketType.connect, username);
		}catch(Exception ex){
			guiAusgabe.zeigeNachricht("Error connecting to server!");
			return;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		instance = new LaunchClient();
	}


}
