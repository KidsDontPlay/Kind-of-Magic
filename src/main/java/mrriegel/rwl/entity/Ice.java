package mrriegel.rwl.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Ice extends EnergyBlastProjectile {
	public Ice(World par1World) {
		super(par1World);
	}

	public Ice(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	public Ice(World par1World, EntityLivingBase par2EntityPlayer, int damage) {
		super(par1World, par2EntityPlayer, damage);
	}

	public Ice(World par1World, EntityLivingBase par2EntityPlayer, int damage,
			int maxTicksInAir, double posX, double posY, double posZ,
			float rotationYaw, float rotationPitch) {
		super(par1World, par2EntityPlayer, damage, maxTicksInAir, posX, posY,
				posZ, rotationYaw, rotationPitch);
	}

	public Ice(World par1World, EntityLivingBase par2EntityLivingBase,
			EntityLivingBase par3EntityLivingBase, float par4, float par5,
			int damage, int maxTicksInAir) {
		super(par1World, par2EntityLivingBase, par3EntityLivingBase, par4,
				par5, damage, maxTicksInAir);
	}

	@Override
	public DamageSource getDamageSource() {
		return DamageSource.causeMobDamage(shootingEntity);
	}

	@Override
	public void onImpact(MovingObjectPosition mop) {
		if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
				&& mop.entityHit != null) {
			if (mop.entityHit == shootingEntity) {
				return;
			}

			this.onImpact(mop.entityHit);
		} else if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
		}
		this.setDead();
	}

	@Override
	public void onImpact(Entity mop) {
		if (mop == shootingEntity && ticksInAir > 3) {
			shootingEntity.attackEntityFrom(
					DamageSource.causeMobDamage(shootingEntity), 1);
			this.setDead();
		} else {
			if (mop instanceof EntityLivingBase)
				doDamage(projectileDamage, mop);
		}
		if (worldObj.isAirBlock((int) this.posX, (int) this.posY,
				(int) this.posZ)) {
			// worldObj.setBlock((int)this.posX, (int)this.posY,
			// (int)this.posZ,Block.fire.blockID);
		}

		spawnHitParticles("magicCrit", 8);
		this.setDead();
	}
}
