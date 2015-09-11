package mrriegel.rwl.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.init.RitualRecipe;
import mrriegel.rwl.init.RitualRecipes;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class RitualRecipeHandler extends TemplateRecipeHandler {

	public class CachedRitualRecipe extends CachedRecipe {
		PositionedStack output;
		public List<PositionedStack> input = new ArrayList<PositionedStack>();
		PositionedStack cat;
		int time, dimensionID;
		public int xp;

		public CachedRitualRecipe(RitualRecipe r) {
			output = new PositionedStack(r.getOutput(), 75, 4, false);
			put(r);
			cat = new PositionedStack(new ItemStack(ModItems.catalyst, 1,
					r.getCat()), 125, 40, false);
			time = r.getTime();
			dimensionID = r.getDimensionID();
			xp = r.getXp();
		}

		private void put(RitualRecipe r) {
			if (r.getInput1() instanceof String)
				input.add(new PositionedStack(OreDictionary.getOres((String) r
						.getInput1()), 25, 40));
			else
				input.add(new PositionedStack(r.getInput1(), 25, 40));
			if (r.getInput2() instanceof String)
				input.add(new PositionedStack(OreDictionary.getOres((String) r
						.getInput2()), 45, 40));
			else
				input.add(new PositionedStack(r.getInput2(), 45, 40));
			if (r.getInput3() instanceof String)
				input.add(new PositionedStack(OreDictionary.getOres((String) r
						.getInput3()), 65, 40));
			else
				input.add(new PositionedStack(r.getInput3(), 65, 40));
			if (r.getInput4() instanceof String)
				input.add(new PositionedStack(OreDictionary.getOres((String) r
						.getInput4()), 85, 40));
			else
				input.add(new PositionedStack(r.getInput4(), 85, 40));
		}

		@Override
		public PositionedStack getResult() {
			return output;
		}

		@Override
		public List<PositionedStack> getIngredients() {
			input.add(cat);
			return getCycledIngredients(cycleticks / 20, input);
		}

	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("tile.rwl:mazerB.name");
	}

	@Override
	public String getGuiTexture() {
		return new ResourceLocation(Reference.MOD_ID + ":"
				+ "textures/gui/nei.png").toString();
	}

	@Override
	public void drawBackground(int recipe) {
		super.drawBackground(recipe);
		if (!(arecipes.get(recipe) instanceof CachedRitualRecipe)) {
			return;
		}
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		CachedRitualRecipe r = (CachedRitualRecipe) arecipes.get(recipe);
		if (r.dimensionID == 0)
			GuiDraw.drawString("Overworld", 7, 15, 0x404040, false);
		else if (r.dimensionID == 1)
			GuiDraw.drawString("End", 7, 15, 0x404040, false);
		else if (r.dimensionID == -1)
			GuiDraw.drawString("Nether", 7, 15, 0x404040, false);
		else
			GuiDraw.drawString("Anywhere", 7, 15, 0x404040, false);
		if (r.time == 0)
			GuiDraw.drawString("Day", 7, 5, 0x404040, false);
		else if (r.time == 1)
			GuiDraw.drawString("Night", 7, 5, 0x404040, false);
		else
			GuiDraw.drawString("Anytime", 7, 5, 0x404040, false);
		GuiDraw.drawString(r.xp + " XP", 7, 25, 0x404040, false);
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(75, 23, 16, 16),
				"rwl:stone"));
	}

	@Override
	public int recipiesPerPage() {
		return 2;
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("rwl:stone")) {
			for (RitualRecipe recipe : RitualRecipes.lis) {
				arecipes.add(new CachedRitualRecipe(recipe));
			}

		} else
			super.loadCraftingRecipes(outputId, results);
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
			if (crecipe.contains(crecipe.input, ingredient)
					|| NEIServerUtils.areStacksSameTypeCrafting(new ItemStack(
							ModItems.catalyst, 1, recipe.getCat()), ingredient))
				arecipes.add(crecipe);

		}
	}
}
