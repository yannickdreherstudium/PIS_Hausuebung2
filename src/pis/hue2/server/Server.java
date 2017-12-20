package pis.hue2.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import pis.hue2.common.PacketType;

public class Server {

	public static Server instance;
	
	public static void main(String[] args) {
		instance = new Server();
		try{
			instance.startServer();
		}catch(IOException ex){
			System.err.println("Error trying to bind port!");
			ex.printStackTrace();
		}
		System.out.println("Server started!");
		while(instance.isRunning()){}
		System.out.println("Server closed!");
	}

	private ServerSocket socket;
	public TeilnehmerListe teilnehmer = new TeilnehmerListe();

	public void startServer() throws IOException{
		socket = new ServerSocket(23);
		System.out.println(socket.getInetAddress());
		new Thread(() -> {
			System.out.println("Waiting for connections");
			while(isRunning()){
				try{
					Socket client = socket.accept();
					System.out.println("New client connected");
					teilnehmer.newConnection(client);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}).start();
	}

	public boolean isRunning(){
		return socket.isBound() && !socket.isClosed();
	}

}
