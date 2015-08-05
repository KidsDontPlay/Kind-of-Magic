package mrriegel.rwl.render;

import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.world.World;

public class MyParticle extends EntityAuraFX {

	public MyParticle(World parWorld, double parX, double parY, double parZ,
			double parMotionX, double parMotionY, double parMotionZ) {
		super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
		setParticleTextureIndex(82); // same as happy villager
		particleScale = 2.0F;
		particleMaxAge = 20;
		setRBGColorF(0x88, 0x00, 0x88);
	}

}
