package mrriegel.rwl.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import mrriegel.rwl.item.BloodRelic;
import mrriegel.rwl.item.Bloodie;
import mrriegel.rwl.item.KCatalyst;
import mrriegel.rwl.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	public static Item bloodie = new Bloodie();
	public static Item relic = new BloodRelic();
	public static Item kcatalyst = new KCatalyst();

	public static void init() {
		GameRegistry.registerItem(bloodie, "bloodie");
		GameRegistry.registerItem(relic, "relic");
		GameRegistry.registerItem(kcatalyst, "kcatalyst");

	}
}
