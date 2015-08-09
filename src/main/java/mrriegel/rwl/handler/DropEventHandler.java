package mrriegel.rwl.handler;

import java.util.Random;

import mrriegel.rwl.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DropEventHandler {

	@SubscribeEvent
	public void drop(BreakEvent e) {
		Block b = e.block;
		Random ran = new Random();
		if (b.equals(Blocks.lapis_ore) && e.getPlayer() != null
				&& !e.getPlayer().worldObj.isRemote && ran.nextInt(10) == 1) {
			EntityItem ei = new EntityItem(e.getPlayer().worldObj, e.x + 0.5D,
					e.y, e.z + 0.5D, new ItemStack(ModItems.drop));
			e.getPlayer().worldObj.spawnEntityInWorld(ei);
		}
	}
}
