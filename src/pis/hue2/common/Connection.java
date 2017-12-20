package pis.hue2.common;

import java.io.IOException;
import java.net.Socket;

public abstract class Connection {

	private Socket socket;
	
	public Connection(Socket socket){
		this.socket = socket;
	}
	
	public void sendPacket(PacketType type, String content){
		try {
			socket.getOutputStream().write((type.name() + ":" + content).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
