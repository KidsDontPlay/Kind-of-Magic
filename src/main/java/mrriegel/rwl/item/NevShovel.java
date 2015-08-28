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
import mrriegel.rwl.utility.RWLUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;

public class NevShovel extends ItemSpade implements INev {

	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 3, 2222, 10.0F, 5.0F, 1);

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
		if (player.isSneaking())
			player.openGui(RWL.instance, GuiIDs.NEVTOOL, world, 0, 0, 0);
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_,
			World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_,
			int p_77648_7_, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {
		if (!p_77648_2_.isSneaking()) {
			Block k = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
			if (k != null && (k.equals(Blocks.dirt) || k.equals(Blocks.grass))) {
				p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_,
						Blocks.farmland);
				p_77648_1_.setItemDamage(p_77648_1_.getItemDamage() + 1);
			}
		}
		return super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_,
				p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_,
				p_77648_10_);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean boo) {
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
		if (stack.getTagCompound() == null)
			return super.getDigSpeed(stack, block, meta);
		if (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage") == 5) {
			return super.getDigSpeed(stack, block, meta) * 2.5f;

		} else if (stack.getTagCompound()
				.getCompoundTag(InventoryNevTool.tagName).getShort("Damage") == 2) {
			return super.getDigSpeed(stack, block, meta) / 7.5f;

		} else if (stack.getTagCompound()
				.getCompoundTag(InventoryNevTool.tagName).getShort("Damage") == 1) {
			return super.getDigSpeed(stack, block, meta) / 4.5f;

		}
		return super.getDigSpeed(stack, block, meta);
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
			if (player.worldObj.getBlock(x, y, z).canSilkHarvest(
					player.worldObj, player, x, y, z,
					player.worldObj.getBlockMetadata(x, y, z))) {
				silk(stack, x, y, z, player);
				return true;
			} else
				return false;
		default:
			break;

		}

		return super.onBlockStartBreak(stack, x, y, z, player);
	}

	private void silk(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		RWLUtils.breakWithSilk(player.worldObj, x, y, z);
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
				RWLUtils.breakWithFortune(world, b.x, b.y, b.z, 0);
				stack.setItemDamage(stack.getItemDamage() + 1);
				if (stack.getItemDamage() > MATERIAL.getMaxUses())
					return;
			}
		}

	}
}
