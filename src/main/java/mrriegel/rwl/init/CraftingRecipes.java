package mrriegel.rwl.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes {
	public static void init() {
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mazer, 2), "##",
				"##", '#', new ItemStack(Items.dye, 1, 4));
	}
}
