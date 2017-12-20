package pis.hue2.server;

import java.net.Socket;
import java.util.HashSet;

public class TeilnehmerListe {

	private HashSet<ClientConnection> connections = new HashSet<>();
	
	public void newConnection(Socket connection){
		connections.add(new ClientConnection(connection));
	}
	
}
