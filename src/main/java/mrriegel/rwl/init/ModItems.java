package mrriegel.rwl.init;

import java.util.ArrayList;
import java.util.List;

import mrriegel.rwl.item.AirOreDust;
import mrriegel.rwl.item.Amber;
import mrriegel.rwl.item.BloodRelic;
import mrriegel.rwl.item.Bloodie;
import mrriegel.rwl.item.Catalyst;
import mrriegel.rwl.item.CrystalBag;
import mrriegel.rwl.item.Crysthal;
import mrriegel.rwl.item.Drop;
import mrriegel.rwl.item.Dung;
import mrriegel.rwl.item.Flash;
import mrriegel.rwl.item.ItemBreather;
import mrriegel.rwl.item.ItemCooler;
import mrriegel.rwl.item.ItemExtinger;
import mrriegel.rwl.item.ItemFeeder;
import mrriegel.rwl.item.ItemFlyer;
import mrriegel.rwl.item.ItemJumper;
import mrriegel.rwl.item.ItemRepair;
import mrriegel.rwl.item.ItemSprinter;
import mrriegel.rwl.item.ItemStepper;
import mrriegel.rwl.item.ItemVision;
import mrriegel.rwl.item.Light;
import mrriegel.rwl.item.Lseeds;
import mrriegel.rwl.item.MazeDust;
import mrriegel.rwl.item.Nev;
import mrriegel.rwl.item.NevAxe;
import mrriegel.rwl.item.NevPick;
import mrriegel.rwl.item.NevShovel;
import mrriegel.rwl.item.NevSword;
import mrriegel.rwl.item.OreDust;
import mrriegel.rwl.item.OreStick;
import mrriegel.rwl.item.PanStick;
import mrriegel.rwl.item.Resin;
import mrriegel.rwl.item.Shoot;
import mrriegel.rwl.item.TaliBag;
import mrriegel.rwl.item.Up;
import mrriegel.rwl.reference.Reference;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	public static final Item bloodie = new Bloodie();
	public static final Item relic = new BloodRelic();
	public static final Item catalyst = new Catalyst();
	public static final Item oredust = new OreDust();
	public static final Item airoredust = new AirOreDust();
	public static final Item mdust = new MazeDust();
	public static final Item nevsword = new NevSword();
	public static final Item nevpick = new NevPick();
	public static final Item nevshovel = new NevShovel();
	public static final Item nevaxe = new NevAxe();
	public static final Item ostick = new OreStick();
	public static final Item panstick = new PanStick();
	public static final Item resin = new Resin();
	public static final Item lseeds = new Lseeds(ModBlocks.lplant,
			Blocks.farmland);
	public static final Item amber = new Amber();
	public static final Item drop = new Drop();
	// public static final Item wnugget = new WNugget();
	public static final Item bag = new CrystalBag();
	public static final Item tbag = new TaliBag();
	public static final Item nev = new Nev();
	public static final Item cry = new Crysthal();
	public static final Item feeder = new ItemFeeder();
	public static final Item sprinter = new ItemSprinter();
	public static final Item repair = new ItemRepair();
	public static final Item stepper = new ItemStepper();
	public static final Item flyer = new ItemFlyer();
	public static final Item breather = new ItemBreather();
	public static final Item vision = new ItemVision();
	public static final Item jumper = new ItemJumper();
	public static final Item cooler = new ItemCooler();
	public static final Item extinger = new ItemExtinger();
	// public static Item decor = new ItemDecor();
	public static final Item dung = new Dung();
	public static final Item up = new Up();
	public static final Item light = new Light();
	public static final Item flash = new Flash();
	public static final Item shoot = new Shoot();

	static Item[] f = { bloodie, relic, catalyst, oredust, airoredust, mdust,
			nevsword, nevpick, nevshovel, nevaxe, ostick, panstick, resin,
			amber, drop, /* wnugget, */bag, tbag, nev, cry, feeder, sprinter,
			repair, stepper, flyer, breather, vision, jumper, cooler, extinger,
			dung, up, light, flash, shoot };

	public static List<Item> lis = new ArrayList<Item>();

	public static void init() {
		GameRegistry.registerItem(bloodie, "bloodie");
		bloodie.setContainerItem(Items.glass_bottle);
		GameRegistry.registerItem(relic, "relic");
		GameRegistry.registerItem(catalyst, "catalyst");
		GameRegistry.registerItem(oredust, "oredust");
		GameRegistry.registerItem(airoredust, "airoredust");
		GameRegistry.registerItem(mdust, "mdust");

		GameRegistry.registerItem(nevsword, "nevsword");
		GameRegistry.registerItem(nevpick, "nevpick");
		GameRegistry.registerItem(nevshovel, "nevshovel");
		GameRegistry.registerItem(nevaxe, "nevaxe");
		GameRegistry.registerItem(ostick, "ostick");
		GameRegistry.registerItem(panstick, "panstick");
		GameRegistry.registerItem(resin, "resin");
		GameRegistry.registerItem(lseeds, "lseeds");
		GameRegistry.registerItem(amber, "amber");
		GameRegistry.registerItem(drop, "drop");
		// GameRegistry.registerItem(wnugget, "wnugget");
		GameRegistry.registerItem(bag, "bag");
		GameRegistry.registerItem(tbag, "tbag");
		GameRegistry.registerItem(nev, "nev");
		GameRegistry.registerItem(cry, "cry");
		GameRegistry.registerItem(feeder, "feeder");
		GameRegistry.registerItem(sprinter, "sprinter");
		GameRegistry.registerItem(repair, "repair");
		GameRegistry.registerItem(stepper, "stepper");
		GameRegistry.registerItem(flyer, "flyer");
		GameRegistry.registerItem(breather, "breather");
		GameRegistry.registerItem(vision, "vision");
		GameRegistry.registerItem(jumper, "jumper");
		GameRegistry.registerItem(cooler, "cooler");
		GameRegistry.registerItem(extinger, "extinger");
		// GameRegistry.registerItem(decor, "decor");
		GameRegistry.registerItem(dung, "dung");
		GameRegistry.registerItem(up, "up");
		GameRegistry.registerItem(light, "light");
		GameRegistry.registerItem(flash, "flash");
		GameRegistry.registerItem(shoot, "shoot");

		for (Item i : f)
			lis.add(i);
	}
}
