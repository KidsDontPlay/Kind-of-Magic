package mrriegel.rwl.item;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemFlyer extends ItemTalisman {
	public ItemFlyer() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "flyer");
		this.setTextureName(Reference.MOD_ID + ":" + "flyer");
	}

	@Override
	public void perform(EntityPlayer player) {
		player.capabilities.allowFlying = true;
	}

	@SubscribeEvent
	public void anti(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			int contains = -1;
			for (int i = 0; i < player.inventory.mainInventory.length; i++) {
				if (player.inventory.mainInventory[i] == null)
					continue;
				if (player.inventory.mainInventory[i].getItem().equals(
						ModItems.tbag)) {
					contains = i;
					break;
				}
			}
			boolean anti = false;
			if (contains != -1) {
				ItemStack bag = player.inventory.mainInventory[contains];
				for (int i = 0; i < 15; i++) {
					try {
						ItemStack invStack = ItemStack.loadItemStackFromNBT(bag
								.getTagCompound()
								.getTagList(InventoryTaliBag.tagName,
										bag.getTagCompound().getId())
								.getCompoundTagAt(i));
						if (invStack == null)
							continue;

						if (invStack.getItem().equals(ModItems.flyer)) {
							anti = true;
							break;
						}
					} catch (NullPointerException e) {
					}

				}
			}
			if ((!anti && !player.capabilities.isCreativeMode)
					|| !TaliBag.validCount(player)) {
				player.capabilities.allowFlying = false;
				if (player.capabilities.isFlying)
					player.capabilities.isFlying = false;
			}
		}
	}
}
