package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.Item;

public class AirOreDust extends Item {
	public AirOreDust() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "airoredust");
		this.setTextureName(Reference.MOD_ID + ":" + "airoredust");
	}
}
