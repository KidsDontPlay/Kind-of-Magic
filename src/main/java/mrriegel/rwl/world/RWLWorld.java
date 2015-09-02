package mrriegel.rwl.world;

import java.util.ArrayList;
import java.util.Random;

import mrriegel.rwl.handler.ConfigurationHandler;
import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.RWLUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class RWLWorld implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (world.provider.dimensionId == 0)
			generateShrine(random, chunkX, chunkZ, world);

		if (world.provider.dimensionId != -1 && world.provider.dimensionId != 1) {
			addOreSpawn(ModBlocks.orus, world, random, chunkX * 16,
					chunkZ * 16, 16, 16, 16, 32);
			addOreSpawnL(ModBlocks.airorus, world, random, chunkX * 16,
					chunkZ * 16, 16, 16, 140, 160);
		}

	}

	public void addOreSpawn(Block block, World world, Random random,
			int blockXPos, int blockZPos, int maxX, int maxZ, int minY, int maxY) {

		for (int i = 0; i < 200; i++) {
			if (random.nextInt(ConfigurationHandler.nevOreRarity) != 0)
				continue;
			int diffBtwnMinMaxY = maxY - minY;
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, 8 + random.nextInt(3))).generate(world,
					random, posX, posY, posZ);
		}
	}

	public void addOreSpawnL(Block block, World world, Random random,
			int blockXPos, int blockZPos, int maxX, int maxZ, int minY, int maxY) {
		if (random.nextInt(ConfigurationHandler.lightNevOreRarity) != 0)
			return;
		int diffBtwnMinMaxY = maxY - minY;
		int posX = blockXPos + random.nextInt(maxX);
		int posY = minY + random.nextInt(diffBtwnMinMaxY);
		int posZ = blockZPos + random.nextInt(maxZ);

		boolean te = false;
		for (int i = 50; i < 80; i++) {
			if (world.getBlock(posX, i, posZ).equals(Blocks.water)
					|| world.getBlock(posX, i, posZ).equals(Blocks.ice)) {
				te = true;
				break;
			}
		}
		if (te)
			(new WorldGenMinable(block, 8 + random.nextInt(3), Blocks.air))
					.generate(world, random, posX, posY, posZ);

	}

	private void generateShrine(Random random, int chunkX, int chunkZ,
			World world) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		for (int i = 110; i > 50; i--) {
			int ran = (int) (Math.random() * ConfigurationHandler.structureRarity) + 1;

			if (isSolid(x, i, z, world) && ran == 1) {
				setB(x, i, z, ModBlocks.mazer, world);

				for (BlockLocation l : RWLUtils.getAroundBlocks(world, x, i, z)) {
					setB(l.x, l.y, l.z, ModBlocks.mazer, world);
				}
				for (BlockLocation l : RWLUtils.getAroundBlocks(world, x + 1,
						i, z + 1)) {
					setB(l.x, l.y, l.z, ModBlocks.mazer, world);
				}
				for (BlockLocation l : RWLUtils.getAroundBlocks(world, x + 1,
						i, z - 1)) {
					setB(l.x, l.y, l.z, ModBlocks.mazer, world);
				}
				for (BlockLocation l : RWLUtils.getAroundBlocks(world, x - 1,
						i, z + 1)) {
					setB(l.x, l.y, l.z, ModBlocks.mazer, world);
				}
				for (BlockLocation l : RWLUtils.getAroundBlocks(world, x - 1,
						i, z - 1)) {
					setB(l.x, l.y, l.z, ModBlocks.mazer, world);
				}

				setB(x + 2, i + 1, z + 2, ModBlocks.mazer, world);
				setB(x - 2, i + 1, z + 2, ModBlocks.mazer, world);
				setB(x + 2, i + 1, z - 2, ModBlocks.mazer, world);
				setB(x - 2, i + 1, z - 2, ModBlocks.mazer, world);
				if (world.getBlock(x + 2, i + 1, z + 2).equals(ModBlocks.mazer))
					setB(x + 2, i + 2, z + 2, ModBlocks.mazer, world);
				if (world.getBlock(x - 2, i + 1, z + 2).equals(ModBlocks.mazer))
					setB(x - 2, i + 2, z + 2, ModBlocks.mazer, world);
				if (world.getBlock(x + 2, i + 1, z - 2).equals(ModBlocks.mazer))
					setB(x + 2, i + 2, z - 2, ModBlocks.mazer, world);
				if (world.getBlock(x - 2, i + 1, z - 2).equals(ModBlocks.mazer))
					setB(x - 2, i + 2, z - 2, ModBlocks.mazer, world);
				break;
			}
		}

	}

	private void setB(int x, int y, int z, Block b, World world) {
		int ran = (int) (Math.random() * Integer.MAX_VALUE);
		if (ran % 4 != 0 && ran % 3 != 1 && ran % 5 != 2) {
			world.setBlock(x, y, z, b);
		}
	}

	private boolean isSolid(int x, int i, int z, World world) {
		ArrayList<BlockLocation> lis = RWLUtils.getAroundBlocks(world, x, i, z);
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
