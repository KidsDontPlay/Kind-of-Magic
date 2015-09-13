package mrriegel.rwl.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ParticlePacketHandler implements
		IMessageHandler<ParticlePacket, IMessage> {

	@Override
	public IMessage onMessage(ParticlePacket message, MessageContext ctx) {
		World w = Minecraft.getMinecraft().theWorld;
		for (int i = 0; i < 7; i++)
			w.spawnParticle("happyVillager", message.x + w.rand.nextDouble(),
					message.y + 0.7D, message.z + w.rand.nextDouble(), 0, 0, 0);
		return null;
	}

}
