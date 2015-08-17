package mrriegel.rwl.item;

import mrriegel.rwl.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemSprinter extends ItemTalisman {
	public ItemSprinter() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "sprinter");
		this.setTextureName(Reference.MOD_ID + ":" + "sprinter");
	}

	@Override
	public void perform(ItemStack stack, EntityPlayer player) {
		if ((player.onGround || player.capabilities.isFlying)
				&& player.moveForward > 0F
				&& !player.isInsideOfMaterial(Material.water))
			player.moveFlying(0F, 1F, player.capabilities.isFlying ? 0.14F
					: 0.14F * 2);
	}
}
