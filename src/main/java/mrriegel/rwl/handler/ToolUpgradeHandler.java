package mrriegel.rwl.handler;

import mrriegel.rwl.item.INev;
import mrriegel.rwl.item.NevPick;
import mrriegel.rwl.item.NevSword;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ToolUpgradeHandler {
	@SubscribeEvent
	public void breakB(BreakEvent e) {
		if (!e.world.isRemote && e.getPlayer() != null
				&& e.getPlayer().getHeldItem() != null
				&& e.getPlayer().getHeldItem().getItem() instanceof INev) {
			ItemStack stack = e.getPlayer().getHeldItem();
			if (NBTHelper.getInt(stack, "exp") <= 10000)
				NBTHelper.setInteger(stack, "exp",
						NBTHelper.getInt(stack, "exp") + 1);
			if (NBTHelper.getInt(stack, "exp") >= 1000
					&& !NBTHelper.getBoolean(stack, "tier1"))
				NBTHelper.setBoolean(stack, "tier1", true);
			if (NBTHelper.getInt(stack, "exp") >= 10000
					&& !NBTHelper.getBoolean(stack, "tier2"))
				NBTHelper.setBoolean(stack, "tier2", true);
		}
	}

	@SubscribeEvent
	public void kill(LivingDeathEvent e) {
		if (!e.entityLiving.worldObj.isRemote && e.entity instanceof IMob
				&& e.source.getSourceOfDamage() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.source.getSourceOfDamage();
			ItemStack stack = player.getHeldItem();
			if (stack != null && stack.getItem() instanceof NevSword) {
				if (NBTHelper.getInt(stack, "kill") <= 1000)
					NBTHelper.setInteger(stack, "kill",
							NBTHelper.getInt(stack, "kill") + 1);
				if (NBTHelper.getInt(stack, "kill") >= 100
						&& !NBTHelper.getBoolean(stack, "tier1"))
					NBTHelper.setBoolean(stack, "tier1", true);
				if (NBTHelper.getInt(stack, "kill") >= 1000
						&& !NBTHelper.getBoolean(stack, "tier2"))
					NBTHelper.setBoolean(stack, "tier2", true);
			}
		}
	}
}
