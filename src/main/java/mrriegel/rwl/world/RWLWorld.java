package mrriegel.rwl.world;

import java.util.ArrayList;
import java.util.Random;

import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.MyUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class RWLWorld implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (world.provider.dimensionId == 0) {
			generateShrine(random, chunkX, chunkZ, world);
		}

	}

	private void generateShrine(Random random, int chunkX, int chunkZ,
			World world) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		for (int i = 110; i > 50; i--) {
			int ran = (int) (Math.random() * 80) + 1;
			if (isSolid(x, i, z, world) && ran == 1) {
				ArrayList<BlockLocation> lis = MyUtils.getAroundBlocks(world,
						x, i, z);
				for (BlockLocation l : lis) {
					setB(l.x, l.y, l.z, ModBlocks.mazer, world);

				}
				setB(x, i, z, ModBlocks.mazer, world);
				setB(x + 2, i + 1, z + 2, ModBlocks.mazer, world);
				setB(x + -2, i + 1, z + 2, ModBlocks.mazer, world);
				setB(x + 2, i + 1, z - 2, ModBlocks.mazer, world);
				setB(x - 2, i + 1, z - 2, ModBlocks.mazer, world);
				setB(x + 2, i + 2, z + 2, ModBlocks.mazer, world);
				setB(x + -2, i + 2, z + 2, ModBlocks.mazer, world);
				setB(x + 2, i + 2, z - 2, ModBlocks.mazer, world);
				setB(x - 2, i + 2, z - 2, ModBlocks.mazer, world);
				break;
			}
		}

	}

	private void setB(int x, int y, int z, Block b, World world) {
		int ran = (int) (Math.random() * Integer.MAX_VALUE);
		if (ran % 4 != 0) {
			world.setBlock(x, y, z, b);
		}
	}

	private boolean isSolid(int x, int i, int z, World world) {
		ArrayList<BlockLocation> lis = MyUtils.getAroundBlocks(world, x, i, z);
		boolean res = true;
		for (BlockLocation l : lis) {
			if (world.getBlock(l.x, l.y, l.z).getMaterial()
					.equals(Material.air)
					|| world.getBlock(l.x, l.y, l.z).getMaterial().isLiquid()
					|| !world.getBlock(l.x, l.y, l.z).getMaterial().isSolid()
					|| world.getBlock(l.x, l.y, l.z).getMaterial()
							.equals(Material.leaves)) {
				res = false;
				break;
			}
			if (world.getBlock(l.x, l.y + 1, l.z).getMaterial().isSolid()
					|| world.getBlock(l.x, l.y + 1, l.z).getMaterial()
							.isLiquid()) {
				res = false;
				break;
			}
			if (world.getBlock(l.x, l.y + 2, l.z).getMaterial().isSolid()
					|| world.getBlock(l.x, l.y + 1, l.z).getMaterial()
							.isLiquid()) {
				res = false;
				break;
			}
			if (world.getBlock(l.x, l.y + 3, l.z).getMaterial().isSolid()
					|| world.getBlock(l.x, l.y + 1, l.z).getMaterial()
							.isLiquid()) {
				res = false;
				break;
			}
		}
		// if (world.getBlock(x, i, z).getMaterial().equals(Material.air)
		// || world.getBlock(x, i, z).getMaterial().isLiquid()
		// || !world.getBlock(x, i, z).getMaterial().isSolid()) {
		// res = false;
		// return res;
		//
		// }
		// if (world.getBlock(x, i + 1, z).getMaterial().isSolid()) {
		// res = false;
		// return res;
		//
		// }
		// if (world.getBlock(x, i + 2, z).getMaterial().isSolid()) {
		// res = false;
		// return res;
		//
		// }
		// if (world.getBlock(x, i + 3, z).getMaterial().isSolid()) {
		// res = false;
		// return res;
		//
		// }
		return res;

	}

}
