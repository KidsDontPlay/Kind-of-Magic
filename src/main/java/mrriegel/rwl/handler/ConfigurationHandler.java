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

	static final String CLIENT = "Client";
	static final String COMMON = "Common";

	public static void init(File file) {
		if (configuration == null) {
			configuration = new Configuration(file);
		}
		try {
			configuration.load();
wailaDirection = configuration.get(CLIENT, "wailaDirection",
					"vertical", "direction of ingredients").getString();
			wailaAltar = configuration.get(CLIENT, "wailaIngredients", true,
					"show ingredients of secrect stone in waila").getBoolean();
			HUDType = configuration.get(CLIENT, "HUDType", "name",
					"name or icon").getString();
			swordHUD = configuration.get(CLIENT, "toolHUD", false,
					"shows the name/icon of the crystal while holding Nev Sword").getBoolean();
			pickHUD = configuration.get(CLIENT, "toolHUD", true,
					"shows the name/icon of the crystal while holding Nev Pickaxe").getBoolean();
			shovelHUD = configuration.get(CLIENT, "toolHUD", true,
					"shows the name/icon of the crystal while holding Nev Shovel").getBoolean();
			axeHUD = configuration.get(CLIENT, "toolHUD", true,
					"shows the name/icon of the crystal while holding Nev Axe").getBoolean();
			

		} catch (Exception e) {

		} finally {
			if (configuration.hasChanged()) {
				configuration.save();
			}
		}

	}

}
