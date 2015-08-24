package mrriegel.rwl.item;

import java.util.List;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Crysthal extends Item {

	public IIcon[] icons = new IIcon[16];

	public Crysthal() {
		super();
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "cry");
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		for (int i = 0; i < 16; i++) {
			this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":cry_" + i);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta > 15)
			meta = 0;

		return this.icons[meta];
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean p_77624_4_) {
		super.addInformation(stack, player, list, p_77624_4_);

		switch (stack.getItemDamage()) {
		case 0:
			list.add("Pickaxe, Shovel, Axe");
			break;
		case 1:
			list.add("Pickaxe, Shovel, Axe");
			break;
		case 2:
			list.add("Pickaxe, Shovel, Axe");
			break;
		case 3:
			list.add("Pickaxe, Shovel");
			break;
		case 4:
			list.add("Pickaxe, Sword");
			break;
		case 5:
			list.add("Pickaxe, Shovel, Axe");
			break;
		case 6:
			list.add("Sword");
			break;
		case 7:
			list.add("Sword");
			break;
		case 8:
			list.add("Axe");
			break;
		case 9:
			list.add("Sword");
			break;
		case 10:
			list.add("Sword");
			break;
		case 11:
			list.add("Sword");
			break;
		case 12:
			list.add("Sword");
			break;
		case 13:
			list.add("Pickaxe, Sword");
			break;
		case 14:
			list.add("Pickaxe");
			break;
		case 15:
			list.add("Pickaxe");
			break;
		}
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}

}
