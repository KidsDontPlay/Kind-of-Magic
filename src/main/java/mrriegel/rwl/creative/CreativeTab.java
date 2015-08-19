package mrriegel.rwl.creative;

import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab {

	public static CreativeTabs tab1 = new CreativeTabs(Reference.MOD_ID) {

		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.mazer);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.MOD_NAME;
		}
	};

}
