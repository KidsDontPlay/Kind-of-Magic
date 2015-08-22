package mrriegel.rwl.gui;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CryBagSlot extends Slot {
	public CryBagSlot(IInventory inv, int p_i1824_2_, int p_i1824_3_,
			int p_i1824_4_) {

		super(inv, p_i1824_2_, p_i1824_3_, p_i1824_4_);

	}

	@Override
	public boolean isItemValid(ItemStack p) {
		return p.getItem().equals(ModItems.cry);
	}

}
