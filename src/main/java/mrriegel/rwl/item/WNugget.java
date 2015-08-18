package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.ItemSimpleFoiled;

public class WNugget extends ItemSimpleFoiled {
	public WNugget() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "wnugget");
		this.setTextureName(Reference.MOD_ID + ":" + "wnugget");
	}

}