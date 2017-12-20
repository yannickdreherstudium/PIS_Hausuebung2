package pis.hue2.server;

import java.net.Socket;
import java.util.HashSet;

import pis.hue2.common.ConnectionState;
import pis.hue2.common.PacketType;

public class TeilnehmerListe {

	private HashSet<ClientConnection> connections = new HashSet<>();

	public void broadcastUserList(){
		String s = "";
		for(ClientConnection con : connections){
			if(con.getConnectionState() == ConnectionState.Connected)
				s += con.getName() + ":";
		}
		if(s.length() == 0)return;
		s = s.substring(0, s.length()-1);
		broadcast(PacketType.namelist, s);
	}
	
	public boolean isNameinUse(String name){
		for(ClientConnection con : connections)
			if(name.equals(con.getName()))
				return true;
		return false;
	}
	
	public void broadcast(PacketType type, String content){
		synchronized (connections) {
			for(ClientConnection con : connections){
				if(con.getConnectionState() == ConnectionState.Connected)
					con.sendPacket(type, content);
			}
		}
	}

	public void newConnection(Socket connection){
		synchronized (connections) {
			if(connections.size() >= 3){
				ClientConnection con = new ClientConnection(connection, Server.instance.packetManager);
				con.sendPacket(PacketType.refused, "too_many_users");
				return;
			}
			connections.add(new ClientConnection(connection, Server.instance.packetManager));
		}
	}

	public void endConnection(ClientConnection connection) {
		synchronized (connections) {
			connections.remove(connection);
			broadcastUserList();
		}
	}

}
