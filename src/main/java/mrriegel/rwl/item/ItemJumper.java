package mrriegel.rwl.item;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemJumper extends ItemTalisman {
	public ItemJumper() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "jumper");
		this.setTextureName(Reference.MOD_ID + ":" + "jumper");
	}

	@Override
	public void perform(EntityPlayer player) {
	}

	@SubscribeEvent
	public void act(LivingJumpEvent event) {
		if (!(event.entity instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) event.entity;
		if (!TaliBag.validCount(player))
			return;
		ItemStack stack = null;
		for (ItemStack s : player.inventory.mainInventory) {
			if (s == null)
				continue;
			if (s.getItem().equals(ModItems.tbag)) {
				stack = s;
				break;
			}
		}
		if (stack == null || stack.getTagCompound() == null)
			return;
		for (int i = 0; i < 15; i++) {
			ItemStack invStack = ItemStack
					.loadItemStackFromNBT(stack
							.getTagCompound()
							.getTagList(InventoryTaliBag.tagName,
									stack.getTagCompound().getId())
							.getCompoundTagAt(i));
			if (invStack != null && invStack.getItem() instanceof ItemJumper) {
				player.motionY += 0.19;

			}
		}
	}
}
