package mrriegel.rwl.gui;

import mrriegel.rwl.inventory.InventoryBag;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiCombo extends GuiContainer {

	private final InventoryNevTool inv1;
	private final InventoryBag inv2;
	private static final ResourceLocation texture = new ResourceLocation(
			Reference.MOD_ID + ":" + "textures/gui/combobag.png");

	public GuiCombo(ContainerCombo c) {
		super(c);
		this.inv1 = c.inv1;
		this.inv2 = c.inv2;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {

		fontRendererObj.drawString(inv1.storedInv.getDisplayName(), 8, 6,
				4210752);
		fontRendererObj.drawString(inv2.storedInv.getDisplayName(), 80, 6,
				4210752);
		fontRendererObj.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		GL11.glPopMatrix();

	}

}
