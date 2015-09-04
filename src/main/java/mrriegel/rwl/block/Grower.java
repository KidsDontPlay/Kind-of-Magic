package mrriegel.rwl.block;

import cofh.core.entity.CoFHFakePlayer;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.GrowerTile;
import mrriegel.rwl.utility.RWLUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Grower extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[6];

	public Grower() {
		super(Material.rock);
		this.setHardness(2.5f);
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "grower");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":" + "grower");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new GrowerTile();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_,
			int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return true;
	}
}
