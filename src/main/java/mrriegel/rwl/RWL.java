package mrriegel.rwl;

import mrriegel.rwl.handler.ConfigurationHandler;
import mrriegel.rwl.handler.DropEventHandler;
import mrriegel.rwl.handler.ToolEventHandler;
import mrriegel.rwl.init.CraftingRecipes;
import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.init.RitualRecipes;
import mrriegel.rwl.item.ItemFlyer;
import mrriegel.rwl.item.ItemStepper;
import mrriegel.rwl.item.ItemVision;
import mrriegel.rwl.packet.Packet;
import mrriegel.rwl.packet.PacketHandler;
import mrriegel.rwl.proxy.CommonProxy;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.waila.StoneHandler;
import mrriegel.rwl.world.RWLWorld;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class RWL {

	@Mod.Instance(Reference.MOD_ID)
	public static RWL instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper net;

	private static int modGuiIndex = 0;
	public static final int ItemInventoryGuiIndex = modGuiIndex++;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		GameRegistry.registerWorldGenerator(new RWLWorld(), 1);
		ModBlocks.init();
		ModItems.init();
		net = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		net.registerMessage(PacketHandler.class, Packet.class, 0, Side.CLIENT);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
		MinecraftForge.EVENT_BUS.register(new ToolEventHandler());
		MinecraftForge.EVENT_BUS.register(new DropEventHandler());
		MinecraftForge.EVENT_BUS.register(new ItemStepper());
		MinecraftForge.EVENT_BUS.register(new ItemFlyer());
		MinecraftForge.EVENT_BUS.register(new ItemVision());

		CraftingRecipes.init();
		RitualRecipes.init();
		// ClientProxy.init();
		proxy.registerRenderers();
		FMLInterModComms.sendMessage("Waila", "register",
				"mrriegel.rwl.waila.StoneHandler.callbackRegister");
		String r = new StoneHandler().toString() + "eine updatenotiz";
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
