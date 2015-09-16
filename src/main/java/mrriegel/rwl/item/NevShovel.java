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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class NevShovel extends ItemSpade implements INev {

	public NevShovel() {
		super(MATERIAL);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevshovel");
		this.setTextureName(Reference.MOD_ID + ":" + "nevshovel");
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
		boolean res = false;
		for (ItemStack s : player.inventory.mainInventory)
			if (s != null && s.getItem().equals(ModItems.bag)) {
				res = true;
				break;
			}

		if (player.isSneaking())
			if (!res)
				player.openGui(RWL.instance, GuiIDs.NEVTOOL, world, 0, 0, 0);
			else
				player.openGui(RWL.instance, GuiIDs.COMBOBAG, world, 0, 0, 0);
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int p_77648_7_, float p_77648_8_,
			float p_77648_9_, float p_77648_10_) {
		if (!player.isSneaking()) {
			Block k = world.getBlock(x, y, z);
			if (k != null && (k.equals(Blocks.dirt) || k.equals(Blocks.grass))) {
				world.setBlock(x, y, z, Blocks.farmland);
				RWLUtils.damageItemINev(1, player);
			}
		}
		return super.onItemUse(stack, player, world, x, y, z, p_77648_7_,
				p_77648_8_, p_77648_9_, p_77648_10_);
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
		case 3:
			list.add("Silk");
			break;
		case 5:
			list.add("Efficiency");
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
				.toString().equals("{}"))
			return super.getDigSpeed(stack, block, meta) + plus;
		switch (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage")) {
		case 0:
			return super.getDigSpeed(stack, block, meta) / 2.5f + plus;
		case 1:
			return super.getDigSpeed(stack, block, meta) / 6.5f + plus;
		case 2:
			return super.getDigSpeed(stack, block, meta) / 10.5f + plus;
		case 5:
			return super.getDigSpeed(stack, block, meta) * 3.0f + plus;
		default:
			return super.getDigSpeed(stack, block, meta) + plus;
		}
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
		case 3:
			if (silk(x, y, z, player)) {
				RWLUtils.damageItemINev(1, player);
				return true;
			} else
				return false;
		default:
			break;

		}

		return super.onBlockStartBreak(stack, x, y, z, player);
	}

	private boolean silk(int x, int y, int z, EntityPlayer player) {
		return RWLUtils.breakWithSilk(player, player.worldObj, x, y, z);

	}

	protected void radius(ItemStack stack, int x, int y, int z,
			EntityPlayer player, int radius) {
		World world = player.worldObj;
		int meta = world.getBlockMetadata(x, y, z);
		int direction = -1;
		Vector<BlockLocation> v = new Vector<BlockLocation>();
		MovingObjectPosition mop = RWLUtils.raytraceFromEntity(world, player,
				false, 4.5d);

		if (mop == null) {
			return;
		}
		if (!RWLUtils.isHarvestable(world, stack, new BlockLocation(x, y, z),
				meta)) {
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
			if (RWLUtils.isHarvestable(world, stack, b, meta)) {
				RWLUtils.breakWithFortune(player, world, b.x, b.y, b.z, 0);
				if (RWLUtils.damageItemINev(1, player))
					break;
			}
		}

	}
}
