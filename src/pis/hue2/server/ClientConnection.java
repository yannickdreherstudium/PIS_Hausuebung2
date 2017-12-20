package pis.hue2.server;

import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.PacketManager;

public class ClientConnection extends Connection{

	private String name;
	
	public ClientConnection(Socket socket, PacketManager manager) {
		super(socket, manager);
	}

	@Override
	public void onDisconnect() {
		Server.instance.teilnehmer.endConnection(this);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
