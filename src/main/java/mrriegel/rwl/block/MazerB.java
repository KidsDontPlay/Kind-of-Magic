package mrriegel.rwl.block;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.MyUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MazerB extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[6];

	public MazerB() {
		super(Material.rock);
		this.setHardness(3.5f);
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "mazerB");
		setBlockBounds(0.05F, 0.0F, 0.05F, 0.95F, 0.63F, 0.95F);
		setLightOpacity(255);
		useNeighborBrightness = true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			if (i == 0 || i == 1) {
				this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":"
						+ "mazer");
			} else {
				this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":"
						+ "mazerB");
			}
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return !super.isOpaqueCube();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new MazerTile();
	}

	// @Override
	// public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x,
	// int y, int z) {
	// float f = (float) (1.0F-getBlockBoundsMaxY());
	// return AxisAlignedBB.getBoundingBox((float) x + f, y, (float) z + f,
	// (float) (x + 1) - f, (float) (y + 1) - f, (float) (z + 1) - f);
	// }

	@Override
	public boolean renderAsNormalBlock() {
		return !super.renderAsNormalBlock();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z,
			Entity entity) {
		if (entity instanceof EntityFX) {
			if (entity.ticksExisted > 10)
				entity.setDead();
			return;
		}
		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		if (!isConstruct(world, x, y, z) && tile.isActive()) {
			release(world, x, y, z);
			tile.setActive(false);
			System.out.println("disabeld");
			return;
		}
		if (tile.isActive() && entity instanceof EntityItem
				&& entity.posY <= y + 0.9D /*
											 * && !world. isRemote
											 */) {
			EntityItem e = (EntityItem) entity;
			boolean in = false;
			for (int i = 0; i < tile.getInv().length; i++) {
				if (tile.getStackInSlot(i) == null) {
					tile.setInventorySlotContents(i, e.getEntityItem());
					in = true;
					break;
				}
			}
			if (in) {
				entity.setDead();
				world.spawnParticle("instantSpell", x + 0.5D, y + 0.75D,
						z + 0.5D, 0, -0.3D, 0);
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block,
			int meta) {
		// if (world.isRemote) {
		// return;
		// }
		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		if (tile.isActive())
			release(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	private void release(World world, int x, int y, int z) {
		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		for (ItemStack s : tile.getInv()) {
			if (s != null && !world.isRemote)
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5d, y + 1,
						z + 0.5d, s));
		}
		tile.clear();
		if (!world.isRemote)
			world.spawnEntityInWorld(new EntityItem(world, x + 0.5d, y + 1,
					z + 0.5d, new ItemStack(ModItems.relic)));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		// if (world.isRemote) {
		// return false;
		// }
		// if (world.isRemote)
		// ParticleEffects.spawnParticle("fire", x + 0.5d, y + 0.9d, z + 0.5d,
		// 0.0D, 0.05D, 0.0D);
		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		if (!isConstruct(world, x, y, z) && tile.isActive() && !world.isRemote) {

			release(world, x, y, z);
			tile.setActive(false);
			System.out.println("disabeld");
			return false;
		}

		// activate
		if (!player.isSneaking()
				&& player.getCurrentEquippedItem() != null
				&& player.getCurrentEquippedItem().getItem()
						.equals(ModItems.relic) && !tile.isActive()
				&& isConstruct(world, x, y, z)) {
			player.inventory.setInventorySlotContents(
					player.inventory.currentItem, new ItemStack(ModItems.relic,
							player.getCurrentEquippedItem().stackSize - 1));
			tile.setActive(true);
			System.out.println("activated");
			world.markBlockForUpdate(x, y, z);
			return true;

			// fill bottle
		} else if (player.getCurrentEquippedItem() != null
				&& player.getCurrentEquippedItem().getItem()
						.equals(Items.glass_bottle)
				&& player.getActivePotionEffect(Potion.confusion) == null) {
			player.addPotionEffect(new PotionEffect(9, 100, 40));
			player.inventory.setInventorySlotContents(
					player.inventory.currentItem,
					new ItemStack(player.getHeldItem().getItem(), player
							.getCurrentEquippedItem().stackSize - 1));
			if (!world.isRemote)
				world.spawnEntityInWorld(new EntityItem(world, player.posX,
						player.posY, player.posZ, new ItemStack(
								ModItems.bloodie)));
			player.getFoodStats().setFoodLevel(
					player.getFoodStats().getFoodLevel() - 2);
			return true;

			// start
		}/*
		 * else if (!player.isSneaking() && player.getHeldItem() != null &&
		 * player.getHeldItem().getItem().equals(ModItems.catalyst) &&
		 * tile.isActive() && isConstruct(world, x, y, z)) { ItemStack stack =
		 * player.getHeldItem(); for (RitualRecipe r : RitualRecipes.lis) { if
		 * (ItemStack.areItemStacksEqual(stack, r.getCat())) { if
		 * (r.matches(tile.getInv())) { tile.clear(); player.inventory
		 * .setInventorySlotContents( player.inventory.currentItem, new
		 * ItemStack( player.getHeldItem().getItem(),
		 * player.getCurrentEquippedItem().stackSize - 1)); Random ran = new
		 * Random(); for (int i = 0; i < 20; i++) {
		 * world.spawnParticle("happyVillager", x + ran.nextDouble(), y + 0.6d +
		 * ran.nextDouble() / 1.5, z + ran.nextDouble(), 0, 0, 0); } if
		 * (!world.isRemote) { EntityItem ei = new EntityItem(world, x + 0.5d, y
		 * + 0.5d, z + 0.5d, r.getOutput());
		 * 
		 * world.spawnEntityInWorld(ei); ei.setPosition(player.posX,
		 * player.posY, player.posZ); player.addChatMessage(new
		 * ChatComponentText("Done!")); } world.markBlockForUpdate(x, y, z);
		 * return true; } } }
		 * 
		 * // back }
		 */else if (player.isSneaking() && player.getHeldItem() == null
				&& tile.isActive() && isConstruct(world, x, y, z)) {
			boolean item = true;
			for (int i = tile.getInv().length - 1; i >= 0; i--) {
				if (tile.getStackInSlot(i) != null) {
					if (!world.isRemote) {
						EntityItem ei = new EntityItem(world, x + 0.5d,
								y + 0.5d, z + 0.5d, tile.getStackInSlot(i));
						world.spawnEntityInWorld(ei);
						ei.setPosition(player.posX, player.posY, player.posZ);
					}
					tile.setInventorySlotContents(i, null);
					item = false;
					world.markBlockForUpdate(x, y, z);
					return true;
				}
			}
			if (item) {
				if (!world.isRemote) {
					EntityItem ei = new EntityItem(world, x + 0.5d, y + 0.5d,
							z + 0.5d, new ItemStack(ModItems.relic));
					world.spawnEntityInWorld(ei);
					ei.setPosition(player.posX, player.posY, player.posZ);
				}
				tile.setActive(false);
			}

		}
		return false;

	}

	static boolean isConstruct(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		if (!world.getBlock(x, y - 1, z).equals(ModBlocks.mazer)) {
			return false;
		}
		for (BlockLocation bl : MyUtils.getAroundBlocks(world, x, y - 1, z)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		for (BlockLocation bl : MyUtils.getAroundBlocks(world, x + 1, y - 1,
				z + 1)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		for (BlockLocation bl : MyUtils.getAroundBlocks(world, x + 1, y - 1,
				z - 1)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		for (BlockLocation bl : MyUtils.getAroundBlocks(world, x - 1, y - 1,
				z + 1)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		for (BlockLocation bl : MyUtils.getAroundBlocks(world, x - 1, y - 1,
				z - 1)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		if (!world.getBlock(x + 2, y, z + 2).equals(ModBlocks.mazer)) {
			return false;
		}
		if (!world.getBlock(x - 2, y, z + 2).equals(ModBlocks.mazer)) {
			return false;
		}
		if (!world.getBlock(x + 2, y, z - 2).equals(ModBlocks.mazer)) {
			return false;
		}
		if (!world.getBlock(x - 2, y, z - 2).equals(ModBlocks.mazer)) {
			return false;
		}
		if (!world.getBlock(x + 2, y + 1, z + 2).equals(ModBlocks.mazer)) {
			return false;
		}
		if (!world.getBlock(x - 2, y + 1, z + 2).equals(ModBlocks.mazer)) {
			return false;
		}
		if (!world.getBlock(x + 2, y + 1, z - 2).equals(ModBlocks.mazer)) {
			return false;
		}
		if (!world.getBlock(x - 2, y + 1, z - 2).equals(ModBlocks.mazer)) {
			return false;
		}
		if (!world.getBlock(x, y + 2, z).equals(ModBlocks.keep)) {
			return false;
		}
		return true;
	}
}
