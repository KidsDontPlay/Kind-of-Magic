package mrriegel.rwl.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import mrriegel.rwl.item.BloodRelic;
import mrriegel.rwl.item.Bloodie;
import mrriegel.rwl.item.Crysthal;
import mrriegel.rwl.item.KCatalyst;
import mrriegel.rwl.item.MazeDust;
import mrriegel.rwl.item.NevPick;
import mrriegel.rwl.item.NevSword;
import mrriegel.rwl.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	public static Item bloodie = new Bloodie();
	public static Item relic = new BloodRelic();
	public static Item kcatalyst = new KCatalyst();
	public static Item mdust = new MazeDust();
	public static Item nevsword = new NevSword();
	public static Item nevpick = new NevPick();
	public static Item cry = new Crysthal();

	public static void init() {
		GameRegistry.registerItem(bloodie, "bloodie");
		GameRegistry.registerItem(relic, "relic");
		GameRegistry.registerItem(kcatalyst, "kcatalyst");
		kcatalyst.setContainerItem(kcatalyst);
		GameRegistry.registerItem(mdust, "msdust");
		GameRegistry.registerItem(nevsword, "nevsword");
		GameRegistry.registerItem(nevpick, "nevpick");
		GameRegistry.registerItem(cry, "cry");

	}
}
