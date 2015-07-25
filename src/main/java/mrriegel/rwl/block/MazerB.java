package mrriegel.rwl.block;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class MazerB extends Block {
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
}
