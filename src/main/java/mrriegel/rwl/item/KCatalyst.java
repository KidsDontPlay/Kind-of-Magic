package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class KCatalyst extends Catalyst {
	public KCatalyst() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "kcatalyst");
		this.setTextureName(Reference.MOD_ID + ":" + "kcatalyst");

	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
		return false;
	}
}
