package mrriegel.rwl.item;

import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemBreather extends ItemTalisman {
	public ItemBreather() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "breather");
		this.setTextureName(Reference.MOD_ID + ":" + "breather");
	}

	@Override
	public void perform(ItemStack stack, EntityPlayer player) {
		player.setAir(300);

	}
}
