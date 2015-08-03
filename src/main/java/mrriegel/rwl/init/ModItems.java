package mrriegel.rwl.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import mrriegel.rwl.item.BloodRelic;
import mrriegel.rwl.item.Bloodie;
import mrriegel.rwl.item.CrystalBag;
import mrriegel.rwl.item.Crysthal;
import mrriegel.rwl.item.Catalyst;
import mrriegel.rwl.item.MazeDust;
import mrriegel.rwl.item.NevAxe;
import mrriegel.rwl.item.NevPick;
import mrriegel.rwl.item.NevShovel;
import mrriegel.rwl.item.NevSword;
import mrriegel.rwl.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	public static Item bloodie = new Bloodie();
	public static Item relic = new BloodRelic();
	public static Item catalyst = new Catalyst();
	public static Item mdust = new MazeDust();
	public static Item nevsword = new NevSword();
	public static Item nevpick = new NevPick();
	public static Item nevshovel = new NevShovel();
	public static Item nevaxe = new NevAxe();
	public static Item cry = new Crysthal();
	public static Item bag = new CrystalBag();

	public static void init() {
		GameRegistry.registerItem(bloodie, "bloodie");
		GameRegistry.registerItem(relic, "relic");
		GameRegistry.registerItem(catalyst, "catalyst");
		catalyst.setContainerItem(catalyst);
		GameRegistry.registerItem(mdust, "mdust");
		GameRegistry.registerItem(nevsword, "nevsword");
		GameRegistry.registerItem(nevpick, "nevpick");
		GameRegistry.registerItem(nevshovel, "nevshovel");
		GameRegistry.registerItem(nevaxe, "nevaxe");
		GameRegistry.registerItem(cry, "cry");
		GameRegistry.registerItem(bag, "bag");

	}
}
