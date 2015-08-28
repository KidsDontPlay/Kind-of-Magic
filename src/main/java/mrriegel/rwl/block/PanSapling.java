package mrriegel.rwl.block;

import java.util.List;
import java.util.Random;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.world.PanTree;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PanSapling extends BlockSapling {
	public PanSapling() {
		this.setHardness(0.0F);
		setStepSound(soundTypeGrass);
		setBlockName(Reference.MOD_ID + ":" + "pansapling");
		setCreativeTab(CreativeTab.tab1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":"
				+ "pansapling");
	}

	@Override
	public IIcon getIcon(int side, int metadata) {
		return blockIcon;
	}

	@Override
	public void func_149878_d(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(5) != 3)
			return;
		if (world.isRemote || !TerrainGen.saplingGrowTree(world, rand, x, y, z))
			return;

		WorldGenAbstractTree t = new PanTree();

		t.generate(world, rand, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_,
			List p_149666_3_) {
		p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 7;
	}
}
