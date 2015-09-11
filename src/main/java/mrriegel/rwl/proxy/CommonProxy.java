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

public class CommonProxy {
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public static void storeEntityData(String name, NBTTagCompound compound) {
		extendedEntityData.put(name, compound);
	}

	public static NBTTagCompound getEntityData(String name) {
		return extendedEntityData.remove(name);
	}

	public void registerRenderers() {
	}

}
