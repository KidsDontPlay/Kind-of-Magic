package mrriegel.rwl.render;

import mrriegel.rwl.item.ItemEdelstein;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class EdelsteinRenderer implements IItemRenderer {
	private static RenderItem renderItem = new RenderItem();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack itemStack,
			Object... data) {
		if (NBTHelper.getInt(itemStack, "cooldown") == 0) {
			RenderHelper.enableGUIStandardItemLighting();

			renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer,
					Minecraft.getMinecraft().renderEngine, itemStack, 0, 0);
		} else {
			RenderHelper.enableGUIStandardItemLighting();
			// GL11.glPushMatrix();
			ItemEdelstein ie = (ItemEdelstein) itemStack.getItem();
			renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer,
					Minecraft.getMinecraft().renderEngine, itemStack, 0, 0);
			// GL11.glPopMatrix();
			// IIcon icon = ie.getIconFromDamage(0);
			// renderItem.renderIcon(0, 0, icon, 16, 16);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			double s = 1.0D - ((double) NBTHelper.getInt(itemStack, "cooldown") / ie.cooldown);

			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawing(GL11.GL_QUADS);
			tessellator.setColorRGBA(255, 40, 40, 128);
			tessellator.addVertex(0, s * 16.0D, 0);// lo
			tessellator.addVertex(0, 16, 0);// lu
			tessellator.addVertex(16, 16, 0);// ru
			tessellator.addVertex(16, s * 16.0D, 0);// ro
			tessellator.draw();

			GL11.glDepthMask(true);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

	}
}
