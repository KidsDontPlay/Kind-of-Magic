package mrriegel.rwl.handler;

import java.util.List;

import mrriegel.rwl.gui.InventoryNevTool;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.utility.MyUtils;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BreakEventHandler {

	//@SubscribeEvent
	public void mining(LivingUpdateEvent event) {
		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) event.entityLiving;
		World world = player.worldObj;
		if (world.isRemote)
			return;
		ItemStack stack = player.getHeldItem();
		if (stack != null
				&& (stack.getItem().equals(ModItems.nevpick) || stack.getItem()
						.equals(ModItems.nevshovel))) {
			if (!NBTHelper.getBoolean(stack, "opened")) {
				return;
			}
			if (stack
					.getTagCompound()
					.getTagList(InventoryNevTool.tagName,
							stack.getTagCompound().getId()).getCompoundTagAt(0)
					.getShort("Damage") == 5) {
				player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20,
						3, true));

			} else if (stack
					.getTagCompound()
					.getTagList(InventoryNevTool.tagName,
							stack.getTagCompound().getId()).getCompoundTagAt(0)
					.getShort("Damage") == 2) {
				player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id,
						5, 3, true));
			}
		}
	}

	// @SubscribeEvent
	public void inter(PlayerInteractEvent event) {
		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) event.entityLiving;
		World world = player.worldObj;
		if (world.isRemote)
			return;
		ItemStack stack = player.getHeldItem();
		if (event.action.equals(Action.LEFT_CLICK_BLOCK)) {
			if (stack != null
					&& (stack.getItem().equals(ModItems.nevpick) || stack
							.getItem().equals(ModItems.nevshovel))) {
				if (!NBTHelper.getBoolean(stack, "opened")) {
					return;
				}
				if (stack
						.getTagCompound()
						.getTagList(InventoryNevTool.tagName,
								stack.getTagCompound().getId())
						.getCompoundTagAt(0).getShort("Damage") == 5) {
					world.getBlock(event.x, event.y, event.z).setHardness(
							(float) (world.getBlock(event.x, event.y, event.z)
									.getBlockHardness(world, event.x, event.y,
											event.z) / 1.5));
				}
			}
		}
	}
}
