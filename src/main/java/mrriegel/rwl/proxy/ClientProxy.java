package mrriegel.rwl.proxy;

import mrriegel.rwl.render.StoneItemRenderer;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	public static void init() {
		// ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class,
		// new MazerBRenderer());
		// MinecraftForgeClient.registerItemRenderer(ModItems.ostick,new
		// ItemRend());
	}

	@Override
	public void addEffect(EntityFX entityFX) {
		Minecraft.getMinecraft().theWorld.spawnEntityInWorld(entityFX);

	}
	
	@Override
	public void registerRenderers(){
		ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class, new StoneItemRenderer());
	}
}
