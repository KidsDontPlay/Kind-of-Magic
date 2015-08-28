package mrriegel.rwl.item;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemExtinger extends ItemTalisman {
	public ItemExtinger() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "extinger");
		this.setTextureName(Reference.MOD_ID + ":" + "extinger");
	}

	@Override
	public void perform(EntityPlayer player) {
	}

	@SubscribeEvent
	public void ex(LivingHurtEvent event) {
		if (event.source.isFireDamage()
				&& event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
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
			if (contains != -1)
				if (contains != -1) {
					ItemStack bag = player.inventory.mainInventory[contains];
					for (int i = 0; i < 6; i++) {
						try {
							ItemStack invStack = ItemStack
									.loadItemStackFromNBT(bag
											.getTagCompound()
											.getTagList(
													InventoryTaliBag.tagName,
													bag.getTagCompound()
															.getId())
											.getCompoundTagAt(i));
							if (invStack == null)
								continue;

							if (invStack.getItem().equals(ModItems.extinger)) {
								anti = true;
								break;
							}
						} catch (NullPointerException e) {
						}

					}
				}
			if (anti && TaliBag.validCount(player)) {
				event.setCanceled(true);
			}
		}

	}
}
