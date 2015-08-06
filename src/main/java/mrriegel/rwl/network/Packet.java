package mrriegel.rwl.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class Packet implements IMessage {
	public static int x, y, z;
	public static boolean activ;

	public Packet() {
	}

	public Packet(int x, int y, int z, boolean activ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.activ = activ;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.activ = buf.readBoolean();
		System.out.println("from: " + x + " " + y + " " + z);

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(activ);
		System.out.println("to: " + x + " " + y + " " + z);
	}

}