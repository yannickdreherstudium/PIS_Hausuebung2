package pis.hue2.client;

import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.ConnectionState;
import pis.hue2.common.PacketManager;

public class ServerConnection extends Connection {
	
	private ConnectionState state = ConnectionState.Login;

	public ServerConnection(Socket socket, PacketManager handler) {
		super(socket, handler);
	}

	@Override
	public void onDisconnect() {
		state = ConnectionState.Disconnected;
		LaunchClient.getInstance().getGuiAusgabe().zeigeNachricht("Disconnected!");
	}

	@Override
	public ConnectionState getConnectionState() {
		return state;
	}

}
