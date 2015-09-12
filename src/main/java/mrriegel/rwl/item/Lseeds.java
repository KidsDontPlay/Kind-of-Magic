package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;

public class Lseeds extends ItemSeeds{

	public Lseeds(Block p_i45352_1_, Block p_i45352_2_) {
		super(p_i45352_1_, p_i45352_2_);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "lseeds");
		this.setTextureName(Reference.MOD_ID + ":" + "lseeds");
	}

}
