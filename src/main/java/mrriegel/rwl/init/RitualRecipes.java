package mrriegel.rwl.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RitualRecipes {

	public static List<RitualRecipe> lis;

	public static void init() {
		lis = new ArrayList<RitualRecipe>();
		lis.add(new RitualRecipe(new ItemStack(ModItems.nev), new ItemStack(
				ModItems.mdust), new ItemStack(Items.ender_pearl),
				new ItemStack(Blocks.lapis_block),
				new ItemStack(Items.diamond), new ItemStack(ModItems.catalyst,
						1, 0)));
	}
}
