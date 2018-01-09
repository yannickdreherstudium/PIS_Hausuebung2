package pis.hue2.common;

import java.util.HashMap;

/**
 * Regelt die einzelnen Packet's #äü
 * 
 * @author Johannes Mahn, Yannick Dreher
 *
 */
public class PacketManager {

	private HashMap<PacketType, PacketHandler> handler = new HashMap<>();
	
	public void registerPacketHandler(PacketType type, PacketHandler handler){
		this.handler.put(type, handler);
	}
	
	public boolean handlePacket(Connection con, PacketType type, String packet){
		if(!handler.containsKey(type)){
			System.out.println("Unknown packet " + type);
			return false;
		}
		return handler.get(type).handlePacket(con, packet);
	}
	
}
