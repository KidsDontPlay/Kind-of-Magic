package mrriegel.rwl.gui;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.item.NevPick;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryNevPick implements IInventory {

	private ItemStack[] inv;

	// EntityPlayer player;
	// int slot;
	public static final int INV_SIZE = 1;

	ItemStack[] stacks = null;

	boolean invPushed = false;
	ItemStack storedInv = null;

	public InventoryNevPick(ItemStack stack) {
		inv = new ItemStack[INV_SIZE];
		this.storedInv = stack;
		if (!storedInv.hasTagCompound()) {
			storedInv.setTagCompound(new NBTTagCompound());
		}
		readFromNBT(storedInv.getTagCompound());
	}

	public void readFromNBT(NBTTagCompound compound) {
		String key = "NevPick";
		if (key == null || key.equals("")) {
			return;
		}
		NBTTagList items = compound.getTagList(key, compound.getId());
		for (int i = 0; i < items.tagCount(); ++i) {
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if (slot >= 0 && slot < getSizeInventory()) {
				inv[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}

	}

	public void writeToNBT(NBTTagCompound compound) {
		String key = "NevPick";
		if (key == null || key.equals("")) {
			return;
		}
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); ++i) {
			if (getStackInSlot(i) != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag(key, items);
	}

	public static boolean isNevPick(ItemStack stack) {
		return stack != null && stack.getItem() == ModItems.nevpick;
	}

	// ItemStack getStack() {
	// ItemStack stack = player.inventory.getStackInSlot(slot);
	// if (stack != null)
	// storedInv = stack;
	// return stack;
	// }
	//
	// ItemStack[] getInventory() {
	// if (stacks != null)
	// return stacks;
	//
	// ItemStack stack = getStack();
	// if (isNevPick(getStack())) {
	// stacks = NevPick.loadStacks(stack);
	// return stacks;
	// }
	//
	// return inv;
	// }
	//
	// public void pushInventory() {
	// if (invPushed)
	// return;
	//
	// ItemStack stack = getStack();
	// if (stack == null)
	// stack = storedInv;
	//
	// if (stack != null) {
	// ItemStack[] inv = getInventory();
	// NevPick.setStacks(stack, inv);
	// }
	//
	// invPushed = true;
	// }

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
		this.markDirty();
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		this.markDirty();

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
		// for (int i = 0; i < this.getSizeInventory(); ++i) {
		// if (this.getStackInSlot(i) != null
		// && this.getStackInSlot(i).stackSize == 0)
		//
		// this.setInventorySlotContents(i, null);
		// }

		for (int i = 0; i < getSizeInventory(); ++i) {
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
				inv[i] = null;
		}
		writeToNBT(storedInv.getTagCompound());
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
