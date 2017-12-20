package pis.hue2.server;

import java.net.Socket;

import pis.hue2.common.Connection;

public class ClientConnection extends Connection{

	public ClientConnection(Socket socket) {
		super(socket);
	}

}
