package mrriegel.rwl.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class ItemStepper extends ItemTalisman {
	public ItemStepper() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "stepper");
		this.setTextureName(Reference.MOD_ID + ":" + "stepper");
	}

	@Override
	public void perform(ItemStack stack, EntityPlayer player) {
		if (player.isSneaking())
			player.stepHeight = 0.50001F;
		else
			player.stepHeight = 1F;
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

						if (invStack.getItem().equals(ModItems.stepper)) {
							anti = true;
							break;
						}
					} catch (NullPointerException e) {
					}

				}
			}
			if (!anti)
				player.stepHeight = 0.5F;
		}
	}

}
