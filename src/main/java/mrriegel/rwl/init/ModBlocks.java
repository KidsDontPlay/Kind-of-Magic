package mrriegel.rwl.init;

import mrriegel.rwl.block.Mazer;
import mrriegel.rwl.block.MazerB;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

	public static Block mazer = new Mazer();
	public static Block mazerB = new MazerB();

	public static void init() {
		GameRegistry.registerBlock(mazer, "mazer");
		GameRegistry.registerBlock(mazerB, "mazerB");

		
		GameRegistry.registerTileEntity(MazerTile.class, "mazertile");
	}

}
