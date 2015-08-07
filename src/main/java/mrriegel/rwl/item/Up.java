package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Up extends Item {
	public Up() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "up");
		this.setTextureName(Reference.MOD_ID + ":" + "up");

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		for (double i = player.posY + 2.0D; i < 255; i = i + 1.0D) {
			if (world
					.getBlock(trueVal(player.posX), trueVal(i),
							trueVal(player.posZ)).getMaterial().isSolid()) {
				if (world.getBlock(trueVal(player.posX), trueVal(i + 1),
						trueVal(player.posZ)).getMaterial() == Material.air
						&& world.getBlock(trueVal(player.posX), trueVal(i + 2),
								trueVal(player.posZ)).getMaterial() == Material.air) {
					player.setPositionAndUpdate(player.posX, i + 1.1D,
							player.posZ);
					break;
				}
			}
		}
		return stack;

	}

	private int trueVal(double num) {
		if (num < 0) {
			num--;
		}
		return (int) num;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1;
	}
}