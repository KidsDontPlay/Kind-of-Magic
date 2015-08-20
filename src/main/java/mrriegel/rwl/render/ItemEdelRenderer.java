package mrriegel.rwl.render;

import javax.swing.Icon;

import org.lwjgl.opengl.GL11;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
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
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
           
            // ====================== Render item texture ======================
            IIcon icon = itemStack.getIconIndex();
            renderItem.renderIcon(0, 0, icon, 16, 16);
           
            // ====================== Render OpenGL square shape ======================
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDepthMask(false);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
           
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawing(GL11.GL_QUADS);
            tessellator.setColorRGBA(0, 0, 0, 128);
            tessellator.addVertex(0, 0, 0);
            tessellator.addVertex(0, 8, 0);
            tessellator.addVertex(8, 8, 0);
            tessellator.addVertex(8, 0, 0);
            tessellator.draw();
           
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_BLEND);
           
            // ====================== Render text ======================
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            String text = Integer.toString(itemStack.getItemDamage());
            fontRenderer.drawStringWithShadow(text, 1, 1, 0xFFFFFF);
            class Stop extends Item{
            	Stop(){
            		super();
            		this.setUnlocalizedName(Reference.MOD_ID + ":" + "stop");
            		this.setTextureName(Reference.MOD_ID + ":" + "stop");
            	}
            }
            renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(new Stop()), 8, 8);
    }
}
