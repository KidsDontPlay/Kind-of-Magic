package mrriegel.rwl.item;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemVision extends ItemTalisman {
	public ItemVision() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "vision");
		this.setTextureName(Reference.MOD_ID + ":" + "vision");
	}

	@Override
	public void perform(ItemStack stack, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, -4));
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

						if (invStack.getItem().equals(ModItems.vision)) {
							anti = true;
							break;
						}
					} catch (NullPointerException e) {
					}

				}
			}
			if ((!anti && player.isPotionActive(Potion.nightVision.id))
					|| !TaliBag.validCount(player)) {
				PotionEffect nightVision = null;
				if (player.isPotionActive(Potion.nightVision.id)) {
					nightVision = player
							.getActivePotionEffect(Potion.nightVision);
				}
				if (nightVision != null && nightVision.getAmplifier() == -4) {
					if (player.worldObj.isRemote) {
						player.removePotionEffectClient(Potion.nightVision.id);
					} else {
						player.removePotionEffect(Potion.nightVision.id);
					}
				}
			}
		}
	}
}
