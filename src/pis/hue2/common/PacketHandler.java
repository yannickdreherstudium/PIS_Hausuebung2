package pis.hue2.common;

/**
 * 
 * @author Johannes Mahn, Yannick Dreher
 *
 */
public abstract class PacketHandler {

	public abstract boolean handlePacket(Connection con, String packet);
	
}
