package mrriegel.rwl;

import mrriegel.rwl.handler.ConfigurationHandler;
import mrriegel.rwl.init.CraftingRecipes;
import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.proxy.IProxy;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.world.RWLWorld;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class RWL {

	@Mod.Instance(Reference.MOD_ID)
	public static RWL instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		GameRegistry.registerWorldGenerator(new RWLWorld(), 1);
		ModBlocks.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		CraftingRecipes.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
