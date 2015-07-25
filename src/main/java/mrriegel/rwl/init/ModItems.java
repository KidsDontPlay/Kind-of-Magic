package mrriegel.rwl.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import mrriegel.rwl.item.Bloodie;
import mrriegel.rwl.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	public static Item bloodie = new Bloodie();

	public static void init() {
		GameRegistry.registerItem(bloodie, "bloodie");

	}
}
