package mrriegel.rwl.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mrriegel.rwl.init.RitualRecipe;
import mrriegel.rwl.init.RitualRecipes;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class RecipeHandler extends TemplateRecipeHandler {

	public class CachedRitualRecipe extends CachedRecipe {
		PositionedStack output;
		public List<PositionedStack> input = new ArrayList<PositionedStack>();
		PositionedStack cat;

		public CachedRitualRecipe(RitualRecipe r) {
			output = new PositionedStack(r.getOutput(), 75, 2, false);
			input.add(new PositionedStack(r.getInput1(), 25, 40, false));
			input.add(new PositionedStack(r.getInput2(), 45, 40, false));
			input.add(new PositionedStack(r.getInput3(), 65, 40, false));
			input.add(new PositionedStack(r.getInput4(), 85, 40, false));
			cat = new PositionedStack(r.getCat(), 125, 40, false);
		}

		@Override
		public PositionedStack getResult() {
			return output;
		}

		@Override
		public List<PositionedStack> getIngredients() {
			input.add(cat);
			return input;
		}

	}

	@Override
	public String getRecipeName() {
		return "Stone";
	}

	@Override
	public String getGuiTexture() {
		return new ResourceLocation(Reference.MOD_ID + ":"
				+ "textures/gui/nei.png").toString();
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(75, 21, 16, 16),
				"another"));
	}

	@Override
	public int recipiesPerPage() {
		return 2;
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (RitualRecipe recipe : RitualRecipes.lis) {

			if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getOutput(),
					result))
				arecipes.add(new CachedRitualRecipe(recipe));
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (RitualRecipe recipe : RitualRecipes.lis) {

			CachedRitualRecipe crecipe = new CachedRitualRecipe(recipe);
			arecipes.add(crecipe);

		}
	}
}
