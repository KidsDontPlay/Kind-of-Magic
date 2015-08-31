package mrriegel.rwl.item;

import java.util.List;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModBlocks;
import mrriegel.rwl.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Catalyst extends Item {
	public IIcon[] icons = new IIcon[3];

	public Catalyst() {
		super();
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "catalyst");

	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
		return false;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return itemStack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int s, float xs, float ys, float zs) {
		if (world.getBlock(x, y, z).equals(Blocks.lapis_block)
				&& !world.isRemote) {
			world.playAuxSFX(2001, x, y, z,
					Block.getIdFromBlock(Blocks.lapis_block) + (0 << 12));
			world.setBlock(x, y, z, ModBlocks.mazer);
			return true;
		}
		return false;
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		for (int i = 0; i < 3; i++) {
			this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":catalyst_"
					+ i);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta > 2)
			meta = 0;

		return this.icons[meta];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 3; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
}
