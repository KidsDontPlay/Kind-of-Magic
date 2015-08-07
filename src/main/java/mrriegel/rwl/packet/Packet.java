package mrriegel.rwl.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class Packet implements IMessage {
	public static short damage;

	public Packet() {
	}

	public Packet(short a) {
		this.damage = a;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeShort(damage);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.damage = buf.readShort();
	}

}
