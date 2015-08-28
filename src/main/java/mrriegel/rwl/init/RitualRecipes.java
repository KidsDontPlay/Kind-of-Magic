package mrriegel.rwl.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RitualRecipes {

	public static List<RitualRecipe> lis;
	final static int anywhere = Integer.MAX_VALUE;
	final static int overworld = 0;
	final static int nether = -1;
	final static int end = 1;
	final static int anytime = -1;
	final static int day = 0;
	final static int night = 1;

	public static void init() {
		lis = new ArrayList<RitualRecipe>();
		lis.add(new RitualRecipe(new ItemStack(ModItems.nev), new ItemStack(
				Items.diamond), new ItemStack(ModItems.drop,1,0), new ItemStack(
				ModItems.odust), new ItemStack(ModItems.aodust), new ItemStack(
				ModItems.catalyst, 1, 0), anywhere, anytime, 3));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 0),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Items.gold_ingot), new ItemStack(ModItems.drop,
						1, 0), new ItemStack(ModItems.catalyst, 1, 0),
				anywhere, anytime, 8));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 1),
				new ItemStack(ModItems.cry, 1, 0),
				new ItemStack(ModItems.mdust), new ItemStack(
						Items.golden_apple, 1, 0), new ItemStack(ModItems.drop,
						1, 1), new ItemStack(ModItems.catalyst, 1, 1), nether,
				anytime, 16));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 2),
				new ItemStack(ModItems.cry, 1, 1),
				new ItemStack(ModItems.mdust), new ItemStack(
						Items.golden_apple, 1, 1), new ItemStack(ModItems.drop,
						1, 2), new ItemStack(ModItems.catalyst, 1, 2), end,
				anytime, 32));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 3),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Items.slime_ball), new ItemStack(Blocks.wool, 1,
						13), new ItemStack(ModItems.catalyst, 1, 0), overworld,
				day, 4));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 4),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(ModItems.drop,1,1), new ItemStack(
						Items.potionitem), new ItemStack(ModItems.catalyst, 1,
						1), anywhere, day, 12));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 5),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Blocks.redstone_block),
				new ItemStack(ModItems.drop,1,1), new ItemStack(ModItems.catalyst, 1,
						1), anywhere, night, 10));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 6),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Items.blaze_rod), new ItemStack(
						Items.flint_and_steel), new ItemStack(
						ModItems.catalyst, 1, 0), nether, anytime, 8));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 7),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Items.spider_eye), new ItemStack(
						Items.poisonous_potato), new ItemStack(
						ModItems.catalyst, 1, 1), anywhere, anytime, 12));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 8),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(ModItems.drop,1,0),
				new ItemStack(Items.iron_axe), new ItemStack(ModItems.catalyst,
						1, 0), overworld, day, 5));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 9),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Items.quartz), new ItemStack(ModItems.drop,1,1),
				new ItemStack(ModItems.catalyst, 1, 1), anywhere, night, 15));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 10),
				new ItemStack(ModItems.cry, 1, 9),
				new ItemStack(ModItems.mdust), new ItemStack(
						Blocks.quartz_block), new ItemStack(ModItems.drop,1,3),
				new ItemStack(ModItems.catalyst, 1, 2), nether, anytime, 30));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 11),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(ModItems.drop,1,0), new ItemStack(
						Items.fermented_spider_eye), new ItemStack(
						ModItems.catalyst, 1, 0), overworld, night, 11));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 12),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Items.skull, 1, 1), new ItemStack(Items.emerald),
				new ItemStack(ModItems.catalyst, 1, 2), end, anytime, 25));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 13),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Items.writable_book), new ItemStack(
						ModItems.drop,1,1), new ItemStack(ModItems.catalyst,
						1, 1), anywhere, anytime, 8));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 14),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(ModItems.drop,1,1), new ItemStack(
						Blocks.redstone_block), new ItemStack(
						ModItems.catalyst, 1, 1), nether, anytime, 16));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cry, 1, 15),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.mdust),
				new ItemStack(Items.ghast_tear), new ItemStack(ModItems.drop,1,3),
				new ItemStack(ModItems.catalyst, 1, 2), nether, anytime, 18));

		lis.add(new RitualRecipe(new ItemStack(ModItems.nevsword),
				new ItemStack(Items.iron_sword), new ItemStack(Items.diamond),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.ostick),
				new ItemStack(ModItems.catalyst, 1, 0), anywhere, night, 10));

		lis.add(new RitualRecipe(new ItemStack(ModItems.nevpick),
				new ItemStack(Items.iron_pickaxe),
				new ItemStack(Items.diamond), new ItemStack(ModItems.nev),
				new ItemStack(ModItems.ostick), new ItemStack(
						ModItems.catalyst, 1, 0), anywhere, night, 10));

		lis.add(new RitualRecipe(new ItemStack(ModItems.nevshovel),
				new ItemStack(Items.iron_shovel), new ItemStack(Items.diamond),
				new ItemStack(ModItems.nev), new ItemStack(ModItems.ostick),
				new ItemStack(ModItems.catalyst, 1, 0), anywhere, night, 10));

		lis.add(new RitualRecipe(new ItemStack(ModItems.nevaxe), new ItemStack(
				Items.iron_axe), new ItemStack(Items.diamond), new ItemStack(
				ModItems.nev), new ItemStack(ModItems.ostick), new ItemStack(
				ModItems.catalyst, 1, 0), anywhere, night, 10));

		lis.add(new RitualRecipe(new ItemStack(ModItems.feeder), new ItemStack(
				ModItems.nev), new ItemStack(Blocks.coal_block), new ItemStack(
						ModItems.drop,1,1), new ItemStack(Items.pumpkin_pie),
				new ItemStack(ModItems.catalyst, 1, 1), nether, anytime, 7));

		lis.add(new RitualRecipe(new ItemStack(ModItems.sprinter),
				new ItemStack(ModItems.nev), new ItemStack(Blocks.coal_block),
				new ItemStack(Blocks.redstone_block),
				new ItemStack(ModItems.drop,1,1), new ItemStack(ModItems.catalyst, 1,
						0), nether, anytime, 8));

		lis.add(new RitualRecipe(new ItemStack(ModItems.repair), new ItemStack(
				ModItems.nev), new ItemStack(Blocks.coal_block), new ItemStack(
				Blocks.anvil), new ItemStack(ModItems.drop,1,1), new ItemStack(
				ModItems.catalyst, 1, 1), nether, anytime, 12));

		lis.add(new RitualRecipe(new ItemStack(ModItems.stepper),
				new ItemStack(ModItems.nev), new ItemStack(Blocks.coal_block),
				new ItemStack(Blocks.piston), new ItemStack(ModItems.drop,1,0),
				new ItemStack(ModItems.catalyst, 1, 0), nether, anytime, 5));

		lis.add(new RitualRecipe(new ItemStack(ModItems.flyer), new ItemStack(
				ModItems.nev), new ItemStack(Blocks.coal_block), new ItemStack(
				Items.nether_star), new ItemStack(ModItems.drop,1,2),
				new ItemStack(ModItems.catalyst, 1, 2), nether, anytime, 28));

		lis.add(new RitualRecipe(new ItemStack(ModItems.breather),
				new ItemStack(ModItems.nev), new ItemStack(Blocks.coal_block),
				new ItemStack(ModItems.drop,1,1), new ItemStack(Items.glass_bottle),
				new ItemStack(ModItems.catalyst, 1, 1), nether, anytime, 10));

		lis.add(new RitualRecipe(new ItemStack(ModItems.vision), new ItemStack(
				ModItems.nev), new ItemStack(Blocks.coal_block), new ItemStack(
				Items.potionitem, 1, 8262), new ItemStack(Items.golden_apple),
				new ItemStack(ModItems.catalyst, 1, 1), nether, anytime, 8));

		lis.add(new RitualRecipe(new ItemStack(ModItems.jumper), new ItemStack(
				ModItems.nev), new ItemStack(Blocks.coal_block), new ItemStack(
						ModItems.drop,1,0), new ItemStack(Items.slime_ball), new ItemStack(
				ModItems.catalyst, 1, 0), nether, anytime, 6));

		lis.add(new RitualRecipe(new ItemStack(ModItems.cooler), new ItemStack(
				ModItems.nev), new ItemStack(Blocks.coal_block), new ItemStack(
				Items.clock), new ItemStack(ModItems.drop,1,2), new ItemStack(
				ModItems.catalyst, 1, 2), nether, anytime, 20));
		
		lis.add(new RitualRecipe(new ItemStack(ModItems.extinger), new ItemStack(
				ModItems.nev), new ItemStack(Blocks.coal_block), new ItemStack(
				Items.magma_cream), new ItemStack(ModItems.drop,1,3), new ItemStack(
				ModItems.catalyst, 1, 2), nether, anytime, 27));

		lis.add(new RitualRecipe(new ItemStack(ModBlocks.grower),
				new ItemStack(ModItems.nev),
				new ItemStack(Blocks.emerald_block), new ItemStack(
						ModItems.wnugget), new ItemStack(Items.bone),
				new ItemStack(ModItems.catalyst, 1, 1), overworld, day, 16));

		lis.add(new RitualRecipe(new ItemStack(ModItems.dung), new ItemStack(
				ModItems.nev), new ItemStack(Items.emerald), new ItemStack(
						ModItems.drop,1,2), new ItemStack(Items.dye, 1, 15),
				new ItemStack(ModItems.catalyst, 1, 1), nether, anytime, 22));

		lis.add(new RitualRecipe(new ItemStack(ModItems.up), new ItemStack(
				ModItems.nev), new ItemStack(Items.ender_pearl), new ItemStack(
				ModItems.aodust), new ItemStack(Items.gold_ingot),
				new ItemStack(ModItems.catalyst, 1, 0), nether, anytime, 5));

		lis.add(new RitualRecipe(new ItemStack(ModItems.light), new ItemStack(
				ModItems.nev), new ItemStack(ModItems.drop,1,0), new ItemStack(
				Items.glowstone_dust), new ItemStack(Blocks.torch),
				new ItemStack(ModItems.catalyst, 1, 0), nether, anytime, 3));

		lis.add(new RitualRecipe(new ItemStack(ModItems.flash), new ItemStack(
				ModItems.nev), new ItemStack(Items.blaze_rod), new ItemStack(
				ModItems.aodust), new ItemStack(ModItems.drop,1,2),
				new ItemStack(ModItems.catalyst, 1, 1), nether, anytime, 12));

		lis.add(new RitualRecipe(new ItemStack(ModItems.shoot), new ItemStack(
				ModItems.nev), new ItemStack(Items.bow), new ItemStack(
						ModItems.drop,1,1), new ItemStack(Items.fire_charge), new ItemStack(
				ModItems.catalyst, 1, 0), nether, anytime, 4));

		lis.add(new RitualRecipe(new ItemStack(ModItems.drop, 1, 1),
				new ItemStack(ModItems.drop, 1, 0), new ItemStack(
						ModItems.mdust), new ItemStack(Items.gold_ingot),
				new ItemStack(ModItems.bloodie), new ItemStack(
						ModItems.catalyst, 1, 0), anywhere, anytime, 9));

		lis.add(new RitualRecipe(new ItemStack(ModItems.drop, 1, 2),
				new ItemStack(ModItems.drop, 1, 1), new ItemStack(
						ModItems.mdust), new ItemStack(Items.diamond),
				new ItemStack(ModItems.bloodie), new ItemStack(
						ModItems.catalyst, 1, 1), anywhere, anytime, 18));

		lis.add(new RitualRecipe(new ItemStack(ModItems.drop, 1, 3),
				new ItemStack(ModItems.drop, 1, 2), new ItemStack(
						ModItems.mdust), new ItemStack(Items.nether_star),
				new ItemStack(ModItems.bloodie), new ItemStack(
						ModItems.catalyst, 1, 2), anywhere, anytime, 27));

	}
}
