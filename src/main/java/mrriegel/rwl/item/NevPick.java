package mrriegel.rwl.item;

import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class NevPick extends ItemPickaxe {
	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 2, 1999, 10.0F, 2.0F, 14);
	private static final String TAG_ITEMS = "InvItems";
	private static final String TAG_SLOT = "Slot";

	public NevPick() {
		super(MATERIAL);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevpick");
		this.setTextureName(Reference.MOD_ID + ":" + "nevpick");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		player.openGui(RWL.instance, GuiIDs.NEVPICK, world, 0, 0, 0);
		return stack;
	}

	public static ItemStack[] loadStacks(ItemStack stack) {
		NBTTagList var2 = NBTHelper.getList(stack, TAG_ITEMS, 10, false);
		ItemStack[] inventorySlots = new ItemStack[16];
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = var2.getCompoundTagAt(var3);
			byte var5 = var4.getByte(TAG_SLOT);
			if (var5 >= 0 && var5 < inventorySlots.length)
				inventorySlots[var5] = ItemStack.loadItemStackFromNBT(var4);
		}

		return inventorySlots;
	}

	public static void setStacks(ItemStack stack, ItemStack[] inventorySlots) {
		NBTTagList var2 = new NBTTagList();
		for (int var3 = 0; var3 < inventorySlots.length; ++var3)
			if (inventorySlots[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte(TAG_SLOT, (byte) var3);
				inventorySlots[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}

		NBTHelper.setList(stack, TAG_ITEMS, var2);
	}

}
