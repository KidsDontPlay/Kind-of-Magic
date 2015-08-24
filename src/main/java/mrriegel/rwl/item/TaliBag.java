package mrriegel.rwl.item;

import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TaliBag extends Item {
	public TaliBag() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "tbag");
		this.setTextureName(Reference.MOD_ID + ":" + "tbag");

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		player.openGui(RWL.instance, GuiIDs.TALIBAG, world, 0, 0, 0);
		return stack;

	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player,
			int p_77663_4_, boolean p_77663_5_) {
		// if (world.isRemote)
		// return;
		if (!(player instanceof EntityPlayer))
			return;
		if (stack.getTagCompound() == null)
			return;
		if (!validCount((EntityPlayer) player))
			return;
		for (int i = 0; i < 6; i++) {
			ItemStack invStack = ItemStack
					.loadItemStackFromNBT(stack
							.getTagCompound()
							.getTagList(InventoryTaliBag.tagName,
									stack.getTagCompound().getId())
							.getCompoundTagAt(i));
			if (invStack != null && invStack.getItem() instanceof ItemTalisman) {
				ItemTalisman item = (ItemTalisman) invStack.getItem();
				item.perform((EntityPlayer) player);
			}
		}
	}

	public static boolean validCount(EntityPlayer player) {
		int count = 0;
		for (ItemStack s : ((EntityPlayer) player).inventory.mainInventory) {
			if (s == null)
				continue;
			if (s.getItem().equals(ModItems.tbag))
				count++;
			if (count > 1)
				return false;
		}
		return true;
	}

}
