package mrriegel.rwl.inventory;

import mrriegel.rwl.gui.ContainerNevTool;
import mrriegel.rwl.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryNevTool implements IInventory {

	private ItemStack[] inv;

	public static final int INV_SIZE = 1;
	public static String tagName = "NevTool";

	public ContainerNevTool container;
	public ItemStack storedInv = null;

	public InventoryNevTool(ItemStack stack) {
		inv = new ItemStack[INV_SIZE];
		this.storedInv = stack;
		if (!storedInv.hasTagCompound()) {
			storedInv.setTagCompound(new NBTTagCompound());
		}
	}

	public ItemStack[] getInv() {
		return inv;
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
		return storedInv.getDisplayName();
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
		return stack != null
				&& (stack.getItem() == ModItems.nevpick
						|| stack.getItem() == ModItems.nevshovel
						|| stack.getItem() == ModItems.nevsword || stack
						.getItem() == ModItems.nevaxe);
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

	public void updateItems() {
		ItemStack cry = getStackInSlot(0);

		if (cry == null) {
			container.onSlotChanged();
			return;
		}

		Item item = cry.getItem();
		if (item == null)
			return;

		container.onSlotChanged();
	}

}
