package mrriegel.rwl.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mrriegel.rwl.block.AOBlock;
import mrriegel.rwl.block.AirOrus;
import mrriegel.rwl.block.Grower;
import mrriegel.rwl.block.Keep;
import mrriegel.rwl.block.Mazer;
import mrriegel.rwl.block.MazerB;
import mrriegel.rwl.block.OBlock;
import mrriegel.rwl.block.Orus;
import mrriegel.rwl.block.PanLeaves;
import mrriegel.rwl.block.PanLog;
import mrriegel.rwl.block.PanSapling;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.GrowerTile;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

	public static Block mazer = new Mazer();
	public static Block mazerB = new MazerB();
	public static Block orus = new Orus();
	public static Block airorus = new AirOrus();
	public static Block keep = new Keep();
	public static Block oblock = new OBlock();
	public static Block aoblock = new AOBlock();
	public static Block grower = new Grower();
	public static Block panlog = new PanLog();
	public static Block panleaves = new PanLeaves();
	public static Block pansapling = new PanSapling();

	static Item[] f = { Item.getItemFromBlock(orus),
			Item.getItemFromBlock(airorus), Item.getItemFromBlock(mazer),
			Item.getItemFromBlock(mazerB), Item.getItemFromBlock(keep),
			Item.getItemFromBlock(oblock), Item.getItemFromBlock(aoblock),
			Item.getItemFromBlock(panlog), Item.getItemFromBlock(panleaves),
			Item.getItemFromBlock(pansapling), Item.getItemFromBlock(grower) };

	public static List<Item> lis = new ArrayList<Item>();

	public static void init() {

		GameRegistry.registerBlock(mazer, "mazer");
		GameRegistry.registerBlock(mazerB, "mazerB");
		GameRegistry.registerBlock(orus, "orus");
		GameRegistry.registerBlock(airorus, "airorus");
		GameRegistry.registerBlock(keep, "keep");
		GameRegistry.registerBlock(oblock, "oblock");
		GameRegistry.registerBlock(aoblock, "aoblock");
		GameRegistry.registerBlock(grower, "grower");
		GameRegistry.registerBlock(panlog, "panlog");
		GameRegistry.registerBlock(panleaves, "panleaves");
		GameRegistry.registerBlock(pansapling, "pansapling");

		GameRegistry.registerTileEntity(MazerTile.class, "mazertile");
		GameRegistry.registerTileEntity(GrowerTile.class, "growertile");
		for (Item i : f)
			lis.add(i);
	}
}
