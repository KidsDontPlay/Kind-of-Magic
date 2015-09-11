package mrriegel.rwl.proxy;

import mrriegel.rwl.entity.EnergyBlastProjectile;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.render.EdelsteinRenderer;
import mrriegel.rwl.render.NevToolRenderer;
import mrriegel.rwl.render.RenderEnergyBlastProjectile;
import mrriegel.rwl.render.StoneItemRenderer;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(MazerTile.class,
				new StoneItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.nevpick,
				new NevToolRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.nevaxe,
				new NevToolRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.nevshovel,
				new NevToolRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.nevsword,
				new NevToolRenderer());
		RenderingRegistry.registerEntityRenderingHandler(
				EnergyBlastProjectile.class, new RenderEnergyBlastProjectile());
		MinecraftForgeClient.registerItemRenderer(ModItems.up,
				new EdelsteinRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.dung,
				new EdelsteinRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.light,
				new EdelsteinRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.flash,
				new EdelsteinRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.shoot,
				new EdelsteinRenderer());
	}
}
