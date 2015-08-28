package mrriegel.rwl.item;

import java.util.List;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Drop extends Item {
	public Drop() {
		super();
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "drop");
	}

	public IIcon[] icons = new IIcon[4];

	@Override
	public void registerIcons(IIconRegister reg) {
		for (int i = 0; i < 4; i++) {
			this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":drop_" + i);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta > 3)
			meta = 0;

		return this.icons[meta];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 4; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
}
