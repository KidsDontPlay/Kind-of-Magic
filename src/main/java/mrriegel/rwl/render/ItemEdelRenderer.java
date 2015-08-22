package mrriegel.rwl.render;

import javax.swing.Icon;

import org.lwjgl.opengl.GL11;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.inventory.InventoryTaliBag;
import mrriegel.rwl.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class ItemEdelRenderer implements IItemRenderer {
	private static RenderItem renderItem = new RenderItem();

	@Override
	public boolean handleRenderType(ItemStack itemStack, ItemRenderType type) {
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
		RenderHelper.enableGUIStandardItemLighting();

		renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer,
				Minecraft.getMinecraft().renderEngine, itemStack, 0, 0);

		if (itemStack.stackTagCompound == null)
			return;

		ItemStack target = ItemStack.loadItemStackFromNBT(itemStack
				.getTagCompound().getCompoundTag(InventoryNevTool.tagName));

		if (target == null)
			return;

		GL11.glPushMatrix();
		GL11.glScalef(0.48f, 0.48f, 0.48f);
		GL11.glTranslatef(-8f, -8f, 0.0f);

		renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer,
				Minecraft.getMinecraft().renderEngine, target, 25, 25);

		GL11.glPopMatrix();
	}
}
