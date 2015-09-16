package mrriegel.rwl.utility;

import java.util.ArrayList;
import java.util.Vector;

import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.item.INev;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.world.BlockEvent;

public class RWLUtils {
	public static ArrayList<BlockLocation> getAroundBlocks(int x, int y, int z) {
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
		int meta = world.getBlockMetadata(x, y, z);

		if (block.getMaterial() == Material.air) {
			return false;
		} else {
			int l = world.getBlockMetadata(x, y, z);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block)
					+ (l << 12));

			block.dropBlockAsItem(world, x, y, z, l, fortune);
			for (int i = 0; i < block.getExpDrop(world, meta, fortune); i++) {
				world.spawnEntityInWorld(new EntityXPOrb(world, x + 0.5d, y,
						z + 0.5d,
						EntityXPOrb.getXPSplit(world.rand.nextInt(10) + 1)));
			}
			MinecraftForge.EVENT_BUS.post(new BlockEvent.HarvestDropsEvent(x,
					y, z, world, block, l, fortune, 1.0F, block.getDrops(world,
							x, y, z, l, fortune), player, false));
			MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(x, y, z,
					world, block, l, player));
			return world.setBlock(x, y, z, Blocks.air);
		}
	}

	public static boolean breakWithSilk(EntityPlayer player, World world,
			int x, int y, int z) {
		Block block = world.getBlock(x, y, z);

		if (!block.canSilkHarvest(world, player, x, y, z,
				world.getBlockMetadata(x, y, z)))
			return false;
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

	public static BlockLocation getNeighborBlock(int x, int y, int z, int side) {
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

	public static Vector<BlockLocation> getNeighborBlocks(int x, int y, int z) {
		Vector<BlockLocation> vec = new Vector<BlockLocation>();
		for (int i = 0; i < 6; i++) {
			vec.add(getNeighborBlock(x, y, z, i));
		}
		return vec;
	}

	public static Vector<BlockLocation> getNeighbors12(int x, int y, int z) {
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

	public static Vector<BlockLocation> getCube(int x, int y, int z) {
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

	public static boolean insert(ItemStack stack, IInventory inv) {
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack in = inv.getStackInSlot(i);
			if (in != null && in.isItemEqual(stack)
					&& in.stackSize < inv.getInventoryStackLimit()
					&& in.stackSize < stack.getItem().getItemStackLimit()) {
				inv.setInventorySlotContents(i, new ItemStack(in.getItem(),
						in.stackSize + 1, in.getItemDamage()));
				return true;
			}
		}
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) == null) {
				inv.setInventorySlotContents(i, stack);
				return true;
			}
		}
		return false;
	}

	public static boolean canInsert(ItemStack stack, IInventory inv) {
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack in = inv.getStackInSlot(i);
			if (in != null && in.isItemEqual(stack)
					&& in.stackSize < inv.getInventoryStackLimit()
					&& in.stackSize < stack.getItem().getItemStackLimit())
				return true;
		}
		for (int i = 0; i < inv.getSizeInventory(); i++)
			if (inv.getStackInSlot(i) == null)
				return true;
		return false;
	}

	public static ForgeDirection getForgeDirectionOfBlock(BlockLocation block,
			BlockLocation neighbor) {
		boolean m = false;
		for (BlockLocation bl : getNeighborBlocks(block.x, block.y, block.z)) {
			if (bl.x == neighbor.x && bl.y == neighbor.y && bl.z == neighbor.z) {
				m = true;
			}
		}
		if (!m)
			return null;
		if (block.y == neighbor.y + 1)
			return ForgeDirection.DOWN;
		if (block.y == neighbor.y - 1)
			return ForgeDirection.UP;
		if (block.z == neighbor.z + 1)
			return ForgeDirection.NORTH;
		if (block.z == neighbor.z - 1)
			return ForgeDirection.UP;
		if (block.x == neighbor.x + 1)
			return ForgeDirection.WEST;
		if (block.x == neighbor.x - 1)
			return ForgeDirection.EAST;
		return null;
	}

	public static EntityPlayer name2player(String name) {
		EntityPlayer player = null;
		for (World w : MinecraftServer.getServer().worldServers)
			for (Object o : w.playerEntities) {
				EntityPlayer p = (EntityPlayer) o;
				if (p.getDisplayName().equals(name))
					player = p;
			}
		return player;
	}

	public static boolean damageItemINev(int i, EntityPlayer player) {
		ItemStack stack = player.getHeldItem();
		if (stack == null)
			return true;
		if (!(player instanceof EntityPlayer)
				|| !((EntityPlayer) player).capabilities.isCreativeMode) {
			if (stack.isItemStackDamageable()) {
				if (stack.attemptDamageItem(i, player.getRNG())) {
					player.renderBrokenItemStack(stack);
					if (stack.getItem() instanceof INev
							&& stack.getTagCompound() != null
							&& !stack.getTagCompound()
									.getCompoundTag(InventoryNevTool.tagName)
									.toString().equals("{}")) {
						MinecraftForge.EVENT_BUS
								.post(new PlayerDestroyItemEvent(player, stack));
						return true;
					} else
						player.inventory.setInventorySlotContents(
								player.inventory.currentItem, (ItemStack) null);
					MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(
							player, stack));
				}
			}
		}
		return false;
	}

	public static boolean isHarvestable(World world, ItemStack stack,
			BlockLocation bl, int meta) {
		if (!(stack.getItem() instanceof ItemTool))
			return false;
		ItemTool tool = (ItemTool) stack.getItem();
		Block block = world.getBlock(bl.x, bl.y, bl.z);
		if (block == null)
			return false;
		if (block.getHarvestLevel(meta) > tool.func_150913_i()
				.getHarvestLevel()
				|| block.getBlockHardness(world, bl.x, bl.y, bl.z) < 0.0F)
			return false;
		if (tool instanceof ItemPickaxe) {
			if (block.getMaterial().equals(Material.glass)
					|| block.getMaterial().equals(Material.rock)
					|| block.getMaterial().equals(Material.iron)
					|| block.getMaterial().equals(Material.anvil)
					|| block.getMaterial().equals(Material.ice)
					|| block.getMaterial().equals(Material.packedIce))
				return true;
		} else if (tool instanceof ItemSpade) {
			if (block.getMaterial().equals(Material.grass)
					|| block.getMaterial().equals(Material.ground)
					|| block.getMaterial().equals(Material.sand)
					|| block.getMaterial().equals(Material.snow)
					|| block.getMaterial().equals(Material.craftedSnow)
					|| block.getMaterial().equals(Material.clay))
				return true;
		} else if (tool instanceof ItemAxe) {
			if (block.getMaterial().equals(Material.wood))
				return true;
		}
		return false;
	}
}
