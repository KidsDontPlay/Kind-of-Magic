package mrriegel.rwl.gui;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.item.NevPick;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryNevPick implements IInventory {

	private ItemStack[] inv;

	EntityPlayer player;
	int slot;

	ItemStack[] stacks = null;

	boolean invPushed = false;
	ItemStack storedInv = null;

	public InventoryNevPick(EntityPlayer player, int slot) {
		inv = new ItemStack[1];
		this.player = player;
		this.slot = slot;
	}

	public static boolean isNevPick(ItemStack stack) {
		return stack != null && stack.getItem() == ModItems.nevpick;
	}

	ItemStack getStack() {
		ItemStack stack = player.inventory.getStackInSlot(slot);
		if (stack != null)
			storedInv = stack;
		return stack;
	}

	ItemStack[] getInventory() {
		if (stacks != null)
			return stacks;

		ItemStack stack = getStack();
		if (isNevPick(getStack())) {
			stacks = NevPick.loadStacks(stack);
			return stacks;
		}

		return inv;
	}

	public void pushInventory() {
		if (invPushed)
			return;

		ItemStack stack = getStack();
		if (stack == null)
			stack = storedInv;

		if (stack != null) {
			ItemStack[] inv = getInventory();
			NevPick.setStacks(stack, inv);
		}

		invPushed = true;
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}

	}

	@Override
	public String getInventoryName() {
		return "Nev Pick";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		ItemStack stack = player.getCurrentEquippedItem();
		return stack != null && stack.getItem() == ModItems.nevpick;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

}
