package mrriegel.rwl.gui;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CrySlot extends Slot {
	InventoryNevTool inv;
	ContainerNevTool con;

	@Override
	public void onSlotChanged() {

		super.onSlotChanged();
		inv.updateItems();

	}

	public CrySlot(ContainerNevTool con,IInventory inv, int p_i1824_2_, int p_i1824_3_,
			int p_i1824_4_) {

		super(inv, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		this.inv = (InventoryNevTool) inv;
		this.con=con;
		this.inv.container=con;

	}

	@Override
	public boolean isItemValid(ItemStack p) {
		return p.getItem().equals(ModItems.cry);
	}

}
