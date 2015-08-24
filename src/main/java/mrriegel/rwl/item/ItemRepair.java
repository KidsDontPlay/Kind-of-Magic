package mrriegel.rwl.item;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemRepair extends ItemTalisman {
	public ItemRepair() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "repair");
		this.setTextureName(Reference.MOD_ID + ":" + "repair");
	}

	@Override
	public void perform(EntityPlayer player) {
		int repair = -1;
		for (int i = 0; i < player.inventory.mainInventory.length; i++) {
			ItemStack s = player.inventory.mainInventory[i];
			if (s == null)
				continue;
			if (s.getItem().equals(ModItems.nev)) {
				repair = i;
				break;
			}
		}
		for (int i = 0; i < player.inventory.mainInventory.length; i++) {
			ItemStack iNev = player.inventory.mainInventory[i];
			if (iNev != null && iNev.getItem() instanceof INev) {
				INev st = (INev) iNev.getItem();
				if (iNev.getItemDamage() > iNev.getMaxDamage() / 2) {
					if (repair == -1)
						return;
					if (player.inventory.mainInventory[repair].stackSize != 1) {
						player.inventory.mainInventory[repair].stackSize--;
						iNev.setItemDamage(iNev.getItemDamage()
								- iNev.getMaxDamage() / 4);
					} else {
						player.inventory.mainInventory[repair].stackSize--;
						iNev.setItemDamage(iNev.getItemDamage()
								- iNev.getMaxDamage() / 4);
						player.inventory.mainInventory[repair] = null;
					}
				}
			}
		}
	}
}
