package mrriegel.rwl.block;

import java.util.Random;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PanLeaves extends BlockLeaves {

	public IIcon FancyLeaves;
	public IIcon FastLeaves;

	public PanLeaves() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "panleaves");
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		FancyLeaves = register.registerIcon(Reference.MOD_ID + ":"
				+ "panleaves");
		FastLeaves = register.registerIcon(Reference.MOD_ID + ":"
				+ "panleaves_fast");
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Items.golden_apple;
	}

	@Override
	public boolean isOpaqueCube() {
		return !super.isOpaqueCube();
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess acces, int x, int y,
			int z, int meta) {
		return true;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z,
			int metadata, float chance, int fortune) {
		if (world.isRemote) {
			return;
		}

		if (world.rand.nextInt(50) == 0) {
			Item item = this.getItemDropped(metadata, world.rand, fortune);
			this.dropBlockAsItem(world, x, y, z, new ItemStack(item));

		}
		if (world.rand.nextInt(61) == 0) {
			Item item = ModItems.drop;
			this.dropBlockAsItem(world, x, y, z, new ItemStack(item));

		}
		if (world.rand.nextInt(73) == 0) {
			Item item = ModItems.panstick;
			this.dropBlockAsItem(world, x, y, z, new ItemStack(item));

		}
	}

	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		if (Minecraft.getMinecraft().gameSettings.fancyGraphics)
			return this.FancyLeaves;
		else
			return this.FastLeaves;
	}

	@Override
	public String[] func_150125_e() {
		return null;
	}

}
