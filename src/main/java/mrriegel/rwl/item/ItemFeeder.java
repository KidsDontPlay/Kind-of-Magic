package mrriegel.rwl.item;

import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemFeeder extends ItemTalisman {
	public ItemFeeder() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "feeder");
		this.setTextureName(Reference.MOD_ID + ":" + "feeder");
	}

	@Override
	public void perform(EntityPlayer player) {
		if (!player.capabilities.isCreativeMode
				&& player.getFoodStats().needFood()) {
			for (int i = 0; i < player.inventory.mainInventory.length; i++) {
				ItemStack stackfood = player.inventory.mainInventory[i];
				if (stackfood != null
						&& stackfood.getItem() instanceof ItemFood) {
					ItemFood iff = (ItemFood) stackfood.getItem();
					if (stackfood.stackSize != 1) {
						iff.onEaten(stackfood, player.worldObj, player);
						break;
					} else {
						iff.onEaten(stackfood, player.worldObj, player);
						player.inventory.mainInventory[i] = null;
						break;
					}
				}
			}
		}
	}
}
