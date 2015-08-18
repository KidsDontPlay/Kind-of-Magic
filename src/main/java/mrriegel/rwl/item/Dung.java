package mrriegel.rwl.item;

import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.RWLUtils;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Dung extends ItemEdelstein {
	public Dung() {
		super();
		cooldown = 18000;
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "dung");
		this.setTextureName(Reference.MOD_ID + ":" + "dung");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int s, float xs, float ys, float zs) {
		if (world.isRemote || NBTHelper.getInt(stack, "cooldown") != 0)
			return false;
		Block block = world.getBlock(x, y, z);
		if (block == null)
			return false;
		if (block instanceof BlockCrops) {
			for (BlockLocation loc : RWLUtils.getAroundBlocks(world, x, y, z)) {
				if (!world.getBlock(loc.x, loc.y, loc.z).equals(Blocks.air)
						&& !world.getBlock(loc.x, loc.y, loc.z).equals(
								Blocks.tallgrass))
					continue;
				if (world.getBlock(loc.x, loc.y - 1, loc.z)
						.equals(Blocks.grass)
						|| world.getBlock(loc.x, loc.y - 1, loc.z).equals(
								Blocks.dirt)) {
					world.setBlock(loc.x, loc.y - 1, loc.z, Blocks.farmland, 0,
							2);
					world.setBlock(loc.x, loc.y, loc.z, block, 0, 2);
				} else if (world.getBlock(loc.x, loc.y - 1, loc.z).equals(
						Blocks.farmland)) {
					world.setBlock(loc.x, loc.y, loc.z, block, 0, 2);
				}
			}
			NBTHelper.setInteger(stack, "cooldown", cooldown);
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_,
			EntityPlayer p_77659_3_) {
		return p_77659_1_;
	}
}
