package mrriegel.rwl.item;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class NevSword extends ItemSword {
	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 0, 2222, 10.0F, 5.5F, 30);

	public NevSword() {
		super(MATERIAL);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevsword");
		this.setTextureName(Reference.MOD_ID + ":" + "nevsword");
	}

}
