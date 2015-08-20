package mrriegel.rwl.proxy;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.render.ItemEdelRenderer;
import mrriegel.rwl.render.StoneItemRenderer;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void addEffect(EntityFX entityFX) {
		Minecraft.getMinecraft().theWorld.spawnEntityInWorld(entityFX);

	}

	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class,
				new StoneItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.nevpick,
				new ItemEdelRenderer());
	}
}
