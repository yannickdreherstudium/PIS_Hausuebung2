package pis.hue2.server;

import java.net.Socket;
import java.util.HashSet;

public class TeilnehmerListe {

	private HashSet<ClientConnection> connections = new HashSet<>();
	
	public void newConnection(Socket connection){
		synchronized (connections) {
			connections.add(new ClientConnection(connection));
		}
	}
	
	public void endConnection(ClientConnection connection) {
		synchronized (connections) {
			connections.remove(connection);
		}
	}
	
}
