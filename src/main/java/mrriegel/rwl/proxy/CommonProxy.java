package mrriegel.rwl.proxy;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.network.IGuiHandler;
import mrriegel.rwl.RWL;
import mrriegel.rwl.gui.ContainerNevPick;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.gui.GuiNevPick;
import mrriegel.rwl.gui.InventoryNevPick;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

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
		case GuiIDs.NEVPICK:
			return new ContainerNevPick(player, player.inventory,
					new InventoryNevPick(player.getHeldItem()));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case GuiIDs.NEVPICK:
			return new GuiNevPick(new ContainerNevPick(player,
					player.inventory,
					new InventoryNevPick(player.getHeldItem())));
		}
		return null;
	}

}
