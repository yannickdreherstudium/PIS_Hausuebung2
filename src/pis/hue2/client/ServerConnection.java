package pis.hue2.client;

import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.ConnectionState;
import pis.hue2.common.PacketManager;

/**
 * Stellt die Server Connetion zur Verfügung
 * 
 * @author Johannes Mahn, Yannick Dreher
 *
 */
public class ServerConnection extends Connection {

	private ConnectionState state = ConnectionState.Login;

	public ServerConnection(Socket socket, PacketManager handler) {
		super(socket, handler);
	}

	/**
	 * Setzt den staus disconnect, und leert die Userlist
	 */
	@Override
	public void onDisconnect() {
		state = ConnectionState.Disconnected;
		LaunchClient.getInstance().getGuiAusgabe().zeigeNachricht("Disconnected!");
		LaunchClient.getInstance().getGuiAusgabe().zeigeListe(new String[0]);
	}

	/**
	 * Gibt den ConnectionStatus zurück
	 */
	@Override
	public ConnectionState getConnectionState() {
		return state;
	}

	/**
	 * Setzt den ConnectionStatus
	 */
	public void setConnected() {
		state = ConnectionState.Connected;
	}

}
