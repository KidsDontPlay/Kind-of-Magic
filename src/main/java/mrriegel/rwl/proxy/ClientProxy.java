package mrriegel.rwl.proxy;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.render.ItemRend;
import mrriegel.rwl.render.MazerBRenderer;
import mrriegel.rwl.render.MyParticle;
import mrriegel.rwl.tile.MazerTile;
import mrriegel.rwl.utility.BlockLocation;
import cpw.mods.fml.client.registry.ClientRegistry;

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
