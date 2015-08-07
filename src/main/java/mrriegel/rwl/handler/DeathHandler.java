package mrriegel.rwl.handler;

import java.util.ArrayList;
import java.util.Random;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DeathHandler {
	
	@SubscribeEvent
	public void death(LivingDropsEvent event) {
		Entity e = event.entity;
		DamageSource source = event.source;
		if (!e.worldObj.isRemote
				&& source.getSourceOfDamage() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) source.getSourceOfDamage();

			if (player.getHeldItem() == null) {
				return;
			}
			ItemStack stack = player.getHeldItem();
			if (player.getHeldItem().getItem().equals(ModItems.nevsword)
					&& stack.getTagCompound() != null
					&& stack.getTagCompound()
							.getTagList(InventoryNevTool.tagName,
									stack.getTagCompound().getId())
							.getCompoundTagAt(0).getShort("Damage") == 4) {
				ArrayList<EntityItem> l = new ArrayList<EntityItem>();
				for (EntityItem ei : event.drops) {
					l.add(new EntityItem(ei.worldObj, ei.posX, ei.posY,
							ei.posZ, ei.getEntityItem()));
				}
				for (EntityItem ei : l) {
					Random r = new Random();
					int c = r.nextInt(1) + 1;
					for (int i = 0; i < c; i++) {
						event.drops.add(new EntityItem(ei.worldObj, ei.posX,
								ei.posY, ei.posZ, ei.getEntityItem()));
					}
				}
			} else if (player.getHeldItem().getItem().equals(ModItems.nevsword)
					&& stack.getTagCompound() != null
					&& stack.getTagCompound()
							.getTagList(InventoryNevTool.tagName,
									stack.getTagCompound().getId())
							.getCompoundTagAt(0).getShort("Damage") == 13) {
				for (int i = 0; i < 3; i++) {
					player.worldObj.spawnEntityInWorld(new EntityXPOrb(
							player.worldObj, e.posX + 0.5d, e.posY,
							e.posZ + 0.5d, EntityXPOrb.getXPSplit(0)));
				}
			}

		}
	}

	@SubscribeEvent
	public void xp(BreakEvent event) {
		EntityPlayer player = event.getPlayer();
		if (!player.worldObj.isRemote) {

			if (player.getHeldItem() == null) {
				return;
			}
			ItemStack stack = player.getHeldItem();
			if (player.getHeldItem().getItem().equals(ModItems.nevpick)
					&& stack.getTagCompound() != null
					&& stack.getTagCompound()
							.getTagList(InventoryNevTool.tagName,
									stack.getTagCompound().getId())
							.getCompoundTagAt(0).getShort("Damage") == 13) {
				int xp = event.block.getExpDrop(player.worldObj,
						player.worldObj.getBlockMetadata(event.x, event.y,
								event.z), 0);
				if (xp > 0) {
					for (int i = 0; i < 2; i++) {
						player.worldObj.spawnEntityInWorld(new EntityXPOrb(
								player.worldObj, event.x + 0.5d, event.y,
								event.z + 0.5d, EntityXPOrb.getXPSplit(0)));
					}
				}

			}
		}
	}
}
