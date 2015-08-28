package mrriegel.rwl.block;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class PanLog extends BlockLog {

	private IIcon _iconLogTop;
	private IIcon _iconLogSide;

	public PanLog() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "panlog");
		this.setHarvestLevel("axe", 0);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		_iconLogSide = register.registerIcon(Reference.MOD_ID + ":"
				+ "panlog_side");
		_iconLogTop = register.registerIcon(Reference.MOD_ID + ":"
				+ "panlog_top");

	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int par1) {
		return _iconLogSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int par1) {
		return _iconLogTop;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z,
			int metadata, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata,
				fortune);

		int dropCount = world.rand.nextInt(2 + fortune) + 1;

		return ret;
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x,
			int y, int z, int metadata) {
		return false;
	}
}
