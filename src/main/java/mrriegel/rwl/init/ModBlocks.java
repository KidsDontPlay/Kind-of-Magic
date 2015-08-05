package mrriegel.rwl.init;

import mrriegel.rwl.block.AirOrus;
import mrriegel.rwl.block.Keep;
import mrriegel.rwl.block.Mazer;
import mrriegel.rwl.block.MazerB;
import mrriegel.rwl.block.Orus;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

	public static Block mazer = new Mazer();
	public static Block mazerB = new MazerB();
	public static Block orus = new Orus();
	public static Block airorus = new AirOrus();
	public static Block keep = new Keep();

	public static void init() {
		GameRegistry.registerBlock(mazer, "mazer");
		GameRegistry.registerBlock(mazerB, "mazerB");
		GameRegistry.registerBlock(orus, "orus");
		GameRegistry.registerBlock(airorus, "airorus");
		GameRegistry.registerBlock(keep, "keep");

		GameRegistry.registerTileEntity(MazerTile.class, "mazertile");
	}

}
