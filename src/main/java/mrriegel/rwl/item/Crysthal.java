package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.Item;

public class Crysthal extends Item {

	public Crysthal() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "cry");
		this.setTextureName(Reference.MOD_ID + ":" + "cry");
	}

}
