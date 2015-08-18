package mrriegel.rwl.item;

import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemCooler extends ItemTalisman {
	public ItemCooler() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "cooler");
		this.setTextureName(Reference.MOD_ID + ":" + "cooler");
	}

	@Override
	public void perform(ItemStack stack, EntityPlayer player) {
		for (int i = 0; i < player.inventory.mainInventory.length; i++) {
			ItemStack s = player.inventory.mainInventory[i];
			if (s != null && s.getItem() instanceof ItemEdelstein) {
				ItemEdelstein edel = (ItemEdelstein) s.getItem();
				if (NBTHelper.getInt(s, "cooldown") != 0)
					NBTHelper.setInteger(s, "cooldown",
							NBTHelper.getInt(s, "cooldown") - 1);

			}
		}
	}
}
