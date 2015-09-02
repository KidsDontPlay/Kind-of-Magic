package mrriegel.rwl.gui;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryBag;
import mrriegel.rwl.inventory.InventoryNevTool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerCombo extends Container implements IContainer{
	InventoryNevTool inv1;
	InventoryBag inv2;
	ItemStack con;
	InventoryPlayer playerInventory;

	public ContainerCombo(EntityPlayer player, InventoryPlayer invPlayer,
			InventoryNevTool inv1, InventoryBag inv2) {
		this.inv1 = inv1;
		this.inv2 = inv2;

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				addSlotToContainer(new CrySlot(this, inv1, j + i * 1,
						26 + j * 18, 37 + i * 18));
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				addSlotToContainer(new CryBagSlot(inv2, j + i * 5, 62 + j * 18,
						19 + i * 18));
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			if (i == invPlayer.currentItem)
				addSlotToContainer(new EvilSlot(invPlayer, i, 8 + i * 18, 142));
			addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
		con = invPlayer.getCurrentItem();
		playerInventory = invPlayer;
		if (con != null && con.stackTagCompound != null) {
			ItemStack stack = ItemStack
					.loadItemStackFromNBT(con.stackTagCompound
							.getCompoundTag(inv1.tagName));
			inv1.setInventorySlotContents(0, stack);
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		boolean res = false;
		for (ItemStack s : playerInventory.mainInventory)
			if (s!=null&&s.getItem().equals(ModItems.bag)) {
				res = true;
				break;
			}

		return inv1.isUseableByPlayer(p_75145_1_) && res;
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			if (!stackInSlot.getItem().equals(ModItems.cry))
				return null;
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the tileEntity
			if (slot < inv1.getSizeInventory()) {
				if (!this.mergeItemStack(stackInSlot, inv1.getSizeInventory(),
						36 + inv1.getSizeInventory() + 16, true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0,
					inv1.getSizeInventory(), false)) {
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
	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_) {
		inv2.updateNBT();
		super.onContainerClosed(p_75134_1_);
	}
	
	@Override
	public void onSlotChanged() {
		ItemStack stack = playerInventory.mainInventory[playerInventory.currentItem];

		setTarget(con, inv1.getStackInSlot(0));

		playerInventory.mainInventory[playerInventory.currentItem] = con;
	}

	private void setTarget(ItemStack con2, ItemStack stackInSlot) {
		if (!con.hasTagCompound())
			con.setTagCompound(new NBTTagCompound());
		NBTTagCompound tag = new NBTTagCompound();
		if (stackInSlot != null)
			stackInSlot.writeToNBT(tag);
		con2.getTagCompound().setTag(inv1.tagName, tag);

	}

}
