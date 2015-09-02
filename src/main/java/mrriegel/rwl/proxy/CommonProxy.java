package mrriegel.rwl.proxy;

import java.util.HashMap;
import java.util.Map;

import mrriegel.rwl.gui.ContainerBag;
import mrriegel.rwl.gui.ContainerCombo;
import mrriegel.rwl.gui.ContainerNevTool;
import mrriegel.rwl.gui.ContainerTaliBag;
import mrriegel.rwl.gui.GuiBag;
import mrriegel.rwl.gui.GuiCombo;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.gui.GuiNevTool;
import mrriegel.rwl.gui.GuiTaliBag;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryBag;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.inventory.InventoryTaliBag;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public static void storeEntityData(String name, NBTTagCompound compound) {
		extendedEntityData.put(name, compound);
	}

	public static NBTTagCompound getEntityData(String name) {
		return extendedEntityData.remove(name);
	}

	public void registerRenderers() {
	}

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

	public void generateParticles(World world, double x, double y, double z) {
	}

	public void addEffect(EntityFX entityfx) {
	}

}
