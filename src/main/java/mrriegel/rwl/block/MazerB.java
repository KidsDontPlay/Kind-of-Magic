package mrriegel.rwl.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
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
		if (entity instanceof EntityItem && entity.posY >= y + 1 + 0.0624F
				&& !world.isRemote) {
			System.out.println("block: " + y);
			System.out.println("item: " + entity.posY);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		MazerTile te = (MazerTile) world.getTileEntity(x, y, z);
		EntityFX candleFlame = new EntityFlameFX(world, x+0.5, y + 1.2, z+0.5, 0.0D,
				0.0D, 0.0D);
		Minecraft.getMinecraft().effectRenderer.addEffect(candleFlame);
		if (world.isRemote) {
			return false;
		}
		if (!player.isSneaking()
				&& player.getCurrentEquippedItem() != null
				&& player.getCurrentEquippedItem().getItem()
						.equals(ModItems.bloodie) && !te.isActive()) {
			player.inventory.setInventorySlotContents(
					player.inventory.currentItem, new ItemStack(
							ModItems.bloodie,
							player.getCurrentEquippedItem().stackSize - 1));
			te.setActive(true);
			System.out.println("inserted");

		} else if (player.isSneaking() && te.isActive()) {
			System.out.println("gib");
			te.setActive(false);
			EntityItem i = new EntityItem(world, x, y + 1, z, new ItemStack(
					ModItems.bloodie));
			world.spawnEntityInWorld(i);
			i.setPosition(player.posX, player.posY, player.posZ);
			System.out.println("extracted");
		} else if (!player.isSneaking()
				&& player.getCurrentEquippedItem() != null
				&& player.getCurrentEquippedItem().getItem()
						.equals(Items.glass_bottle)) {
			player.addPotionEffect(new PotionEffect(9, 200, 40));
			System.out.println("wuerg");
		}
		System.out.println("hop: " + te.isActive());
		return false;

	}

}
