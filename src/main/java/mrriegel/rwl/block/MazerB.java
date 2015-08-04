package mrriegel.rwl.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrriegel.rwl.RWL;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MazerB extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[6];

	public MazerB() {
		super(Material.rock);
		this.setHardness(3.5f);
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "mazerB");
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new MazerTile();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x,
			int y, int z) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox((float) x + f, y, (float) z + f,
				(float) (x + 1) - f, (float) (y + 1) - f, (float) (z + 1) - f);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z,
			Entity entity) {
		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		if (!isConstruct(world, x, y, z) && tile.isActive()) {

			release(world, x, y, z);
			tile.setActive(false);
			System.out.println("disabeld");
			return;
		}
		if (entity instanceof EntityItem && entity.posY >= y + 1 + 0.0624F
				&& !world.isRemote) {
			EntityItem e = (EntityItem) entity;
			boolean in = false;
			for (int i = 0; i < tile.getInv().length; i++) {
				if (tile.getInv()[i] == null) {
					tile.getInv()[i] = e.getEntityItem();
					in = true;
					break;
				}
			}
			if (in) {
				entity.isDead = true;
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block,
			int meta) {
		if (world.isRemote) {
			return;
		}
		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		if (tile.isActive())
			release(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	private void release(World world, int x, int y, int z) {
		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		System.out.println("size: " + tile.getInv().length);
		for (ItemStack s : tile.getInv()) {
			if (s != null)
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5d, y + 1,
						z + 0.5d, s));
		}
		world.spawnEntityInWorld(new EntityItem(world, x + 0.5d, y + 1,
				z + 0.5d, new ItemStack(ModItems.relic)));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		if (world.isRemote) {
			return false;
		}

		MazerTile tile = (MazerTile) world.getTileEntity(x, y, z);
		if (!isConstruct(world, x, y, z) && tile.isActive()) {

			release(world, x, y, z);
			tile.setActive(false);
			System.out.println("disabeld");
			return false;
		}

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

		} else if (player.isSneaking() && tile.isActive()
				&& isConstruct(world, x, y, z) && tile.getInv().length == 0) {
			System.out.println("gib");
			tile.setActive(false);
			EntityItem i = new EntityItem(world, x, y + 1, z, new ItemStack(
					ModItems.relic));
			world.spawnEntityInWorld(i);
			i.setPosition(player.posX + 0.5d, player.posY + 1.1d,
					player.posZ + 0.5d);
			System.out.println("extracted");
		} else if (!player.isSneaking()
				&& player.getCurrentEquippedItem() != null
				&& player.getCurrentEquippedItem().getItem()
						.equals(Items.glass_bottle)) {
			player.addPotionEffect(new PotionEffect(9, 200, 40));
			System.out.println("wuerg");
		}
		System.out.println("hop: " + tile.isActive());
		return false;

	}

	private boolean isConstruct(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		if (!world.getBlock(x, y - 1, z).equals(ModBlocks.mazer)) {
			return false;
		}
		for (BlockLocation bl : MyUtils.getAroundBlocks(world, x, y - 1, z)) {
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
		return true;
	}
}
