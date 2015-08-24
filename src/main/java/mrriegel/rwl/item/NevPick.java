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
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

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
		case 4:
			list.add("Fortune");
			break;
		case 5:
			list.add("Efficiency");
			break;
		case 13:
			list.add("XP");
			break;
		case 14:
			list.add("Vein");
			break;
		case 15:
			list.add("Detector");
			break;
		}

	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		if (stack.getTagCompound() == null)
			return super.getDigSpeed(stack, block, meta);
		if (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage") == 5) {
			return super.getDigSpeed(stack, block, meta) * 3.0f;

		} else if (stack.getTagCompound()
				.getCompoundTag(InventoryNevTool.tagName).getShort("Damage") == 1) {
			return super.getDigSpeed(stack, block, meta) / 6.5f;

		} else if (stack.getTagCompound()
				.getCompoundTag(InventoryNevTool.tagName).getShort("Damage") == 2) {
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
			silk(stack, x, y, z, player);
			return true;
		case 4:
			fortune(stack, x, y, z, player, 3);
			return true;
		case 14:
			Block block = player.worldObj.getBlock(x, y, z);
			if (block.getUnlocalizedName().contains("ore")
					|| block instanceof BlockOre
					|| block instanceof BlockRedstoneOre) {
				vein(stack, x, y, z, player, player.worldObj.getBlock(x, y, z),
						player.worldObj.getBlockMetadata(x, y, z));
				return true;
			}
			return false;
		case 15:
			Block block2 = player.worldObj.getBlock(x, y, z);
			int meta = player.worldObj.getBlockMetadata(x, y, z);
			if (ForgeHooks.isToolEffective(stack, block2, meta)
					|| block2.equals(Blocks.brick_block)
					|| block2.getMaterial().equals(Material.rock)
					|| (block2.getHarvestTool(meta) != null && block2
							.getHarvestTool(meta).equals("pickaxe"))
					|| block2.equals(Blocks.quartz_block)) {
				return detect(stack, x, y, z, player,
						player.worldObj.getBlock(x, y, z));
			}
			return false;
		default:
			break;

		}

		return super.onBlockStartBreak(stack, x, y, z, player);
	}

	private boolean detect(ItemStack stack, int x, int y, int z,
			EntityPlayer player, Block block) {
		for (int y2 = y; y2 > 0; y2--) {
			for (BlockLocation bl : RWLUtils.getAroundBlocks(player.worldObj,
					x, y2, z)) {
				Block b = player.worldObj.getBlock(bl.x, bl.y, bl.z);
				if (b.getUnlocalizedName().contains("ore")
						|| b instanceof BlockOre
						|| b instanceof BlockRedstoneOre) {
					for (ItemStack s : b.getDrops(player.worldObj, bl.x, bl.y,
							bl.z,
							player.worldObj.getBlockMetadata(bl.x, bl.y, bl.z),
							0)) {
						EntityItem ei = new EntityItem(player.worldObj,
								player.posX, player.posY, player.posZ, s);
						player.worldObj.spawnEntityInWorld(ei);
					}
					player.worldObj.setBlock(bl.x, bl.y, bl.z, Blocks.stone);
					stack.setItemDamage(stack.getItemDamage() + 1);
					return true;

				}
			}
			Block b2 = player.worldObj.getBlock(x, y2, z);
			if (b2.getUnlocalizedName().contains("ore")
					|| b2 instanceof BlockOre || b2 instanceof BlockRedstoneOre) {
				for (ItemStack s : b2.getDrops(player.worldObj, x, y2, z,
						player.worldObj.getBlockMetadata(x, y2, z), 0)) {
					EntityItem ei = new EntityItem(player.worldObj,
							player.posX, player.posY, player.posZ, s);
					player.worldObj.spawnEntityInWorld(ei);
				}
				player.worldObj.setBlock(x, y2, z, Blocks.stone);
				stack.setItemDamage(stack.getItemDamage() + 1);
				return true;

			}
		}
		return false;

	}

	private void vein(ItemStack stack, int x, int y, int z,
			EntityPlayer player, Block block, int i) {
		World world = player.worldObj;
		for (BlockLocation bl : RWLUtils.getCube(world, x, y, z)) {
			if (world.getBlock(bl.x, bl.y, bl.z).getUnlocalizedName()
					.equals(block.getUnlocalizedName())
					&& world.getBlockMetadata(bl.x, bl.y, bl.z) == i) {
				List<ItemStack> ls = world.getBlock(bl.x, bl.y, bl.z).getDrops(
						world, x, y, z, i, 0);
				for (ItemStack s : ls) {
					EntityItem ei = new EntityItem(world, player.posX,
							player.posY, player.posZ, s);
					world.spawnEntityInWorld(ei);
				}
				world.setBlock(bl.x, bl.y, bl.z, Blocks.air);
				int l = world.getBlockMetadata(x, y, z);
				world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block)
						+ (l << 12));
				stack.setItemDamage(stack.getItemDamage() + 1);
				if (stack.getItemDamage() > MATERIAL.getMaxUses())
					break;
				vein(stack, bl.x, bl.y, bl.z, player, block, i);
			}

		}
	}

	private void fortune(ItemStack stack, int x, int y, int z,
			EntityPlayer player, int i) {
		RWLUtils.breakWithFortune(player.worldObj, x, y, z, 3);

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

		if (!ForgeHooks.isToolEffective(stack, block, meta)
				&& !ForgeHooks.canToolHarvestBlock(block, meta, stack)
				&& !block.equals(Blocks.brick_block)
				&& !(block.getHarvestTool(meta) != null && block
						.getHarvestTool(meta).equals("pickaxe"))
				&& !block.equals(Blocks.quartz_block)) {
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
			int meta2 = world.getBlockMetadata(b.x, b.y, b.z);
			if (ForgeHooks.isToolEffective(stack, bl, meta2)
					|| ForgeHooks.canToolHarvestBlock(bl, meta2, stack)
					|| (bl.getHarvestTool(meta2) != null && bl.getHarvestTool(
							meta2).equals("pickaxe"))
					|| bl.equals(Blocks.brick_block)
					|| block.equals(Blocks.quartz_block)) {
				RWLUtils.breakWithFortune(world, b.x, b.y, b.z, 0);
				stack.setItemDamage(stack.getItemDamage() + 1);
				if (stack.getItemDamage() > MATERIAL.getMaxUses())
					return;
			}
		}

	}
}
