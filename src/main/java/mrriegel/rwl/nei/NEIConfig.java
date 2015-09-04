package mrriegel.rwl.nei;

import mrriegel.rwl.reference.Reference;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIConfig implements IConfigureNEI {

	@Override
	public String getName() {
		return Reference.MOD_ID + " NEI";
	}

	@Override
	public String getVersion() {
		return Reference.VERSION;
	}

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new RitualRecipeHandler());
		API.registerUsageHandler(new RitualRecipeHandler());
		API.registerRecipeHandler(new LandRecipeHandler());
		API.registerUsageHandler(new LandRecipeHandler());
	}

}
