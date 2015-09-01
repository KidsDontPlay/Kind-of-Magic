package mrriegel.rwl.utility;

import java.util.ArrayList;
import java.util.Vector;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class RWLUtils {
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

	public static int double2int(double num) {
		if (num < 0) {
			num--;
		}
		return (int) num;
	}

	public static boolean breakWithFortune(EntityPlayer player, World world,
			int x, int y, int z, int fortune) {
		Block block = world.getBlock(x, y, z);

		if (block.getMaterial() == Material.air) {
			return false;
		} else {
			int l = world.getBlockMetadata(x, y, z);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block)
					+ (l << 12));

			block.dropBlockAsItem(world, x, y, z, l, fortune);
			MinecraftForge.EVENT_BUS.post(new BlockEvent.HarvestDropsEvent(x,
					y, z, world, block, l, fortune, 1.0F, block.getDrops(world,
							x, y, z, l, fortune), player, false));
			MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(x, y, z,
					world, block, l, player));
			// block.harvestBlock(world, player, x, y, z, l);
			return world.setBlock(x, y, z, Blocks.air);
		}
	}

	public static boolean breakWithSilk(EntityPlayer player, World world,
			int x, int y, int z) {
		Block block = world.getBlock(x, y, z);

		if (block.getMaterial() == Material.air) {
			return false;
		} else {
			int l = world.getBlockMetadata(x, y, z);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block)
					+ (l << 12));

			Item i = block.getItem(world, x, y, z);
			ItemStack stack = new ItemStack(i);
			EntityItem ei = new EntityItem(world, x + 0.5d, y + 0.4, z + 0.5d,
					stack);
			world.spawnEntityInWorld(ei);
			ArrayList<ItemStack> lis = new ArrayList<ItemStack>();
			lis.add(new ItemStack(block));
			MinecraftForge.EVENT_BUS.post(new BlockEvent.HarvestDropsEvent(x,
					y, z, world, block, l, 0, 1.0F, lis, player, true));
			MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(x, y, z,
					world, block, l, player));

			return world.setBlock(x, y, z, Blocks.air, 0, 3);
		}
	}

	public static MovingObjectPosition raytraceFromEntity(World world,
			Entity player, boolean par3, double range) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch
				+ (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw
				+ (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX)
				* (double) f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY)
				* (double) f;
		if (!world.isRemote && player instanceof EntityPlayer)
			d1 += 1.62D;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ)
				* (double) f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
		if (player instanceof EntityPlayerMP) {
			d3 = ((EntityPlayerMP) player).theItemInWorldManager
					.getBlockReachDistance();
		}
		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3,
				(double) f8 * d3);
		return world.func_147447_a(vec3, vec31, par3, !par3, par3);
	}

	public static BlockLocation getNeighbor(World world, int x, int y, int z,
			int side) {
		Block b = world.getBlock(x, y, z);
		switch (side) {
		case 0:
			return new BlockLocation(x, y - 1, z);
		case 1:
			return new BlockLocation(x, y + 1, z);
		case 2:
			return new BlockLocation(x, y, z - 1);
		case 3:
			return new BlockLocation(x, y, z + 1);
		case 4:
			return new BlockLocation(x - 1, y, z);
		case 5:
			return new BlockLocation(x + 1, y, z);
		default:
			return null;
		}

	}

	public static Vector<BlockLocation> getNeighbors12(World world, int x,
			int y, int z) {
		Vector<BlockLocation> v = new Vector<BlockLocation>();
		v.add(new BlockLocation(x + 1, y, z + 1));
		v.add(new BlockLocation(x + 1, y, z));
		v.add(new BlockLocation(x + 1, y, z - 1));
		v.add(new BlockLocation(x, y, z + 1));
		v.add(new BlockLocation(x, y, z - 1));
		v.add(new BlockLocation(x - 1, y, z + 1));
		v.add(new BlockLocation(x - 1, y, z));
		v.add(new BlockLocation(x - 1, y, z - 1));

		v.add(new BlockLocation(x + 1, y + 1, z + 1));
		v.add(new BlockLocation(x + 1, y + 1, z));
		v.add(new BlockLocation(x + 1, y + 1, z - 1));
		v.add(new BlockLocation(x, y + 1, z + 1));
		v.add(new BlockLocation(x, y + 1, z));
		v.add(new BlockLocation(x, y + 1, z - 1));
		v.add(new BlockLocation(x - 1, y + 1, z + 1));
		v.add(new BlockLocation(x - 1, y + 1, z));
		v.add(new BlockLocation(x - 1, y + 1, z - 1));
		return v;
	}

	public static Vector<BlockLocation> getCube(World world, int x, int y, int z) {
		Vector<BlockLocation> v = new Vector<BlockLocation>();

		v.add(new BlockLocation(x, y, z));

		v.add(new BlockLocation(x + 1, y, z + 1));
		v.add(new BlockLocation(x + 1, y, z));
		v.add(new BlockLocation(x + 1, y, z - 1));
		v.add(new BlockLocation(x, y, z + 1));
		v.add(new BlockLocation(x, y, z - 1));
		v.add(new BlockLocation(x - 1, y, z + 1));
		v.add(new BlockLocation(x - 1, y, z));
		v.add(new BlockLocation(x - 1, y, z - 1));

		v.add(new BlockLocation(x + 1, y + 1, z + 1));
		v.add(new BlockLocation(x + 1, y + 1, z));
		v.add(new BlockLocation(x + 1, y + 1, z - 1));
		v.add(new BlockLocation(x, y + 1, z + 1));
		v.add(new BlockLocation(x, y + 1, z));
		v.add(new BlockLocation(x, y + 1, z - 1));
		v.add(new BlockLocation(x - 1, y + 1, z + 1));
		v.add(new BlockLocation(x - 1, y + 1, z));
		v.add(new BlockLocation(x - 1, y + 1, z - 1));

		v.add(new BlockLocation(x + 1, y - 1, z + 1));
		v.add(new BlockLocation(x + 1, y - 1, z));
		v.add(new BlockLocation(x + 1, y - 1, z - 1));
		v.add(new BlockLocation(x, y - 1, z + 1));
		v.add(new BlockLocation(x, y - 1, z));
		v.add(new BlockLocation(x, y - 1, z - 1));
		v.add(new BlockLocation(x - 1, y - 1, z + 1));
		v.add(new BlockLocation(x - 1, y - 1, z));
		v.add(new BlockLocation(x - 1, y - 1, z - 1));
		return v;
	}

}
