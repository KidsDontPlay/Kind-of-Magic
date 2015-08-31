package mrriegel.rwl.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	public static Configuration configuration;

	public static boolean wailaAltar;
	public static String wailaDirection;

	static final String CLIENT = "Client";
	static final String COMMON = "Common";

	public static void init(File file) {
		if (configuration == null) {
			configuration = new Configuration(file);
		}
		try {
			configuration.load();

			wailaAltar = configuration.get(CLIENT, "wailaIngredients", true,
					"show ingredients of secrect stone in waila").getBoolean();
			wailaDirection = configuration.get(CLIENT, "wailaDirection",
					"vertical", "direction of ingredients").getString();

		} catch (Exception e) {

		} finally {
			if (configuration.hasChanged()) {
				configuration.save();
			}
		}

	}

}
