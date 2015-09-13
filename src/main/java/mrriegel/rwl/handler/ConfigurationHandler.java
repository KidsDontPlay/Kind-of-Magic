package mrriegel.rwl.handler;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	public static Configuration config;

	public static boolean wailaAltar;
	public static String wailaDirection;

	public static boolean swordHUD;
	public static boolean pickHUD;
	public static boolean shovelHUD;
	public static boolean axeHUD;
	public static String HUDType;
	public static boolean renderCrystal;

	public static int structureRarity;
	public static int nevOreRarity;
	public static int lightNevOreRarity;

	public static int floralRadius;
	public static int floralRadiusAdvanced;
	public static int floralSpeed;
	public static int floralSpeedAdvanced;

	static final String CLIENT = "Client";
	static final String COMMON = "Common";

	public static boolean refreshConfig() {

		/* client */
		wailaAltar = config.get(CLIENT, "wailaAltar", true,
				"show ingredients of secrect stone in waila").getBoolean(true);
		wailaDirection = config.get(CLIENT, "wailaDirection", "vertical",
				"direction of ingredients").getString();
		swordHUD = config.get(CLIENT, "swordHUD", false,
				"shows the name/icon of the crystal while holding Nev Sword")
				.getBoolean(false);
		pickHUD = config.get(CLIENT, "pickHUD", true,
				"shows the name/icon of the crystal while holding Nev Pickaxe")
				.getBoolean(true);
		shovelHUD = config.get(CLIENT, "shovelHUD", true,
				"shows the name/icon of the crystal while holding Nev Shovel")
				.getBoolean(true);
		axeHUD = config.get(CLIENT, "axeHUD", true,
				"shows the name/icon of the crystal while holding Nev Axe")
				.getBoolean(true);
		renderCrystal = config.get(CLIENT, "renderCrystal", true,
				"render crystal on nev tool").getBoolean(true);
		HUDType = config.get(CLIENT, "HUDType", "name", "name or icon")
				.getString();

		/* common */
		structureRarity = config.get(COMMON, "structureRarity", 90,
				"rarity of generated shrines").getInt(90);
		nevOreRarity = config.get(COMMON, "nevOreRarity", 50,
				"rarity of nev ore").getInt(50);
		lightNevOreRarity = config.get(COMMON, "lightNevOreRarity", 100,
				"rarity of light nev ore").getInt(100);

		floralRadius = config.get(COMMON, "floralRadius", 1,
				"radius of floralizer").getInt(1);
		floralRadiusAdvanced = config.get(COMMON, "floralRadiusAdvanced", 3,
				"radius of upgraded floralizer").getInt(3);
		floralSpeed = config.get(COMMON, "floralSpeed", 130,
				"speed of floralizer").getInt(130);
		floralSpeedAdvanced = config.get(COMMON, "floralSpeedAdvanced", 75,
				"speed of upgraded floralizer").getInt(75);

		if (config.hasChanged()) {
			config.save();
		}
		return true;
	}

}
