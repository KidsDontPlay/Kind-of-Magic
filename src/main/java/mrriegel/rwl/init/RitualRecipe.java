package mrriegel.rwl.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.oredict.OreDictionary;

public class RitualRecipe {

	private ItemStack output;
	private Object input1;
	private Object input2;
	private Object input3;
	private Object input4;
	private int cat;
	private int dimensionID, time;
	private int xp;

	public RitualRecipe(ItemStack output, Object input1, Object input2,
			Object input3, Object input4, int cat, int dimensionID, int time,
			int xp) {
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

	private boolean contains(ItemStack stack, List<ArrayList> soll) {
		for (ArrayList<ItemStack> l : soll) {
			for (ItemStack s : l) {
				if (OreDictionary.itemMatches(s, stack, false)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean eq(List<ItemStack> ist, List<ArrayList> soll) {
		for (ItemStack s : ist) {
			if (!contains(s, soll)) {
				return false;
			}
		}
		return true;
	}

	private int day(World world, int time2) {
		if (world.provider.dimensionId != 0)
			return time2;
		long time = world.getWorldTime() % 24000;
		if (time < 12300 || time > 23850)
			return 0;
		else
			return 1;
	}

	public boolean matches(ItemStack[] ar, World world, EntityPlayer player,
			int catmeta) {

		List<ItemStack> ist = Arrays.asList(ar);
		ArrayList<ItemStack> inputI;
		ArrayList<ItemStack> inputII;
		ArrayList<ItemStack> inputIII;
		ArrayList<ItemStack> inputIIII;
		if (input1 instanceof String)
			inputI = OreDictionary.getOres((String) input1);
		else {
			inputI = new ArrayList<ItemStack>();
			inputI.add((ItemStack) input1);
		}
		if (input2 instanceof String)
			inputII = OreDictionary.getOres((String) input2);
		else {
			inputII = new ArrayList<ItemStack>();
			inputII.add((ItemStack) input2);
		}
		if (input3 instanceof String)
			inputIII = OreDictionary.getOres((String) input3);
		else {
			inputIII = new ArrayList<ItemStack>();
			inputIII.add((ItemStack) input3);
		}
		if (input4 instanceof String)
			inputIIII = OreDictionary.getOres((String) input4);
		else {
			inputIIII = new ArrayList<ItemStack>();
			inputIIII.add((ItemStack) input4);
		}
		List<ArrayList> soll = new ArrayList<ArrayList>();
		soll.add(inputI);
		soll.add(inputII);
		soll.add(inputIII);
		soll.add(inputIIII);
		int tmpdim = dimensionID;
		int tmptim = time;
		if (time != -1)
			tmptim = day(world, time);
		if (dimensionID != Integer.MAX_VALUE)
			tmpdim = world.provider.dimensionId;
		if (eq(ist, soll)
				&& tmpdim == dimensionID
				&& tmptim == time
				&& catmeta >= cat
				&& ((player.experienceLevel >= xp || player.capabilities.isCreativeMode) || player instanceof FakePlayer))
			return true;
		if (eq(ist, soll) && !world.isRemote) {
			if (!(catmeta >= cat))
				player.addChatMessage(new ChatComponentText("Catalyst too weak"));
			if (!(tmpdim == dimensionID))
				player.addChatMessage(new ChatComponentText("False Dimension"));
			if (!(tmptim == time))
				player.addChatMessage(new ChatComponentText("False Time"));
			if (!(player.experienceLevel >= xp || player.capabilities.isCreativeMode))
				player.addChatMessage(new ChatComponentText("Not enough XP"));
		}
		return 0 == 1;
	}

	public ItemStack getOutput() {
		if (output != null) {
			if (output.stackSize <= 0)
				output.stackSize = 1;
			return output;
		} else
			return null;
	}

	public void setOutput(ItemStack output) {
		this.output = output;
	}

	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}

	public Object getInput1() {
		return input1;
	}

	public Object getInput2() {
		return input2;
	}

	public Object getInput3() {
		return input3;
	}

	public Object getInput4() {
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
