package mrriegel.rwl.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LandRecipes {
	public static List<LandRecipe> lis;
	final static int anywhere = Integer.MAX_VALUE;
	final static int overworld = 0;
	final static int nether = -1;
	final static int end = 1;
	final static int anytime = -1;
	final static int day = 0;
	final static int night = 1;

	public static void init() {
		lis = new ArrayList<LandRecipe>();
		lis.add(new LandRecipe("Day", new ItemStack(Items.gold_ingot),
				new ItemStack(Items.ender_pearl), new ItemStack(
						Items.flint_and_steel), new ItemStack(
						Items.glowstone_dust), 0, anywhere, night, 3, 0));
		lis.add(new LandRecipe("Night", new ItemStack(Items.gold_ingot),
				new ItemStack(Items.ender_pearl), new ItemStack(
						Items.flint_and_steel), new ItemStack(Items.redstone),
				0, anywhere, day, 3, 0));
		lis.add(new LandRecipe(EntityPig.class,
				new ItemStack(ModItems.bloodie), new ItemStack(
						Items.speckled_melon), new ItemStack(
						Items.writable_book), new ItemStack(Items.porkchop), 1,
				anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntitySheep.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(Blocks.wool),
				1, anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntityCow.class,
				new ItemStack(ModItems.bloodie), new ItemStack(
						Items.speckled_melon), new ItemStack(
						Items.writable_book), new ItemStack(Items.beef), 1,
				anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntityChicken.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book),
				new ItemStack(Items.feather), 1, anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntityMooshroom.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Blocks.red_mushroom), 1, anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntityBat.class,
				new ItemStack(ModItems.bloodie), new ItemStack(
						Items.speckled_melon), new ItemStack(
						Items.writable_book), new ItemStack(Items.sugar), 1,
				anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntityWolf.class,
				new ItemStack(ModItems.bloodie), new ItemStack(
						Items.speckled_melon), new ItemStack(
						Items.writable_book), new ItemStack(Items.bone), 1,
				anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntityHorse.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.golden_carrot), 1, anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntityOcelot.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(Items.fish),
				1, anywhere, anytime, 5, 4));
		lis.add(new LandRecipe(EntityVillager.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book),
				new ItemStack(Items.emerald), 1, anywhere, anytime, 5, 3));
		lis.add(new LandRecipe(EntityEnderman.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.ender_pearl), 1, anywhere, night, 5, 2));
		lis.add(new LandRecipe(EntityPigZombie.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.gold_nugget), 1, anywhere, anytime, 5, 3));
		lis.add(new LandRecipe(EntityZombie.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.rotten_flesh), 1, anywhere, night, 5, 3));
		lis.add(new LandRecipe(EntitySkeleton.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(Items.bow),
				1, anywhere, night, 5, 3));
		lis.add(new LandRecipe(EntitySpider.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.spider_eye), 1, anywhere, night, 5, 3));
		lis.add(new LandRecipe(EntityCaveSpider.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book),
				new ItemStack(Items.string), 1, anywhere, night, 5, 3));
		lis.add(new LandRecipe(EntityCreeper.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.gunpowder), 1, anywhere, night, 5, 3));
		lis.add(new LandRecipe(EntitySlime.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.slime_ball), 1, anywhere, night, 5, 2));
		lis.add(new LandRecipe(EntityGhast.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.ghast_tear), 1, anywhere, anytime, 5, 1));
		lis.add(new LandRecipe(EntityMagmaCube.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.magma_cream), 1, anywhere, anytime, 5, 2));
		lis.add(new LandRecipe(EntityBlaze.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.blaze_rod), 1, anywhere, anytime, 5, 3));
		lis.add(new LandRecipe(EntityWitch.class, new ItemStack(
				ModItems.bloodie), new ItemStack(Items.speckled_melon),
				new ItemStack(Items.writable_book), new ItemStack(
						Items.glass_bottle), 1, anywhere, night, 5, 2));
	}
}
