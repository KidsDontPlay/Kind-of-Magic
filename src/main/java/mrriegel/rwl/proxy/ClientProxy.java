package mrriegel.rwl.proxy;

import java.util.Random;

import mrriegel.rwl.render.MyParticle;
import mrriegel.rwl.utility.BlockLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
	public static void init() {
//		ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class,
//				new MazerBRenderer());
//		MinecraftForgeClient.registerItemRenderer(ModItems.ostick,new ItemRend());
	}
	
	

	@Override
	public void generateParticles(World world, BlockLocation loc) {
		Random rand = new Random();
		double motionX = rand.nextGaussian() * 0.02D;
		double motionY = rand.nextGaussian() * 0.02D;
		double motionZ = rand.nextGaussian() * 0.02D;
		EntityFX par = new MyParticle(world, loc.x + 0.5d, loc.y + 1.3d,
				loc.z + 0.5d, motionX, motionY, motionZ);
		Minecraft.getMinecraft().effectRenderer.addEffect(par);
	}
}
