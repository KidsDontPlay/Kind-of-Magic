package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Bloodie extends Item{
	
	public Bloodie() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "bloodie");
		this.setTextureName(Reference.MOD_ID + ":" + "bloodie");

	}

}
