package pis.hue2.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public abstract class Connection {

	private Socket socket;
	private Thread clientThread;

	public Connection(Socket socket){
		this.socket = socket;

		clientThread = new Thread(() -> {
			try{
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				while(isConnected()){
					String input = in.readLine();
					if(input != null){
						input = input.replace("ÿûÿû ÿûÿû'ÿýÿûÿý", "");
						System.out.println(input);
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		});
		clientThread.start();

	}

	public boolean isConnected(){
		return socket.isBound() && !socket.isClosed() && socket.isConnected();
	}

	public void sendPacket(PacketType type, String content){
		try {
			socket.getOutputStream().write((type.name() + ":" + content).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
