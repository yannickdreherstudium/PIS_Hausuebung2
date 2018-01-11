package pis.hue2.client;

import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.ConnectionState;
import pis.hue2.common.PacketManager;

public class ServerConnection extends Connection {
	
	private String name = null;
	private ConnectionState state = ConnectionState.Login;

	public ServerConnection(Socket socket, PacketManager handler) {
		super(socket, handler);
	}

	@Override
	public void onDisconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConnectionState getConnectionState() {
		// TODO Auto-generated method stub
		return state;
	}

}
