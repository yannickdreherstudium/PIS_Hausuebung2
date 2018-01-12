package pis.hue2.server;

import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.ConnectionState;
import pis.hue2.common.PacketManager;
import pis.hue2.common.PacketType;

/**
 * Stellt die ClientConnection zur Verfügung
 * 
 * @author Johannes Mahn, Yannick Dreher
 *
 */
public class ClientConnection extends Connection {

	private String name = null;
	private ConnectionState state = ConnectionState.Login;

	/**
	 * Erzeugt eine neue Verbidnung
	 * 
	 * @param socket
	 *            Socket - Der Verbindungssocket
	 * @param manager
	 */
	public ClientConnection(Socket socket, PacketManager manager) {
		super(socket, manager);
	}

	/**
	 * Beendet die Verbindung von diesem Client
	 */
	@Override
	public void onDisconnect() {
		LaunchServer.instance.teilnehmer.endConnection(this);
		state = ConnectionState.Disconnected;
		System.out.println("Client '" + name + "' left!");
	}

	/**
	 * 
	 * @return den Client Namen
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setz dem Client einen Namen, falls dieser nicht schon vorhanden ist
	 * 
	 * @param name
	 *            String - Der Name
	 * @return Boolean - true wenn der Name erfolgreich gesetzt werden konnte
	 */
	public boolean setName(String name) {
		if (LaunchServer.instance.teilnehmer.isNameinUse(name)) {
			sendPacket(PacketType.refused, "name_in_use");
			return false;
		}
		this.name = name;
		state = ConnectionState.Connected;
		return true;
	}

	/**
	 * Gibt den Alktuellen Status zurück
	 */
	@Override
	public ConnectionState getConnectionState() {
		return state;
	}

}
