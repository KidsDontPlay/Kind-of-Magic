package mrriegel.rwl.handler;

import java.util.Random;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DropEventHandler {
	@SubscribeEvent
	public void drop(HarvestDropsEvent e) {
		if (e.harvester == null)
			return;
		Block b = e.block;
		Random ran = new Random();

		if (b.equals(Blocks.lapis_ore)
				&& !e.harvester.capabilities.isCreativeMode
				&& !e.world.isRemote
				&& !(e.isSilkTouching || (e.drops.get(0) != null && ItemStack
						.areItemStacksEqual(e.drops.get(0), new ItemStack(b))))
				&& ran.nextInt(6 - e.fortuneLevel) == 1) {
			ItemStack stack = new ItemStack(ModItems.drop, 1,
					ran.nextInt(6 - e.fortuneLevel) == 2 ? 1 : 0);

			e.drops.add(stack);
			if (e.harvester.getHeldItem().getItem().equals(ModItems.nevpick)
					&& e.harvester.getHeldItem().getTagCompound()
							.getCompoundTag(InventoryNevTool.tagName)
							.getShort("Damage") == 4) {
				EntityItem ei = new EntityItem(e.world, e.x + 0.5D, e.y,
						e.z + 0.5D, stack);
				e.world.spawnEntityInWorld(ei);
			}
		}
	}
}
