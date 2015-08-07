package mrriegel.rwl.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrriegel.rwl.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleSphere extends EntityFX {
	public ParticleSphere(World par1World, double x, double y, double z,
			float motionX, float motionY, float motionZ) {
		super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
//		this.motionX *= 0.10000000149011612D;
//		this.motionY *= 0.10000000149011612D;
//		this.motionZ *= 0.10000000149011612D;
//		this.motionX += motionX * 0.4D;
//		this.motionY += motionY * 0.4D;
//		this.motionZ += motionZ * 0.4D;
		this.motionY=motionY;
		this.particleRed = 0.369F;
		this.particleGreen = 0.808F;
		this.particleBlue = 0.973F;
		this.particleScale *= 0.75F;
		this.noClip = false;

		this.particleMaxAge = 60;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderParticle(Tessellator par1Tessellator, float par2,
			float par3, float par4, float par5, float par6, float par7) {

		par1Tessellator.draw();
		Minecraft
				.getMinecraft()
				.getTextureManager()
				.bindTexture(
						new ResourceLocation(Reference.MOD_ID + ":"
								+ "textures/ent/sp.png"));
		par1Tessellator.startDrawingQuads();
		par1Tessellator.setBrightness(200);// make sure you have this!!
		super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6,
				par7);
	}

}