package mrriegel.rwl.item;

import java.util.List;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemEdelstein extends Item {
	public int cooldown;

	public ItemEdelstein() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setMaxStackSize(1);
	}

	@Override
	abstract public boolean onItemUse(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float p_77648_8_,
			float p_77648_9_, float p_77648_10_);

	@Override
	abstract public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player);

	@Override
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_,
			Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		if (NBTHelper.getInt(p_77663_1_, "cooldown") != 0)
			NBTHelper.setInteger(p_77663_1_, "cooldown",
					NBTHelper.getInt(p_77663_1_, "cooldown") - 1);

	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean boo) {
		list.add("Cooldown: " + NBTHelper.getInt(stack, "cooldown") / 20);
	}

}
