package mrriegel.rwl.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes {
	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModItems.relic), "glg", "dbd", "glg", 'g',
				"blockGlass", 'l', new ItemStack(Items.dye, 1, 4), 'd',
				new ItemStack(Items.diamond), 'b', new ItemStack(
						ModItems.bloodie)));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.mdust, 4),
				ModBlocks.mazer);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mazer), "oo",
				"oo", 'o', new ItemStack(ModItems.mdust));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.catalyst, 1, 0),
				"mbm", "odo", "lol", 'm', new ItemStack(ModItems.mdust), 'd',
				new ItemStack(Items.diamond), 'o', new ItemStack(
						ModItems.oredust), 'b', ModItems.bloodie, 'l',
				new ItemStack(ModItems.drop));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.catalyst, 1, 1),
				"dcd", "cbc", "dcd", 'd', new ItemStack(ModItems.airoredust),
				'c', new ItemStack(ModItems.catalyst, 1, 0), 'b',
				new ItemStack(Blocks.diamond_block));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.catalyst, 1, 2),
				"dcd", "cbc", "dcd", 'd', new ItemStack(Items.nether_star),
				'c', new ItemStack(ModItems.catalyst, 1, 1), 'b',
				new ItemStack(Blocks.emerald_block));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ostick), " a ",
				"asa", " a ", 'a', new ItemStack(ModItems.airoredust), 's',
				new ItemStack(ModItems.panstick));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.oblock), "ooo",
				"ooo", "ooo", 'o', new ItemStack(ModItems.oredust));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.aoblock), "ooo",
				"ooo", "ooo", 'o', new ItemStack(ModItems.airoredust));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.bag), "lol", "lll",
				'o', new ItemStack(ModItems.mdust), 'l', new ItemStack(
						Items.leather));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.keep), " o ",
				"olo", " o ", 'o', new ItemStack(ModItems.oredust), 'l',
				new ItemStack(Blocks.torch));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mazerB), " a ",
				"mom", 'm', new ItemStack(ModBlocks.mazer), 'a', new ItemStack(
						ModItems.drop), 'o', new ItemStack(ModBlocks.oblock));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.airoredust, 9),
				ModBlocks.aoblock);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.oredust, 9),
				ModBlocks.oblock);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.tbag), "lol",
				"lll", 'o', new ItemStack(ModItems.drop), 'l', new ItemStack(
						Items.leather));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.panstick), "  l",
				" l ", "l  ", 'l', new ItemStack(ModBlocks.panlog));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.panstick), "l  ",
				" l ", "  l", 'l', new ItemStack(ModBlocks.panlog));
		// GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.pansapling),
		// new ItemStack(ModItems.drop, 1, 1), "treeSapling");
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(
				ModBlocks.pansapling), new ItemStack(ModItems.drop, 1, 1),
				"treeSapling"));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.amber), "sis",
				"iri", "sis", 's', new ItemStack(ModItems.panstick), 'i',
				new ItemStack(Blocks.ice), 'r', new ItemStack(ModItems.resin));

	}
}
