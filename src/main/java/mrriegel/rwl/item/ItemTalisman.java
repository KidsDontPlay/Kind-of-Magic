package mrriegel.rwl.item;

import java.util.List;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemTalisman extends Item {

	public ItemTalisman() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setMaxStackSize(1);
	}

	abstract public void perform(ItemStack stack, EntityPlayer player);

}
