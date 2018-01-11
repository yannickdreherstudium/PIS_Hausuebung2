package pis.hue2.client;

import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	public LaunchClient(){
		window = new Gui();
		guiAusgabe = new Ausgabe(window);
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		instance = new LaunchClient();
	}


}
