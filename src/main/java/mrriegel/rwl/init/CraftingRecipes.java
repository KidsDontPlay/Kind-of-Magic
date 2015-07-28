package mrriegel.rwl.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes {
	public static void init() {
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.mazer),
				new ItemStack(ModItems.catalyst, 1,
						OreDictionary.WILDCARD_VALUE), new ItemStack(
						Blocks.lapis_block));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModItems.relic), "glg", "dbd", "glg", 'g',
				"blockGlass", 'l', new ItemStack(Items.dye, 1, 4), 'd',
				new ItemStack(Items.diamond), 'b', new ItemStack(
						ModItems.bloodie)));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.mdust, 2),
				ModBlocks.mazer);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.catalyst,1,0), "mmm",
				"mgm", "mmm", 'm', new ItemStack(ModItems.mdust), 'g',
				new ItemStack(Items.gold_ingot));

	}
}
