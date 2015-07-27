package mrriegel.rwl.gui;

import mrriegel.rwl.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerNevPick extends Container {

	InventoryNevPick inv;

	private final ItemStack containerstack;
	public boolean needsUpdate;

	public ContainerNevPick(EntityPlayer player, InventoryPlayer invPlayer,
			InventoryNevPick inv) {
		this.inv = inv;
		this.containerstack = player.getHeldItem();

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				addSlotToContainer(new MySlot(inv, j + i * 1, 80 + j * 18,
						48 + i * 18));
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}

	public void writeToNBT() {
		// Use this.containerstack for getting compound
		if (!this.containerstack.hasTagCompound()) {
			this.containerstack.setTagCompound(new NBTTagCompound());
		}
		// Cast to InventoryItem so we can call the method from that class:
		inv.writeToNBT(this.containerstack.getTagCompound());

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return inv.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		System.out.println("slot: " + slot);
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			if (!stackInSlot.getItem().equals(ModItems.cry))
				return null;
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the tileEntity
			if (slot < inv.getSizeInventory()) {
				if (!this.mergeItemStack(stackInSlot, inv.getSizeInventory(),
						36 + inv.getSizeInventory(), true)) {
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
		this.needsUpdate = true;
		return stack;

	}

	@Override
	public ItemStack slotClick(int slotID, int buttonPressed, int flag,
			EntityPlayer player) {
		this.needsUpdate = true;
		return super.slotClick(slotID, buttonPressed, flag, player);

	}

}
