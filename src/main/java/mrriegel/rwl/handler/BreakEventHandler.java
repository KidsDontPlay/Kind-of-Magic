package mrriegel.rwl.handler;

import java.util.List;

import mrriegel.rwl.gui.InventoryNevPick;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.utility.MyUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BreakEventHandler {

	@SubscribeEvent
	public void onBreak(BreakEvent event) {
		EntityPlayer player = event.getPlayer();
		World world = player.worldObj;
		if (world.isRemote)
			return;
		ItemStack stack = player.getHeldItem();
		if (stack != null && stack.getItem().equals(ModItems.nevpick)) {
			stack.getEnchantmentTagList();
			switch (stack
					.getTagCompound()
					.getTagList(InventoryNevPick.tagName,
							stack.getTagCompound().getId()).getCompoundTagAt(0)
					.getShort("Damage")) {
			case 3:
				event.setCanceled(true);
				MyUtils.breakWithSilk(world, event.x, event.y, event.z);
				break;
			case 4:
				event.setCanceled(true);
				MyUtils.breakWithFortune(world, event.x, event.y, event.z, 3);
				break;
			case 6:
				event.setCanceled(true);

				ItemStack s = FurnaceRecipes.smelting()
						.getSmeltingResult(
								new ItemStack(world.getBlock(event.x, event.y,
										event.z)));
				System.out.println("stack: "
						+ new ItemStack(world.getBlock(event.x, event.y,
								event.z)));
				System.out.println("s: " + s);
				if (s == null) {
					world.getBlock(event.x, event.y, event.z).dropBlockAsItem(
							world, event.x, event.y, event.z,
							world.getBlockMetadata(event.x, event.y, event.z),
							0);
					world.setBlock(event.x, event.y, event.z, Blocks.air, 0, 3);
					return;
				}

				EntityItem ei = new EntityItem(world, event.x, event.y,
						event.z, s);
				world.spawnEntityInWorld(ei);

				world.setBlock(event.x, event.y, event.z, Blocks.air, 0, 3);
			}
		}
	}

	@SubscribeEvent
	public void mining(LivingUpdateEvent event) {
		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) event.entityLiving;
		World world = player.worldObj;
		if (world.isRemote)
			return;
		ItemStack stack = player.getHeldItem();
		if (stack != null && stack.getItem().equals(ModItems.nevpick)) {
			if (stack
					.getTagCompound()
					.getTagList(InventoryNevPick.tagName,
							stack.getTagCompound().getId()).getCompoundTagAt(0)
					.getShort("Damage") == 5) {
				if (player.getActivePotionEffect(Potion.digSpeed) != null)
					player.removePotionEffect(Potion.digSpeed.id);

				player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 5,
						3, true));
			}
		}
	}
}
