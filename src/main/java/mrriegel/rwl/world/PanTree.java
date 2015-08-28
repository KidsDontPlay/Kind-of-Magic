package mrriegel.rwl.world;

import java.util.Random;

import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.RWLUtils;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class PanTree extends WorldGenAbstractTree {

	public PanTree() {
		super(true);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (check(world, x, y, z))
			build(world, x, y, z, rand);
		return true;
	}

	private void build(World world, int x, int y, int z, Random rand) {
		for (int i = y; i < y + 5; i++)
			world.setBlock(x, i, z, ModBlocks.panlog);
		int max = rand.nextInt(10);
		for (int l = y + 2; l <= y + 5; l++) {
			max++;
			int tmp = max % 2 == 0 ? 1 : 2;
			for (int k = x - tmp; k <= x + tmp; k++) {
				for (int j = z - tmp; j <= z + tmp; j++) {
					if (!(world.getBlock(k, l, j) instanceof BlockLeaves)
							&& !(world.getBlock(k, l, j).equals(Blocks.air)))
						continue;
					world.setBlock(k, l, j, ModBlocks.panleaves);
				}
			}
		}
	}

	private boolean check(World world, int x, int y, int z) {
		for (int i = y + 2; i < y + 7; i++) {
			for (BlockLocation bl : RWLUtils.getAroundBlocks(world, x, i, z)) {
				if (!world.getBlock(bl.x, bl.y, bl.z).canBeReplacedByLeaves(
						world, x, y, z)
						&& !(world.getBlock(bl.x, bl.y, bl.z) instanceof BlockLeaves)
						&& !(world.getBlock(bl.x, bl.y, bl.z) instanceof BlockLog)) {
					System.out.println("falsch: "
							+ world.getBlock(bl.x, bl.y, bl.z));
					return false;
				}
			}
		}
		return true;
	}
}
