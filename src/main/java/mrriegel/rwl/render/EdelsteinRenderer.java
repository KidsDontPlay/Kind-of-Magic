package mrriegel.rwl.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.GameRegistry;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.item.ItemEdelstein;
import mrriegel.rwl.item.NevAxe;
import mrriegel.rwl.item.NevPick;
import mrriegel.rwl.item.NevShovel;
import mrriegel.rwl.item.NevSword;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureClock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

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
