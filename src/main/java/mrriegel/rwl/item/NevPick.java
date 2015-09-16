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
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class NevPick extends ItemPickaxe implements INev {

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
		case 15:
			return super.getDigSpeed(stack, block, meta) / 7.5f + plus;
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
		Block block = player.worldObj.getBlock(x, y, z);
		int meta = player.worldObj.getBlockMetadata(x, y, z);
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
		case 4:
			if (!player.capabilities.isCreativeMode) {
				fortune(x, y, z, player, 3);
				return true;
			} else
				return false;
		case 14:
			if ((block.getUnlocalizedName().contains("ore")
					|| block instanceof BlockOre || block instanceof BlockRedstoneOre)
					&& RWLUtils.isHarvestable(player.worldObj, stack,
							new BlockLocation(x, y, z), meta)) {
				vein(stack, x, y, z, player, player.worldObj.getBlock(x, y, z),
						player.worldObj.getBlockMetadata(x, y, z));
				return true;
			}
			return false;
		case 15:
			if (RWLUtils.isHarvestable(player.worldObj, stack,
					new BlockLocation(x, y, z), meta)) {
				return detect(x, y, z, player,
						player.worldObj.getBlock(x, y, z));
			}
			return false;
		default:
			break;

		}

		return super.onBlockStartBreak(stack, x, y, z, player);
	}

	private boolean detect(int x, int y, int z, EntityPlayer player, Block block) {
		for (int y2 = y; y2 > 0; y2--) {
			for (BlockLocation bl : RWLUtils.getAroundBlocks(x, y2, z)) {
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
					MinecraftForge.EVENT_BUS
							.post(new BlockEvent.HarvestDropsEvent(x, y2, z,
									player.worldObj, player.worldObj.getBlock(
											x, y2, z), player.worldObj
											.getBlockMetadata(x, y2, z), 0,
									1.0F, block.getDrops(player.worldObj, x,
											y2, z,
											player.worldObj.getBlockMetadata(x,
													y2, z), 0), player, false));
					player.worldObj.setBlock(bl.x, bl.y, bl.z, Blocks.stone);

					RWLUtils.damageItemINev(1, player);
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

				MinecraftForge.EVENT_BUS.post(new BlockEvent.HarvestDropsEvent(
						x, y2, z, player.worldObj, player.worldObj.getBlock(x,
								y2, z), player.worldObj.getBlockMetadata(x, y2,
								z), 0, 1.0F, block.getDrops(player.worldObj, x,
								y2, z,
								player.worldObj.getBlockMetadata(x, y2, z), 0),
						player, false));
				player.worldObj.setBlock(x, y2, z, Blocks.stone);
				RWLUtils.damageItemINev(1, player);
				return true;

			}
		}
		return false;

	}

	private void vein(ItemStack stack, int x, int y, int z,
			EntityPlayer player, Block block, int i) {
		World world = player.worldObj;
		for (BlockLocation bl : RWLUtils.getCube(x, y, z)) {
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

				MinecraftForge.EVENT_BUS.post(new BlockEvent.HarvestDropsEvent(
						bl.x, bl.y, bl.z, player.worldObj, player.worldObj
								.getBlock(bl.x, bl.y, bl.z), player.worldObj
								.getBlockMetadata(bl.x, bl.y, bl.z), 0, 1.0F,
						block.getDrops(player.worldObj, bl.x, bl.y, bl.z,
								player.worldObj.getBlockMetadata(bl.x, bl.y,
										bl.z), 0), player, false));
				world.setBlock(bl.x, bl.y, bl.z, Blocks.air);
				int l = world.getBlockMetadata(x, y, z);
				world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block)
						+ (l << 12));
				if (RWLUtils.damageItemINev(1, player))
					return;
				vein(stack, bl.x, bl.y, bl.z, player, block, i);
			}

		}
	}

	private void fortune(int x, int y, int z, EntityPlayer player, int i) {
		RWLUtils.breakWithFortune(player, player.worldObj, x, y, z, i);
		RWLUtils.damageItemINev(1, player);
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
			int meta2 = world.getBlockMetadata(b.x, b.y, b.z);
			if (RWLUtils.isHarvestable(world, stack, b, meta2)) {
				RWLUtils.breakWithFortune(player, world, b.x, b.y, b.z, 0);
				if (RWLUtils.damageItemINev(1, player))
					break;
			}
		}

	}
}
