package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.Item;

public class BloodRelic extends Item {

	public BloodRelic() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "relic");
		this.setTextureName(Reference.MOD_ID + ":" + "relic");
	}
}
