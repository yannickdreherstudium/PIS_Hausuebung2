package pis.hue2.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pis.hue2.common.Connection;
import pis.hue2.common.PacketHandler;
import pis.hue2.common.PacketManager;
import pis.hue2.common.PacketType;

public class LaunchClient {

	private ClientGUI clientgui;
	private Socket socket;
	private String server;
	private String name;
	private int port;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	LaunchClient(String server, int port, String name, ClientGUI clientgui){
		this.server = server;
		this.port = port;
		this.name = name;
		this.clientgui = clientgui;
	}
	
	
	


}
