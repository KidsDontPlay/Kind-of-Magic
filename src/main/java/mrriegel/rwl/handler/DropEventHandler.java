package mrriegel.rwl.handler;

import java.util.Random;

import mrriegel.rwl.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DropEventHandler {
	@SubscribeEvent
	public void drop(HarvestDropsEvent e) {
		Block b = e.block;
		Random ran = new Random();
		if (b.equals(Blocks.lapis_ore)
				&& e.harvester != null
				&& !e.harvester.capabilities.isCreativeMode
				&& !e.world.isRemote
				&& (e.isSilkTouching || (e.drops.get(0) != null && ItemStack
						.areItemStacksEqual(e.drops.get(0), new ItemStack(b))))
				&& ran.nextInt(15) == 1) {
			EntityItem ei = new EntityItem(e.harvester.worldObj, e.x + 0.5D,
					e.y, e.z + 0.5D, new ItemStack(ModItems.drop));
			e.harvester.worldObj.spawnEntityInWorld(ei);
		}
	}
}
