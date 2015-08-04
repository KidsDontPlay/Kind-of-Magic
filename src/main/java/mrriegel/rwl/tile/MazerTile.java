package mrriegel.rwl.tile;

import java.util.ArrayList;

import mrriegel.rwl.block.MazerB;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class MazerTile extends TileEntity implements IInventory {

	public static final int INV_SIZE = 4;
	public static String tagName = "Alt";
	private ItemStack[] inv;

	private boolean active;

	public MazerTile() {
		inv = new ItemStack[INV_SIZE];
		active = false;
	}

	public ItemStack[] getInv() {
		return inv;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return new MazerB().getLocalizedName();
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
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
				&& player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
						zCoord + 0.5) < 64;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList invList = tag.getTagList("Inventory",
				Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < invList.tagCount(); i++) {
			NBTTagCompound stackTag = invList.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot");

			if (slot >= 0 && slot < inv.length)
				inv[slot] = ItemStack.loadItemStackFromNBT(stackTag);
		}
		active = tag.getBoolean("active");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagList invList = new NBTTagList();
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				inv[i].writeToNBT(stackTag);
				invList.appendTag(stackTag);
			}
		}

		tag.setTag("Inventory", invList);

		tag.setBoolean("active", active);
	}

	public void clear() {
		inv = new ItemStack[INV_SIZE];
	}
}