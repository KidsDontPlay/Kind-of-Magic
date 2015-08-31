package mrriegel.rwl.render;

import mrriegel.rwl.handler.ConfigurationHandler;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.item.INev;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class NevToolOverlayRenderer {
	@SubscribeEvent
	public void onScreenRenderEvent(RenderGameOverlayEvent event) {
		if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
			Minecraft mc = Minecraft.getMinecraft();
			World world = mc.theWorld;
			EntityPlayer player = mc.thePlayer;
			Vec3 vec3 = player.getPosition(1.0F);
			Vec3 vec3a = player.getLook(1.0F);
			Vec3 vec3b = vec3.addVector(vec3a.xCoord * 3, vec3a.yCoord * 3,
					vec3a.zCoord * 3);
			if (player.getHeldItem() != null
					&& player.getHeldItem().getItem() instanceof INev) {

				ScaledResolution resolution = new ScaledResolution(mc,
						mc.displayWidth, mc.displayHeight);
				if (player.getHeldItem().getTagCompound() == null)
					return;
				ItemStack s = ItemStack.loadItemStackFromNBT(player
						.getHeldItem().getTagCompound()
						.getCompoundTag(InventoryNevTool.tagName));
				if (s == null)
					return;
				RenderItem r = new RenderItem();
				r.zLevel = 200.0F;
				IIcon i = s.getItem().getIconFromDamage(0);
				int centerX = (resolution.getScaledWidth() - mc.fontRenderer
						.getStringWidth(s.getDisplayName())) / 2;
				int centerY = (resolution.getScaledHeight() - mc.fontRenderer.FONT_HEIGHT) / 2;

				Item item = player.getHeldItem().getItem();
				if ((item.equals(ModItems.nevsword) && ConfigurationHandler.swordHUD)
						|| (item.equals(ModItems.nevpick) && ConfigurationHandler.pickHUD)
						|| (item.equals(ModItems.nevshovel) && ConfigurationHandler.shovelHUD)
						|| (item.equals(ModItems.nevaxe) && ConfigurationHandler.axeHUD)) {
					if (ConfigurationHandler.HUDType.equals("name")) {
						mc.fontRenderer.drawStringWithShadow(
								s.getDisplayName(), centerX, centerY - 15,
								0xFF4040);
					} else if (ConfigurationHandler.HUDType.equals("icon")) {
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						RenderHelper.enableGUIStandardItemLighting();
						GL11.glPushMatrix();
						centerX = (resolution.getScaledWidth() - mc.fontRenderer
								.getStringWidth("")) / 2;
						r.renderItemIntoGUI(
								Minecraft.getMinecraft().fontRenderer,
								Minecraft.getMinecraft().renderEngine, s,
								centerX - 8, centerY - 15);
						GL11.glPopMatrix();
					}
				}
			}
		}
	}
}
