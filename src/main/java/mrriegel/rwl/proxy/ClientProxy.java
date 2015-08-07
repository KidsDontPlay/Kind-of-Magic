package mrriegel.rwl.proxy;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.render.MyParticle;
import mrriegel.rwl.render.ParticleSphere;
import mrriegel.rwl.render.StoneItemRenderer;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

	public static void init() {
		// ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class,
		// new MazerBRenderer());
		// MinecraftForgeClient.registerItemRenderer(ModItems.ostick,new
		// ItemRend());
	}

	@Override
	public void addEffect(EntityFX entityFX) {
		Minecraft.getMinecraft().theWorld.spawnEntityInWorld(entityFX);

	}
	
	@Override
	public void registerRenderers(){
		ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class, new StoneItemRenderer());
	}
}
