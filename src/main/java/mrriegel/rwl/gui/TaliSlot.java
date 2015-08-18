package mrriegel.rwl.gui;

import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.item.ItemTalisman;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class TaliSlot extends Slot {
	public TaliSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_,
			int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);

	}

	@Override
	public boolean isItemValid(ItemStack p) {
		if (this.inventory instanceof InventoryTaliBag) {
			InventoryTaliBag t = (InventoryTaliBag) this.inventory;
			boolean in = false;
			for (ItemStack tmp : t.getInv()) {
				if (tmp == null)
					continue;
				if (tmp.getItem().equals(p.getItem())) {
					in = true;
					break;
				}
			}
			return p.getItem() instanceof ItemTalisman && !in;
		} else
			System.out.println("this: " + this.inventory);
		return false;
	}
}
