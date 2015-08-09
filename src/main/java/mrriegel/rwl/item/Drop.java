package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.Item;

public class Drop extends Item {
	public Drop() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "drop");
		this.setTextureName(Reference.MOD_ID + ":" + "drop");

	}
}
