package mrriegel.rwl.block;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MazerB extends BlockContainer {
	private IIcon[] icons = new IIcon[6];

	public MazerB() {
		super(Material.rock);
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "mazerB");
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":" + "mazerB");

		}

	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new MazerTile();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		MazerTile te = (MazerTile) world.getTileEntity(x, y, z);
		if (world.isRemote) {
			return false;
		}
		if (!player.isSneaking()
				&& player.getCurrentEquippedItem() != null
				&& player.getCurrentEquippedItem().getItem()
						.equals(ModItems.bloodie) && !te.isActive()) {
			// player.inventory.setInventorySlotContents(
			// player.inventory.currentItem, new ItemStack(Blocks.air));
			te.setActive(true);

		} else if (player.isSneaking() && te.isActive()) {
			System.out.println("hier");
			te.setActive(false);
			player.inventory.addItemStackToInventory(new ItemStack(
					ModItems.bloodie));
		}
		System.out.println("hop: " + te.isActive());
		return this.blockConstructorCalled;

	}

}
