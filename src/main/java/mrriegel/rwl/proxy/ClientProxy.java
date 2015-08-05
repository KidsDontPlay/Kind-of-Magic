package mrriegel.rwl.proxy;

import java.util.Random;

import mrriegel.rwl.render.MyParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
	public static void init() {
		// ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class,
		// new MazerBRenderer());
		// MinecraftForgeClient.registerItemRenderer(ModItems.ostick,new
		// ItemRend());
	}

	@Override
	public void generateParticles(World world, double x, double y, double z) {
		Random rand = new Random();
		double motionX = rand.nextGaussian() * 0.02D;
		double motionY = rand.nextGaussian() * 0.02D;
		double motionZ = rand.nextGaussian() * 0.02D;
		EntityFX par = new MyParticle(world, x + 0.5d, y + 1.3d, z + 0.5d,
				motionX, motionY, motionZ);
		Minecraft.getMinecraft().effectRenderer.addEffect(par);
	}
}
