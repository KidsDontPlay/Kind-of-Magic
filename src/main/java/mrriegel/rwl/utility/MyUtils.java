package mrriegel.rwl.utility;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MyUtils {
	public static ArrayList<BlockLocation> getAroundBlocks(World world, int x,
			int y, int z) {
		ArrayList<BlockLocation> lis = new ArrayList<BlockLocation>();

		lis.add(new BlockLocation(x - 1, y, z));
		lis.add(new BlockLocation(x + 1, y, z));
		lis.add(new BlockLocation(x - 1, y, z - 1));
		lis.add(new BlockLocation(x + 1, y, z - 1));
		lis.add(new BlockLocation(x - 1, y, z + 1));
		lis.add(new BlockLocation(x + 1, y, z + 1));
		lis.add(new BlockLocation(x, y, z - 1));
		lis.add(new BlockLocation(x, y, z + 1));
		return lis;

	}

	public static boolean breakWithFortune(World world, int x, int y, int z,
			int fortune) {
		Block block = world.getBlock(x, y, z);

		if (block.getMaterial() == Material.air) {
			return false;
		} else {
			int l = world.getBlockMetadata(x, y, z);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block)
					+ (l << 12));

			block.dropBlockAsItem(world, x, y, z, l, fortune);

			return world.setBlock(x, y, z, Blocks.air, 0, 3);
		}
	}

	public static boolean breakWithSilk(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);

		if (block.getMaterial() == Material.air) {
			return false;
		} else {
			int l = world.getBlockMetadata(x, y, z);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block)
					+ (l << 12));

			Item i = block.getItem(world, x, y, z);
			ItemStack stack = new ItemStack(i);
			EntityItem ei = new EntityItem(world, x, y+0.4, z, stack);
			world.spawnEntityInWorld(ei);

			return world.setBlock(x, y, z, Blocks.air, 0, 3);
		}
	}
}
