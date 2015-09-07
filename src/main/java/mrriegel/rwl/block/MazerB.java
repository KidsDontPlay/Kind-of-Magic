package mrriegel.rwl.block;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.RWLUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
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
		setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F - 0.0625F, 0.63F,
				1.0F - 0.0625F);
		setLightOpacity(255);
		useNeighborBrightness = true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			if (i == 0 || i == 1) {
				this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":"
						+ "mazer_top");
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

		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		if (!isConstruct(world, x, y, z) && tile.isActive()) {
			release(world, x, y, z);
			tile.setActive(false);
			return;
		}
		if (tile.isActive() && entity instanceof EntityItem
				&& entity.posY >= y + 0.5D && entity.posY <= y + 0.78D) {

			EntityItem e = (EntityItem) entity;
			if (e.getEntityItem().stackSize != 1) {
				return;
			}
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
						z + 0.5D, 0, -0.4D, 0);
			}
			world.markBlockForUpdate(x, y, z);
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

		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);

		if (!isConstruct(world, x, y, z) && tile.isActive()) {
			if (!world.isRemote)
				release(world, x, y, z);
			tile.setActive(false);
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
			world.markBlockForUpdate(x, y, z);
			return true;

			// fill bottle
		} else if (player.getCurrentEquippedItem() != null
				&& player.getCurrentEquippedItem().getItem()
						.equals(Items.glass_bottle)
				&& player.getActivePotionEffect(Potion.confusion) == null) {
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 150,
					40));
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

			// back
		} else if (player.isSneaking() && player.getHeldItem() == null
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

	public static boolean isConstruct(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		if (!world.getBlock(x, y - 1, z).equals(ModBlocks.mazer)) {
			return false;
		}
		for (BlockLocation bl : RWLUtils.getAroundBlocks(x, y - 1, z)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		for (BlockLocation bl : RWLUtils.getAroundBlocks(x + 1, y - 1, z + 1)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		for (BlockLocation bl : RWLUtils.getAroundBlocks(x + 1, y - 1, z - 1)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		for (BlockLocation bl : RWLUtils.getAroundBlocks(x - 1, y - 1, z + 1)) {
			if (!world.getBlock(bl.x, bl.y, bl.z).equals(ModBlocks.mazer)) {
				return false;
			}
		}
		for (BlockLocation bl : RWLUtils.getAroundBlocks(x - 1, y - 1, z - 1)) {
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
