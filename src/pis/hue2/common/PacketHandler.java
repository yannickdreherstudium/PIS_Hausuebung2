package pis.hue2.common;

public abstract class PacketHandler {

	public abstract boolean handlePacket(Connection con, String packet);
	
}
