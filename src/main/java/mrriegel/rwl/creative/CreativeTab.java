package mrriegel.rwl.creative;

import java.util.List;

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

		@Override
		public void displayAllReleventItems(List p_78018_1_) {
			super.displayAllReleventItems(p_78018_1_);
			// List<ItemStack> copy = new ArrayList<ItemStack>();
			// for (Object s : p_78018_1_)
			// copy.add((ItemStack) s);
			// p_78018_1_.clear();
			// List<Item> lis = ModBlocks.lis;
			// for (Item ii : ModItems.lis)
			// lis.add(ii);
			// for (Item i : lis)
			// for (ItemStack s : copy)
			// if (s.getItem().equals(i))
			// p_78018_1_.add(s);
		}
	};

}
