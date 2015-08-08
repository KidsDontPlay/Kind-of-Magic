package mrriegel.rwl.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WNugget extends Item {
	public WNugget() {
		super();
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "wnugget");
		this.setTextureName(Reference.MOD_ID + ":" + "wnugget");

	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack p_77636_1_) {
		return true;
	}

}