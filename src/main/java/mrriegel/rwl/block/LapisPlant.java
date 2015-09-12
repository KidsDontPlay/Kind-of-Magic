package mrriegel.rwl.block;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LapisPlant extends BlockCrops {
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public LapisPlant() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "lplant");
	}

	@Override
	protected Item func_149866_i() {
		return ModItems.lseeds;
	}

	@Override
	protected Item func_149865_P() {
		return Items.dye;
	}

	@Override
	public int quantityDropped(Random p_149745_1_) {
		return p_149745_1_.nextInt(2) + 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		icons = new IIcon[3];

		for (int i = 0; i < icons.length; ++i) {
			icons[i] = p_149651_1_.registerIcon(Reference.MOD_ID + ":"
					+ "lplant" + "_stage_" + i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		if (p_149691_2_ < 0 || p_149691_2_ > 7) {
			p_149691_2_ = 7;
		}
		int icon=0;
		if (p_149691_2_ >= 0 && p_149691_2_ <= 2)
			icon = 0;
		else if (p_149691_2_ >= 3 && p_149691_2_ <= 6)
			icon = 1;
		else if (p_149691_2_ == 7)
			icon = 2;
		return icons[icon];
	}

	@Override
	public int damageDropped(int p_149692_1_) {
		return 4;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z,
			int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		for (ItemStack s : super.getDrops(world, x, y, z, metadata, fortune))
			if (!s.getItem().equals(func_149866_i()))
				ret.add(s);
		if (metadata >= 7)
			ret.add(new ItemStack(this.func_149866_i(),
					world.rand.nextInt(4) == 0 ? 2 : 1));
		else
			ret.add(new ItemStack(this.func_149866_i()));
		return ret;
	}
}
