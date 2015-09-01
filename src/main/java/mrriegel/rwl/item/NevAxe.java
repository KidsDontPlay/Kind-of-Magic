package mrriegel.rwl.item;

import java.util.List;
import java.util.Vector;

import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.NBTHelper;
import mrriegel.rwl.utility.RWLUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class NevAxe extends ItemAxe implements INev {
	public NevAxe() {
		super(MATERIAL);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevaxe");
		this.setTextureName(Reference.MOD_ID + ":" + "nevaxe");
	}

	@Override
	public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
		if (p_82789_2_.getItem().equals(ModItems.nev)) {
			return true;
		}
		return false;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if (player.isSneaking())
			player.openGui(RWL.instance, GuiIDs.NEVTOOL, world, 0, 0, 0);
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean boo) {
		list.add(NBTHelper.getInt(stack, "exp")
				+ "/"
				+ String.valueOf((NBTHelper.getBoolean(stack, "tier1") || NBTHelper
						.getBoolean(stack, "tier2")) ? 10000 : 1000));
		if (stack.getTagCompound() == null)
			return;

		if (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.toString().equals("{}")) {
			return;
		}

		switch (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage")) {
		case 0:
			list.add("Area I");
			break;
		case 1:
			list.add("Area II");
			break;
		case 2:
			list.add("Area III");
			break;
		case 5:
			list.add("Efficiency");
			break;
		case 8:
			list.add("Timber");
			break;
		}

	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		int plus = 0;
		plus = NBTHelper.getBoolean(stack, "tier1") ? 1 : 0;
		plus = NBTHelper.getBoolean(stack, "tier2") ? 2 : plus;
		if (stack.getTagCompound() == null)
			return super.getDigSpeed(stack, block, meta) + plus;
		if (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage") == 5) {
			return super.getDigSpeed(stack, block, meta) * 3.0f + plus;

		} else if (stack.getTagCompound()
				.getCompoundTag(InventoryNevTool.tagName).getShort("Damage") == 2) {
			return super.getDigSpeed(stack, block, meta) / 6.5f + plus;

		} else if (stack.getTagCompound()
				.getCompoundTag(InventoryNevTool.tagName).getShort("Damage") == 1) {
			return super.getDigSpeed(stack, block, meta) / 3.5f + plus;
		}
		return super.getDigSpeed(stack, block, meta) + plus;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z,
			EntityPlayer player) {
		if (stack.getTagCompound() == null)
			return false;
		if (player.worldObj.isRemote) {
			return false;
		}
		switch (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage")) {
		case 0:
			radius(stack, x, y, z, player, 1);
			return false;
		case 1:
			radius(stack, x, y, z, player, 2);
			return false;
		case 2:
			if (player.getFoodStats().getFoodLevel() <= 2
					&& !player.capabilities.isCreativeMode)
				return false;
			radius(stack, x, y, z, player, 3);
			if (!player.capabilities.isCreativeMode)
				player.getFoodStats().setFoodLevel(
						player.getFoodStats().getFoodLevel() - 1);
			return false;
		case 8:
			if (player.worldObj.getBlock(x, y, z) instanceof BlockLog) {
				chop2(stack, x, y, z, player.worldObj, player,
						player.worldObj.getBlock(x, y, z),
						player.worldObj.getBlockMetadata(x, y, z));
			}
			return false;
		default:
			break;

		}

		return super.onBlockStartBreak(stack, x, y, z, player);
	}

	private void chop2(ItemStack stack, int x, int y, int z, World world,
			EntityPlayer player, Block block, int l) {
		for (BlockLocation bl : RWLUtils.getNeighbors12(world, x, y, z)) {
			if (world.getBlock(bl.x, bl.y, bl.z).getUnlocalizedName()
					.equals(block.getUnlocalizedName())
					&& world.getBlockMetadata(bl.x, bl.y, bl.z) % 4 == l) {
				RWLUtils.breakWithFortune(player, world, bl.x, bl.y, bl.z, 0);
				if (!player.capabilities.isCreativeMode)
					stack.setItemDamage(stack.getItemDamage() + 1);

				if (stack.getItemDamage() > MATERIAL.getMaxUses())
					break;

				chop2(stack, bl.x, bl.y, bl.z, world, player, block, l);
			}

		}
	}

	protected void radius(ItemStack stack, int x, int y, int z,
			EntityPlayer player, int radius) {
		World world = player.worldObj;
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		int direction = -1;
		Vector<BlockLocation> v = new Vector<BlockLocation>();
		MovingObjectPosition mop = RWLUtils.raytraceFromEntity(world, player,
				false, 4.5d);

		if (mop == null) {
			return;
		}
		if (!ForgeHooks.isToolEffective(stack, block, meta)) {
			return;
		}
		if (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.toString().equals("{}")) {
			return;
		}

		direction = mop.sideHit;
		for (int i = -radius; i <= radius; i++) {
			for (int j = -radius; j <= radius; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if (direction == 0 || direction == 1)
					v.add(new BlockLocation(i + x, y, j + z));
				if (direction == 2 || direction == 3)
					v.add(new BlockLocation(i + x, j + y, z));
				if (direction == 4 || direction == 5)
					v.add(new BlockLocation(x, i + y, j + z));
			}
		}
		for (BlockLocation b : v) {
			Block bl = world.getBlock(b.x, b.y, b.z);
			if (ForgeHooks.isToolEffective(stack, bl, meta)) {
				RWLUtils.breakWithFortune(player, world, b.x, b.y, b.z, 0);
				if (!player.capabilities.isCreativeMode)
					stack.setItemDamage(stack.getItemDamage() + 1);
				if (stack.getItemDamage() > MATERIAL.getMaxUses())
					return;
			}
		}

	}
}
