package mrriegel.rwl.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIConfig implements IConfigureNEI {

	@Override
	public String getName() {
		return "RWL NEI";
	}

	@Override
	public String getVersion() {
		return "weiß nich";
	}

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new RecipeHandler());
		API.registerUsageHandler(new RecipeHandler());
	}

}
