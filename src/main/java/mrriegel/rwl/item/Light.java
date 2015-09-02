package mrriegel.rwl.item;

import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.NBTHelper;
import mrriegel.rwl.utility.RWLUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Light extends ItemEdelstein {
	public Light() {
		super();
		cooldown = 50;
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "light");
		this.setTextureName(Reference.MOD_ID + ":" + "light");

	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if (world.isRemote || NBTHelper.getInt(stack, "cooldown") != 0)
			return stack;
		MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity
				.rayTrace(30, 1.0F);
		if (mop == null)
			return stack;
		BlockLocation bl = RWLUtils.getNeighbor(world, mop.blockX, mop.blockY,
				mop.blockZ, mop.sideHit);
		int meta = -1;
		switch (mop.sideHit) {
		case 1:
			meta = 5;
			break;
		case 2:
			meta = 4;
			break;
		case 3:
			meta = 3;
			break;
		case 4:
			meta = 2;
			break;
		case 5:
			meta = 1;
			break;
		default:
			return stack;
		}
		if (player.getDistance(mop.blockX, mop.blockY, mop.blockZ) > 20
				|| !world.getBlock(bl.x, bl.y, bl.z).equals(Blocks.air))
			return stack;
		if (world.getBlock(mop.blockX, mop.blockY, mop.blockZ)
				.canPlaceTorchOnTop(world, mop.blockX, mop.blockY, mop.blockZ)) {
			world.setBlock(bl.x, bl.y, bl.z, Blocks.torch, meta, 3);
			NBTHelper.setInteger(stack, "cooldown", cooldown);
		}
		return stack;
	}

}
