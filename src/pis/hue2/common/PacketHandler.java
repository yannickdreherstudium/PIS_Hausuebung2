package pis.hue2.common;

/**
 * Stellt den PacketHandler zur Verfügung
 * 
 * @author Johannes Mahn, Yannick Dreher
 *
 */
public abstract class PacketHandler {

	public abstract boolean handlePacket(Connection con, String packet);

}
