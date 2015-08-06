package mrriegel.rwl.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketHandler implements IMessageHandler<Packet, IMessage> {

	@Override
	public IMessage onMessage(Packet message, MessageContext ctx) {
		return null;
	}

}
