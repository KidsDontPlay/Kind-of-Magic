package mrriegel.rwl.gui;

import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.item.ItemTalisman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTaliBag extends Container {
	InventoryTaliBag inv;

	public ContainerTaliBag(EntityPlayer player, InventoryPlayer inventory,
			InventoryTaliBag inventoryTaliBag) {
		this.inv = inventoryTaliBag;
		for (int j = 0; j < 6; j++) {
			addSlotToContainer(new TaliSlot(inv, j, 35 + j * 18, 18));
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9,
						8 + j * 18, 51 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			if (i == inventory.currentItem)
				addSlotToContainer(new EvilSlot(inventory, i, 8 + i * 18, 109));
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 109));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return inv.isUseableByPlayer(player);
	}

	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_) {
		inv.updateNBT();
		super.onContainerClosed(p_75134_1_);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			boolean in = false;
			for (ItemStack tmp : inv.getInv()) {
				if (tmp == null)
					continue;
				if (tmp.getItem().equals(stackInSlot.getItem())
						&& !(tmp == stackInSlot)) {
					in = true;
					break;
				}
			}
			if (!(stackInSlot.getItem() instanceof ItemTalisman) || in)
				return null;

			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the tileEntity
			if (slot < inv.getSizeInventory()) {
				if (!this.mergeItemStack(stackInSlot, inv.getSizeInventory(),
						36 + inv.getSizeInventory() + 1, true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0,
					inv.getSizeInventory(), false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
}
