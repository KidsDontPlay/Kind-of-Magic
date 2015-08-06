package mrriegel.rwl.render;

import mrriegel.rwl.RWL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.TextureManager;

public class ParticleEffects {
	public static TextureManager textureManager = Minecraft.getMinecraft().renderEngine;
	public static Minecraft minecraft = Minecraft.getMinecraft();

	public static void spawnParticle(String particleName, double posX,
			double posY, double posZ, double motX, double motY, double motZ) {
		spawnParticle(particleName, posX, posY, posZ, motX, motY, motZ, 0, 0, 0);
	}

	public static void spawnParticle(String particleName, double posX,
			double posY, double posZ, double motX, double motY, double motZ,
			double r, double g, double b) {

		if (minecraft != null && minecraft.renderViewEntity != null
				&& minecraft.effectRenderer != null) {
			int var14 = minecraft.gameSettings.particleSetting;
			if (var14 == 1 && minecraft.theWorld.rand.nextInt(3) == 0) {
				var14 = 2;
			}
			double var15 = minecraft.renderViewEntity.posX - posX;
			double var17 = minecraft.renderViewEntity.posY - posY;
			double var19 = minecraft.renderViewEntity.posZ - posZ;
			EntityFX entityfx = null;
			double var22 = 16.0D;
			if (!(var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22)) {
				if (particleName.equals("fire")) {
					entityfx = new ParticleSphere(minecraft.theWorld, posX,
							posY, posZ, (float) motX, (float) motY,
							(float) motZ);

				}

				RWL.proxy.addEffect(entityfx);
			}
		}

	}

}