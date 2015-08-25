package mrriegel.rwl.item;

import mrriegel.rwl.entity.Ice;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Shoot extends ItemEdelstein {
	public Shoot() {
		super();
		cooldown = 60;
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "shoot");
		this.setTextureName(Reference.MOD_ID + ":" + "shoot");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int side, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if (world.isRemote || NBTHelper.getInt(stack, "cooldown") != 0)
			return stack;
		world.spawnEntityInWorld(new Ice(world, player, 3));
		NBTHelper.setInteger(stack, "cooldown", cooldown);

		return stack;
	}
}
