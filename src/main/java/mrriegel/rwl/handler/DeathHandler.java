package mrriegel.rwl.handler;

import java.util.ArrayList;
import java.util.Random;

import mrriegel.rwl.gui.InventoryNevTool;
import mrriegel.rwl.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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
			}
		}
	}
}
