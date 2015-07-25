package mrriegel.rwl.world;

import java.util.ArrayList;
import java.util.Random;

import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.MyUtils;
import net.minecraft.block.material.Material;
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
					world.setBlock(l.x, l.y, l.z, ModBlocks.mazer);

				}
				world.setBlock(x, i, z, ModBlocks.mazer);
				break;
			}
		}

	}

	private boolean isSolid(int x, int i, int z, World world) {
		ArrayList<BlockLocation> lis = MyUtils.getAroundBlocks(world, x, i, z);
		boolean res = true;
		for (BlockLocation l : lis) {
			if (world.getBlock(l.x, l.y, l.z).getMaterial()
					.equals(Material.air)
					|| world.getBlock(l.x, l.y, l.z).getMaterial().isLiquid()
					|| !world.getBlock(l.x, l.y, l.z).getMaterial().isSolid()) {
				res = false;
				break;
			}
			if (world.getBlock(l.x, l.y + 1, l.z).getMaterial().isSolid()) {
				res = false;
				break;
			}
		}
		return res;
	}

}
