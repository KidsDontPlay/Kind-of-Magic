package mrriegel.rwl.item;

import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Flash extends ItemEdelstein {
	public Flash() {
		super();
		cooldown = 1000;
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "flash");
		this.setTextureName(Reference.MOD_ID + ":" + "flash");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int side, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if (world.isRemote || NBTHelper.getInt(stack, "cooldown") != 0)
			return stack;
		Entity e = Minecraft.getMinecraft().objectMouseOver.entityHit;
		if (e != null && e instanceof EntityLivingBase) {
			world.addWeatherEffect(new EntityLightningBolt(world, e.posX,
					e.posY, e.posZ));
			NBTHelper.setInteger(stack, "cooldown", cooldown);
		}

		return stack;
	}
}
