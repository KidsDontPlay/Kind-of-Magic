package mrriegel.rwl.block;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Mazer extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[6];

	public Mazer() {
		super(Material.rock);
		this.setHardness(3.5f);
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "mazer");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":" + "mazer");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];
	}
}
