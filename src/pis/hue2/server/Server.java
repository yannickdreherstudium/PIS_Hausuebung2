package pis.hue2.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import pis.hue2.common.Connection;
import pis.hue2.common.PacketHandler;
import pis.hue2.common.PacketManager;
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
	public PacketManager packetManager = new PacketManager();

	public void startServer() throws IOException{
		registerHandler();
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

	private void registerHandler(){
		packetManager.registerPacketHandler(PacketType.connect, new PacketHandler() {
			
			@Override
			public boolean handlePacket(Connection con, String packet) {
				ClientConnection ccon = (ClientConnection) con;
				if(packet.isEmpty() || packet.contains(":") || packet.length() > 30){
					ccon.sendPacket(PacketType.refused, "invalid_name");
					return false;
				}
				ccon.setName(packet);
				ccon.sendPacket(PacketType.connect, "ok");
				System.out.println("Client renamed to '" + packet +"'");
				return true;
			}
		});
	}
	
	public boolean isRunning(){
		return socket.isBound() && !socket.isClosed();
	}

}
