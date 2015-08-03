package mrriegel.rwl.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class Orus extends BlockOre {
	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[6];

	public Orus() {
		super();
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(3.0f);
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "orus");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":" + "orus");

		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];

	}
	
	@Override
    public Item getItemDropped(int par1, Random random, int par2)
    {
        return ModItems.mdust;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return (random.nextInt(3) + 1);
    }

}
