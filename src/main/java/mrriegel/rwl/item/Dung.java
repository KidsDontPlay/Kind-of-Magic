package mrriegel.rwl.item;

import java.util.Random;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.MyUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Dung extends Item {
	public Dung() {
		super();

		this.setMaxDamage(2000);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "dung");
		this.setTextureName(Reference.MOD_ID + ":" + "dung");
		this.setMaxStackSize(1);

	}

	@Override
	public boolean isDamageable() {
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int p_77648_7_, float p_77648_8_,
			float p_77648_9_, float p_77648_10_) {

		System.out.println("damg: " + stack.getItemDamage());

		if (world.isRemote || stack.getItemDamage() != 0)
			return false;
		Block block = world.getBlock(x, y, z);
		if (block instanceof BlockCrops) {
			for (BlockLocation loc : MyUtils.getAroundBlocks(world, x, y, z)) {
				if (!world.getBlock(loc.x, loc.y, loc.z).equals(Blocks.air))
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

		}
		stack.setItemDamage(800);
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_,
			EntityPlayer p_77659_3_) {
		if (p_77659_2_.isRemote)
			System.out.println("clie: " + p_77659_1_.getItemDamage());
		else {

			System.out.println("serv: " + p_77659_1_.getItemDamage());
		}
		return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		// if (par1ItemStack.isItemDamaged())
		// par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() - 1);
		// System.out.println("hohoho: " + par1ItemStack.getItemDamage());
	}
}
