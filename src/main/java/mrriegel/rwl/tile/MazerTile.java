package mrriegel.rwl.tile;

import java.util.Random;

import mrriegel.rwl.block.MazerB;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class MazerTile extends TileEntity implements IInventory {

	public static final int INV_SIZE = 4;
	public static String tagName = "Alt";
	private ItemStack[] inv;

	private boolean active;
	private boolean processing;
	private int cooldown = 0;
	private ItemStack stack;
	private EntityPlayer player;
	private String name;

	public MazerTile() {
		inv = new ItemStack[INV_SIZE];
		active = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}

	public ItemStack getStack() {
		return stack;
	}

	public void setStack(ItemStack s) {
		this.stack = s;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;
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
		processing = tag.getBoolean("processing");
		cooldown = tag.getInteger("cooldown");
		name = tag.getString("name");

		NBTTagCompound st = (NBTTagCompound) tag.getTag("stack");
		stack = ItemStack.loadItemStackFromNBT(st);
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
		tag.setBoolean("active", active);
		tag.setBoolean("processing", processing);
		tag.setInteger("cooldown", cooldown);
		if (name != null && !name.equals(""))
			tag.setString("name", name);

		NBTTagCompound st = new NBTTagCompound();
		if (stack != null)
			stack.writeToNBT(st);

		tag.setTag("stack", st);
		tag.setTag("Inventory", invList);

	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord,
				this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		double renderExtention = 1.0d;
		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord
				- renderExtention, yCoord - renderExtention, zCoord
				- renderExtention, xCoord + 1 + renderExtention, yCoord + 1
				+ renderExtention, zCoord + 1 + renderExtention);
		return bb;
	}

	public void clear() {
		inv = new ItemStack[INV_SIZE];
	}

	@Override
	public void updateEntity() {
		if (processing) {
			if (cooldown <= 0) {
				cooldown = 0;
				processing = false;
				if (!worldObj.isRemote) {
					EntityItem ei = new EntityItem(worldObj, xCoord + 0.5d,
							yCoord + 1.1d, zCoord + 0.5d, stack);
					worldObj.spawnEntityInWorld(ei);
					if (player == null)
						for (World w : MinecraftServer.getServer().worldServers)
							for (Object o : w.playerEntities) {
								EntityPlayer p = (EntityPlayer) o;
								if (p.getDisplayName().equals(name))
									player = p;
							}
					if (player != null) {
						ei.setPosition(player.posX, player.posY, player.posZ);
						player.addChatMessage(new ChatComponentText("Success"));
					}
				}
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

				stack = null;
				player = null;
				name = null;
			}
			cooldown--;
		}
	}
}