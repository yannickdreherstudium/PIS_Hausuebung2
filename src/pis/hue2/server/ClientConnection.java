package pis.hue2.server;

import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.ConnectionState;
import pis.hue2.common.PacketManager;
import pis.hue2.common.PacketType;

public class ClientConnection extends Connection{
	
	private String name = null;
	private ConnectionState state = ConnectionState.Login;
	
	public ClientConnection(Socket socket, PacketManager manager) {
		super(socket, manager);
	}

	@Override
	public void onDisconnect() {
		Server.instance.teilnehmer.endConnection(this);
		state = ConnectionState.Disconnected;
		System.out.println("Client '" + name + "' left!");
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if(Server.instance.teilnehmer.isNameinUse(name)){
			sendPacket(PacketType.refused, "name_in_use");
			return false;
		}
		this.name = name;
		state = ConnectionState.Connected;
		return true;
	}

	@Override
	public ConnectionState getConnectionState() {
		return state;
	}
	

}
