package mrriegel.rwl.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	public static Configuration configuration;
	public static boolean wailaAltar;
	public static String wailaDirection;

	public static void init(File file) {
		if (configuration == null) {
			configuration = new Configuration(file);
		}
		try {
			configuration.load();

			wailaAltar = configuration.get(Configuration.CATEGORY_GENERAL,
					"wailaIngredients", true, "show ingredients in waila")
					.getBoolean();
			wailaDirection = configuration.get(Configuration.CATEGORY_GENERAL,
					"wailaDirection", "vertical", "show ingredients in waila")
					.getString();

		} catch (Exception e) {

		} finally {
			if (configuration.hasChanged()) {
				configuration.save();
			}
		}

	}

}
