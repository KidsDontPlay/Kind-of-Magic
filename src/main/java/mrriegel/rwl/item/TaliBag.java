package mrriegel.rwl.item;

import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TaliBag extends Item {
	public TaliBag() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "tbag");
		this.setTextureName(Reference.MOD_ID + ":" + "tbag");

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		player.openGui(RWL.instance, GuiIDs.TALIBAG, world, 0, 0, 0);
		return stack;

	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1;
	}
}
