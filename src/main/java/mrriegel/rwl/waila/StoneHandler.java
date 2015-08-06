package mrriegel.rwl.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StoneHandler implements IWailaDataProvider {

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP arg0, TileEntity arg1,
			NBTTagCompound arg2, World arg3, int arg4, int arg5, int arg6) {
		if (!(arg1 instanceof MazerTile)) {
			return null;
		}

		return arg2;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if (!(accessor.getTileEntity() instanceof MazerTile)) {
			return null;
		}
		MovingObjectPosition mop = accessor.getPosition();
		MazerTile tile = (MazerTile) accessor.getTileEntity();
		currenttip.add("Active: " + tile.isActive());
		return currenttip;
	}

	@Override
	public List<String> getWailaHead(ItemStack arg0, List<String> arg1,
			IWailaDataAccessor arg2, IWailaConfigHandler arg3) {

		return arg1;
	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor arg0,
			IWailaConfigHandler arg1) {
		return null;
	}

	@Override
	public List<String> getWailaTail(ItemStack arg0, List<String> arg1,
			IWailaDataAccessor arg2, IWailaConfigHandler arg3) {
		return arg1;
	}

	public static void callbackRegister(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(new StoneHandler(), MazerTile.class);
		registrar.registerNBTProvider(new StoneHandler(), MazerTile.class);
		registrar.registerHeadProvider(new StoneHandler(), MazerTile.class);

	}

}
