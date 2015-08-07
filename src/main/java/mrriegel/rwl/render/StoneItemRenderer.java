package mrriegel.rwl.render;

import mrriegel.rwl.tile.MazerTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class StoneItemRenderer extends TileEntitySpecialRenderer {

	private final RenderItem renderItem;

	public StoneItemRenderer() {
		renderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return false;
			}
		};
		renderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double d0, double d1,
			double d2, float f) {
		if (tileEntity instanceof MazerTile) {
			add(tileEntity, d0 + 1.3, d1, d2 + 1.3, f, 0);
			add(tileEntity, d0 - 1.3, d1, d2 - 1.3, f, 1);
			add(tileEntity, d0 + 1.3, d1, d2 - 1.3, f, 2);
			add(tileEntity, d0 - 1.3, d1, d2 + 1.3, f, 3);
		}
	}

	private void add(TileEntity tileEntity, double d0, double d1, double d2,
			float f, int slot) {
		MazerTile tile = (MazerTile) tileEntity;
		GL11.glPushMatrix();
		if (tile.getStackInSlot(slot) != null) {
			float scaleFactor = 0.85F;
			float rotationAngle = Minecraft.getMinecraft().gameSettings.fancyGraphics ? (float) (720.0 * (System
					.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) : 0;
			EntityItem ghostEntityItem = new EntityItem(tile.getWorldObj());
			ghostEntityItem.hoverStart = 0.0F;
			ghostEntityItem.setEntityItemStack(tile.getStackInSlot(slot));
			float displacement = 0.2F;

			if (ghostEntityItem.getEntityItem().getItem() instanceof ItemBlock) {
				GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + displacement
						+ 0.7F, (float) d2 + 0.5F);
			} else {
				GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + displacement
						+ 0.6F, (float) d2 + 0.5F);
			}
			GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
			GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);
			renderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
		}

		GL11.glPopMatrix();
	}

}
