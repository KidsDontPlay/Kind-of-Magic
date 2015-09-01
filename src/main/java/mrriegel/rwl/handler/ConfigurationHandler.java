package mrriegel.rwl.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	public static Configuration configuration;

	public static boolean wailaAltar;
	public static String wailaDirection;

	public static boolean swordHUD;
	public static boolean pickHUD;
	public static boolean shovelHUD;
	public static boolean axeHUD;
	public static String HUDType;
	public static int structureRarity;
	public static int nevOreRarity;
	public static int lightNevOreRarity;

	static final String CLIENT = "Client";
	static final String COMMON = "Common";

	public static boolean refreshConfig() {

		/* client */
		wailaAltar = configuration.get(CLIENT, "wailaAltar", true,
				"show ingredients of secrect stone in waila").getBoolean(true);
		wailaDirection = configuration.get(CLIENT, "wailaDirection",
				"vertical", "direction of ingredients").getString();
		swordHUD = configuration.get(CLIENT, "swordHUD", false,
				"shows the name/icon of the crystal while holding Nev Sword")
				.getBoolean(false);
		pickHUD = configuration.get(CLIENT, "pickHUD", true,
				"shows the name/icon of the crystal while holding Nev Pickaxe")
				.getBoolean(true);
		shovelHUD = configuration.get(CLIENT, "shovelHUD", true,
				"shows the name/icon of the crystal while holding Nev Shovel")
				.getBoolean(true);
		axeHUD = configuration.get(CLIENT, "axeHUD", true,
				"shows the name/icon of the crystal while holding Nev Axe")
				.getBoolean(true);
		HUDType = configuration.get(CLIENT, "HUDType", "name", "name or icon")
				.getString();

		structureRarity = configuration.get(COMMON, "structureRarity", 90,
				"rarity of generated shrines").getInt(90);
		nevOreRarity = configuration.get(COMMON, "nevOreRarity", 40,
				"rarity of nev ore").getInt(40);
		lightNevOreRarity = configuration.get(COMMON, "lightNevOreRarity", 100,
				"rarity of light nev ore").getInt(100);

		if (configuration.hasChanged()) {
			configuration.save();
		}
		return true;
	}

}
