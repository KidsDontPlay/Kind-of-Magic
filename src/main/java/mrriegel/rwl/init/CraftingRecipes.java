package mrriegel.rwl.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.catalyst, 1, 0),
				"mbm", "odo", "lol", 'm', new ItemStack(ModItems.mdust), 'd',
				new ItemStack(Items.diamond), 'o',
				new ItemStack(ModItems.odust), 'b', ModItems.bloodie, 'l',
				new ItemStack(ModItems.drop));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.catalyst, 1, 1),
				"dcd", "cbc", "dcd", 'd', new ItemStack(ModItems.aodust), 'c',
				new ItemStack(ModItems.catalyst, 1, 0), 'b', new ItemStack(
						Blocks.diamond_block));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.catalyst, 1, 2),
				"dcd", "cbc", "dcd", 'd', new ItemStack(Items.nether_star),
				'c', new ItemStack(ModItems.catalyst, 1, 1), 'b',
				new ItemStack(Blocks.emerald_block));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ostick), " a ",
				"asa", " a ", 'a', new ItemStack(ModItems.aodust), 's',
				new ItemStack(ModItems.panstick));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.oblock), "ooo",
				"ooo", "ooo", 'o', new ItemStack(ModItems.odust));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.aoblock), "ooo",
				"ooo", "ooo", 'o', new ItemStack(ModItems.aodust));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.bag), "lol", "lll",
				'o', new ItemStack(ModItems.mdust), 'l', new ItemStack(
						Items.leather));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.keep), " o ",
				"olo", " o ", 'o', new ItemStack(ModItems.odust), 'l',
				new ItemStack(Blocks.torch));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.wnugget, 4),
				Items.nether_star);
		GameRegistry.addShapedRecipe(new ItemStack(Items.nether_star), "oo ",
				"oo ", 'o', new ItemStack(ModItems.wnugget));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mazerB), " a ",
				"mom", 'm', new ItemStack(ModBlocks.mazer), 'a', new ItemStack(
						ModItems.drop), 'o', new ItemStack(ModBlocks.oblock));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.aodust, 9),
				ModBlocks.aoblock);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.odust, 9),
				ModBlocks.oblock);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.tbag), "lol",
				"lll", 'o', new ItemStack(ModItems.drop), 'l', new ItemStack(
						Items.leather));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.panstick), "  l",
				" l ", "l  ", 'l', new ItemStack(ModBlocks.panlog));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.panstick), "l  ",
				" l ", "  l", 'l', new ItemStack(ModBlocks.panlog));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.pansapling),
				new ItemStack(ModItems.drop, 1, 1), new ItemStack(
						Blocks.sapling, 1, Short.MAX_VALUE));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.amber), "sis",
				"iri", "sis", 's', new ItemStack(ModItems.panstick), 'i',
				new ItemStack(Blocks.ice), 'r', new ItemStack(ModItems.resin));

	}
}
