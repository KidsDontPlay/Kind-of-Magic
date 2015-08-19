package mrriegel.rwl.creative;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Ordering;

import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

		@Override
		public void displayAllReleventItems(List p_78018_1_) {
			super.displayAllReleventItems(p_78018_1_);
		}

	};

}
