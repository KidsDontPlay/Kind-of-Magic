package mrriegel.rwl.render;

import mrriegel.rwl.item.ItemEdelstein;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
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
			GL11.glPushMatrix();
			ItemEdelstein ie = (ItemEdelstein) itemStack.getItem();
			float s = 1 - ((float) NBTHelper.getInt(itemStack, "cooldown") / ie.cooldown);
			if (s < 0.1F)
				s = 0.1F;
			s = 0.5F;
			GL11.glScalef(s, s, s);
			GL11.glTranslatef(8, 8, 0.0f);
			renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer,
					Minecraft.getMinecraft().renderEngine, itemStack, 0, 0);
			GL11.glPopMatrix();
		}

	}
}
