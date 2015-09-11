package mrriegel.rwl.handler;

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
		if (b.equals(Blocks.lapis_ore)
				&& !e.harvester.capabilities.isCreativeMode
				&& !e.world.isRemote
				&& !(e.isSilkTouching || (e.drops.get(0) != null && ItemStack
						.areItemStacksEqual(e.drops.get(0), new ItemStack(b))))
				&& e.world.rand.nextInt(5 - e.fortuneLevel) == 0) {
			ItemStack stack = new ItemStack(ModItems.drop, 1,
					e.world.rand.nextInt(7 - e.fortuneLevel) == 0 ? 1 : 0);
			System.out.println("hier");
			e.drops.add(stack);
			if (e.harvester.getHeldItem() != null
					&& e.harvester.getHeldItem().getItem()
							.equals(ModItems.nevpick)) {
				EntityItem ei = new EntityItem(e.world, e.harvester.posX,
						e.harvester.posY, e.harvester.posZ, stack);
				e.world.spawnEntityInWorld(ei);
			}
		}
	}
}
