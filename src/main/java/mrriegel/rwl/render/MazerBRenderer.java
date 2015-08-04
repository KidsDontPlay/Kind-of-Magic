package mrriegel.rwl.render;

import java.awt.image.TileObserver;

import org.lwjgl.opengl.GL11;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class MazerBRenderer extends TileEntitySpecialRenderer {

	MyModel model = new MyModel();
	ItemStack stack = new ItemStack(ModItems.ostick, 1, 0);
	EntityItem entItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D,
			0D, 0D, stack);

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,
			double z, float partticks) {
		MazerTile tile = (MazerTile) tileentity;

		GL11.glPushMatrix();
		// Render Model Here
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		// Without the below line, the item will spazz out
		this.entItem.hoverStart = 0.0F;
		RenderItem.renderInFrame = true;
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.02F, (float) z + 0.3F);
		GL11.glRotatef(180, 0, 1, 1);
		RenderManager.instance.renderEntityWithPosYaw(this.entItem, 0.0D, 0.0D,
				0.0D, 0.0F, 0.0F);
		RenderItem.renderInFrame = false;
		GL11.glPopMatrix();
	}

}
