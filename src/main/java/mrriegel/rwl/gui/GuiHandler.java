package mrriegel.rwl.gui;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryBag;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.inventory.InventoryTaliBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case GuiIDs.NEVTOOL:
			return new ContainerNevTool(player, player.inventory,
					new InventoryNevTool(player.getHeldItem()));
		case GuiIDs.BAG:
			return new ContainerBag(player, player.inventory, new InventoryBag(
					player.getHeldItem()));
		case GuiIDs.TALIBAG:
			return new ContainerTaliBag(player, player.inventory,
					new InventoryTaliBag(player.getHeldItem()));
		case GuiIDs.COMBOBAG:
			InventoryBag b = null;
			for (ItemStack s : player.inventory.mainInventory) {
				if (s != null && s.getItem().equals(ModItems.bag))
					b = new InventoryBag(s);
			}
			if (b == null)
				return null;
			return new ContainerCombo(player, player.inventory,
					new InventoryNevTool(player.getHeldItem()), b);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case GuiIDs.NEVTOOL:
			return new GuiNevTool(new ContainerNevTool(player,
					player.inventory,
					new InventoryNevTool(player.getHeldItem())));
		case GuiIDs.BAG:
			return new GuiBag(new ContainerBag(player, player.inventory,
					new InventoryBag(player.getHeldItem())));
		case GuiIDs.TALIBAG:
			return new GuiTaliBag(new ContainerTaliBag(player,
					player.inventory,
					new InventoryTaliBag(player.getHeldItem())));
		case GuiIDs.COMBOBAG:
			InventoryBag b = null;
			for (ItemStack s : player.inventory.mainInventory) {
				if (s != null && s.getItem().equals(ModItems.bag))
					b = new InventoryBag(s);
			}
			if (b == null)
				return null;
			return new GuiCombo(new ContainerCombo(player, player.inventory,
					new InventoryNevTool(player.getHeldItem()), b));
		}
		return null;
	}

}
