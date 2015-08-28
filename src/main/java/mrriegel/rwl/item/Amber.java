package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.Item;

public class Amber extends Item {
	public Amber() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "amber");
		this.setTextureName(Reference.MOD_ID + ":" + "amber");
	}
}
