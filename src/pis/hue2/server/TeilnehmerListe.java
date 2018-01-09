package pis.hue2.server;

import java.net.Socket;
import java.util.HashSet;

import pis.hue2.common.ConnectionState;
import pis.hue2.common.PacketType;

/**
 * Stellt die Teilnehmerliste zur Verfügung
 * 
 * @author Johannes Mahn, Yannick Dreher
 *
 */
public class TeilnehmerListe {

	private HashSet<ClientConnection> connections = new HashSet<>();

	/**
	 * Broadcastet eine Liste aller aktuell connecten Clients
	 */
	public void broadcastUserList() {
		String s = "";
		for (ClientConnection con : connections) {
			if (con.getConnectionState() == ConnectionState.Connected)
				s += con.getName() + ":";
		}
		if (s.length() == 0)
			return;
		s = s.substring(0, s.length() - 1);
		broadcast(PacketType.namelist, s);
	}

	/**
	 * Testet, ob ein Name schon in der TeilnehmerListe vorhanden ist
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean isNameinUse(String name) {
		for (ClientConnection con : connections)
			if (name.equals(con.getName()))
				return true;
		return false;
	}

	/**
	 * Sendet an alle Verbundenen Clients
	 * 
	 * @param type
	 *            PacketType - Den Nachrichtentyp
	 * @param content
	 *            String - Die Nachricht
	 */
	public void broadcast(PacketType type, String content) {
		synchronized (connections) {
			for (ClientConnection con : connections) {
				if (con.getConnectionState() == ConnectionState.Connected)
					con.sendPacket(type, content);
			}
		}
	}

	/**
	 * Falls noch nicht zu viele zum Server verbunden sind, wird die Verbindung
	 * bestätigt
	 * 
	 * @param connection
	 *            Socket - Die Verbindung
	 */
	public void newConnection(Socket connection) {
		synchronized (connections) {
			if (connections.size() >= 3) {
				ClientConnection con = new ClientConnection(connection, Server.instance.packetManager);
				con.sendPacket(PacketType.refused, "too_many_users");
				return;
			}
			connections.add(new ClientConnection(connection, Server.instance.packetManager));
		}
	}

	/**
	 * Beendet die Verbindung, und braodcastet die neue Teilnehmer
	 * 
	 * @param connection
	 *            ClientConnection - Die Verbindung
	 */
	public void endConnection(ClientConnection connection) {
		synchronized (connections) {
			connections.remove(connection);
			broadcastUserList();
		}
	}

}
