package mrriegel.rwl.proxy;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.render.MyParticle;
import mrriegel.rwl.render.ParticleSphere;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
	public static int sphereIdOutside;
	public static int sphereIdInside;

	public static void init() {
		// ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class,
		// new MazerBRenderer());
		// MinecraftForgeClient.registerItemRenderer(ModItems.ostick,new
		// ItemRend());
		Sphere sp = new Sphere();
		sp.setDrawStyle(GLU.GLU_FILL);
		sp.setNormals(GLU.GLU_SMOOTH);
		sp.setOrientation(GLU.GLU_OUTSIDE);
		sphereIdOutside = GL11.glGenLists(1);
		GL11.glNewList(sphereIdOutside, GL11.GL_COMPILE);
		ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":"
				+ "textures/ent/sp.png");
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		sp.draw(0.5f, 16, 16);

		sp.setOrientation(GLU.GLU_INSIDE);
		sphereIdInside = GL11.glGenLists(1);
		// Create a new list to hold our sphere data.
		GL11.glNewList(sphereIdInside, GL11.GL_COMPILE);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		sp.draw(0.5F, 16, 16);
		GL11.glEndList();
	}

	@Override
	public void addEffect(EntityFX entityFX) {
		Minecraft.getMinecraft().theWorld.spawnEntityInWorld(entityFX);

	}
}
