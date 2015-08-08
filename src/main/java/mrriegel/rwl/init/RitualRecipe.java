package mrriegel.rwl.init;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RitualRecipe {

	private ItemStack output;
	private ItemStack input1;
	private ItemStack input2;
	private ItemStack input3;
	private ItemStack input4;
	private ItemStack cat;
	private int dimensionID, time;
	private int xp;

	public RitualRecipe(ItemStack output, ItemStack input1, ItemStack input2,
			ItemStack input3, ItemStack input4, ItemStack cat, int dimensionID,
			int time, int xp) {
		super();
		this.output = output;
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.input4 = input4;
		this.cat = cat;
		this.dimensionID = dimensionID;
		this.time = time;
		this.xp = xp;
	}

	private boolean contains(ItemStack stack, List<ItemStack> lis) {
		for (ItemStack s : lis)
			if (ItemStack.areItemStacksEqual(s, stack))
				return true;
		return false;
	}

	private boolean eq(List<ItemStack> a, List<ItemStack> b) {
		for (ItemStack s : a)
			if (!contains(s, b))
				return false;
		return true;
	}

	private int day(World world) {
		long time = world.getWorldTime() % 24000;
		if (time < 12300 || time > 23850)
			return 0;
		else
			return 1;
	}

	public boolean matches(ItemStack[] ar, World world) {

		List<ItemStack> ist = Arrays.asList(ar);
		List<ItemStack> soll = Arrays.asList(new ItemStack[] { input1, input2,
				input3, input4 });
		int tmpdim = dimensionID;
		int tmptim = time;
		if (time != -1)
			tmptim = day(world);
		if (dimensionID != Integer.MAX_VALUE)
			tmpdim = world.provider.dimensionId;
		if (eq(ist, soll) && tmpdim == dimensionID && tmptim == time)
			return true;

		return 0 == 1;
	}

	public ItemStack getOutput() {
		return output;
	}

	public void setOutput(ItemStack output) {
		this.output = output;
	}

	public ItemStack getCat() {
		return cat;
	}

	public void setCat(ItemStack cat) {
		this.cat = cat;
	}

	public ItemStack getInput1() {
		return input1;
	}

	public ItemStack getInput2() {
		return input2;
	}

	public ItemStack getInput3() {
		return input3;
	}

	public ItemStack getInput4() {
		return input4;
	}

	public int getDimensionID() {
		return dimensionID;
	}

	public int getTime() {
		return time;
	}

	public int getXp() {
		return xp;
	}
}
