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
import mrriegel.rwl.utility.MyUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;

public class NevPick extends ItemPickaxe implements INev {
	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 3, 2222, 10.0F, 5.0F, 1);

	public NevPick() {
		super(MATERIAL);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevpick");
		this.setTextureName(Reference.MOD_ID + ":" + "nevpick");
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
		if (stack.getTagCompound() == null)
			return;
		if (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getStringTagAt(0)
				.equals("")) {
			return;
		}
		switch (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage")) {
		case 0:
			list.add("radius 1");
			break;
		case 1:
			list.add("radius 2");
			break;
		case 2:
			list.add("radius 3");
			break;
		case 3:
			list.add("silk");
			break;
		case 4:
			list.add("fortune");
			break;
		case 5:
			list.add("efficient");
			break;
		case 13:
			list.add("xp");
			break;
		}

	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		if (stack.getTagCompound() == null)
			return super.getDigSpeed(stack, block, meta);
		if (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage") == 5) {
			return super.getDigSpeed(stack, block, meta) * 3.0f;

		} else if (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage") == 1) {
			return super.getDigSpeed(stack, block, meta) / 6.5f;

		} else if (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage") == 2) {
			return super.getDigSpeed(stack, block, meta) / 10.5f;
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

		switch (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
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
			player.getFoodStats().setFoodLevel(
					player.getFoodStats().getFoodLevel() - 1);
			return false;
		case 3:
			silk(stack, x, y, z, player);
			return true;
		case 4:
			fortune(stack, x, y, z, player, 3);
			return true;
		default:
			break;

		}

		return super.onBlockStartBreak(stack, x, y, z, player);
	}

	private void fortune(ItemStack stack, int x, int y, int z,
			EntityPlayer player, int i) {
		MyUtils.breakWithFortune(player.worldObj, x, y, z, 3);

	}

	private void silk(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		MyUtils.breakWithSilk(player.worldObj, x, y, z);

	}

	protected void radius(ItemStack stack, int x, int y, int z,
			EntityPlayer player, int radius) {
		World world = player.worldObj;
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		int direction = -1;
		Vector<BlockLocation> v = new Vector<BlockLocation>();
		MovingObjectPosition mop = MyUtils.raytraceFromEntity(world, player,
				false, 4.5d);

		if (mop == null) {
			return;
		}

		if (!ForgeHooks.isToolEffective(stack, block, meta)
				&& !ForgeHooks.canToolHarvestBlock(block, meta, stack)
				&& !block.equals(Blocks.brick_block)
				&& !(block.getHarvestTool(meta) != null && block
						.getHarvestTool(meta).equals("pickaxe"))
				&& !block.equals(Blocks.quartz_block)) {
			return;
		}
		if (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getStringTagAt(0)
				.equals("")) {
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
			int meta2 = world.getBlockMetadata(b.x, b.y, b.z);
			if (ForgeHooks.isToolEffective(stack, bl, meta2)
					|| ForgeHooks.canToolHarvestBlock(bl, meta2, stack)
					|| (bl.getHarvestTool(meta2) != null && bl.getHarvestTool(
							meta2).equals("pickaxe"))
					|| bl.equals(Blocks.brick_block)
					|| block.equals(Blocks.quartz_block)) {
				MyUtils.breakWithFortune(world, b.x, b.y, b.z, 0);
				stack.setItemDamage(stack.getItemDamage() + 1);
				if (stack.getItemDamage() > MATERIAL.getMaxUses())
					return;
			}
		}

	}
}
