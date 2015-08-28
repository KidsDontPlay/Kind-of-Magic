package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.Item;

public class Resin extends Item {
	public Resin() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "resin");
		this.setTextureName(Reference.MOD_ID + ":" + "resin");
	}
}
