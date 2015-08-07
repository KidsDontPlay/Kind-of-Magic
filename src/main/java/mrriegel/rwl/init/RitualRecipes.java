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
				Items.diamond), new ItemStack(ModBlocks.mazer), new ItemStack(
				ModBlocks.oblock), new ItemStack(ModBlocks.aoblock),
				new ItemStack(ModItems.catalyst, 1, 0)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 0),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Items.gold_ingot), new ItemStack(
						Items.glowstone_dust), new ItemStack(ModItems.catalyst,
						1, 0)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 1),
				new ItemStack(ModItems.cry, 1, 0),
				new ItemStack(ModItems.mdust), new ItemStack(
						Items.golden_apple, 1, 0), new ItemStack(
						Items.glowstone_dust), new ItemStack(ModItems.catalyst,
						1, 1)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 2),
				new ItemStack(ModItems.cry, 1, 1),
				new ItemStack(ModItems.mdust), new ItemStack(
						Items.golden_apple, 1, 1), new ItemStack(
						Blocks.glowstone), new ItemStack(ModItems.catalyst, 1,
						2)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 3),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Items.slime_ball), new ItemStack(Blocks.wool, 1,
						13), new ItemStack(ModItems.catalyst, 1, 0)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 4),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Blocks.lapis_block), new ItemStack(
						Items.potionitem), new ItemStack(ModItems.catalyst, 1,
						1)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 5),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Blocks.redstone_block),
				new ItemStack(Items.sugar), new ItemStack(ModItems.catalyst, 1,
						1)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 6),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Items.blaze_rod), new ItemStack(
						Items.flint_and_steel), new ItemStack(
						ModItems.catalyst, 1, 0)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 7),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Items.spider_eye), new ItemStack(
						Items.poisonous_potato), new ItemStack(
						ModItems.catalyst, 1, 1)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 8),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Blocks.sapling, 1, Short.MAX_VALUE),
				new ItemStack(Items.iron_axe), new ItemStack(ModItems.catalyst,
						1, 0)));
		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 9),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Items.quartz), new ItemStack(Items.ender_pearl),
				new ItemStack(ModItems.catalyst, 1, 1)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 10),
				new ItemStack(ModItems.cry, 1, 9),
				new ItemStack(ModItems.mdust), new ItemStack(
						Blocks.quartz_block), new ItemStack(Items.ghast_tear),
				new ItemStack(ModItems.catalyst, 1, 2)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 11),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Blocks.web), new ItemStack(
						Items.fermented_spider_eye), new ItemStack(
						ModItems.catalyst, 1, 0)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 12),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Items.skull, 1, 1), new ItemStack(Items.emerald),
				new ItemStack(ModItems.catalyst, 1, 2)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 13),
				new ItemStack(Items.diamond), new ItemStack(ModItems.mdust),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.speckled_melon), new ItemStack(ModItems.catalyst,
						1, 1)));

		lis.add(new RitualRecipe(new ItemStack(ModItems.nevsword),
				new ItemStack(Items.iron_sword), new ItemStack(Items.diamond),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.ostick),
				new ItemStack(ModItems.catalyst, 1, 0)));
		
		lis.add(new RitualRecipe(new ItemStack(ModItems.nevpick),
				new ItemStack(Items.iron_pickaxe), new ItemStack(Items.diamond),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.ostick),
				new ItemStack(ModItems.catalyst, 1, 0)));
		
		lis.add(new RitualRecipe(new ItemStack(ModItems.nevshovel),
				new ItemStack(Items.iron_shovel), new ItemStack(Items.diamond),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.ostick),
				new ItemStack(ModItems.catalyst, 1, 0)));
		
		lis.add(new RitualRecipe(new ItemStack(ModItems.nevaxe),
				new ItemStack(Items.iron_axe), new ItemStack(Items.diamond),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.ostick),
				new ItemStack(ModItems.catalyst, 1, 0)));
	}
}
