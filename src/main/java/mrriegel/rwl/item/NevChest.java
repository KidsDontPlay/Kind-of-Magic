package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class NevChest extends ItemArmor {
	public static ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial(
			"MATERIAL", 35, new int[] { 4, 8, 6, 4 }, 14);

	public NevChest() {
		super(MATERIAL, 0, 1);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevchest");
		this.setTextureName(Reference.MOD_ID + ":" + "nevchest");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return Reference.MOD_ID + ":textures/armor/" + "nev_1.png";
	}
}
