package mrriegel.rwl.block;

import java.util.Random;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.init.RitualRecipe;
import mrriegel.rwl.init.RitualRecipes;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Keep extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[6];

	public Keep() {
		super(Material.rock);
		this.setHardness(1.5f);
		this.setCreativeTab(CreativeTab.tab1);
		this.setBlockName(Reference.MOD_ID + ":" + "keep");
		setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
		setLightLevel(1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			this.icons[i] = reg.registerIcon(Reference.MOD_ID + ":" + "keep");

		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];

	}

	@Override
	public boolean isOpaqueCube() {
		return !super.isOpaqueCube();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return !super.renderAsNormalBlock();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		MazerTile tile = (MazerTile) world.getTileEntity(x, y - 2, z);

		if (!player.isSneaking() && player.getHeldItem() != null
				&& player.getHeldItem().getItem().equals(ModItems.catalyst)
				&& tile != null && tile.isActive()
				&& MazerB.isConstruct(world, x, y - 2, z)) {

			ItemStack stack = player.getHeldItem();
			for (RitualRecipe r : RitualRecipes.lis) {
				if (!tile.isProcessing()) {
					if (r.matches(tile.getInv(), world, player,
							stack.getItemDamage())) {
						Random ran = new Random();
						tile.clear();
						tile.setProcessing(true);
						tile.setCooldown(75);
						tile.setStack(r.getOutput());
						tile.setPlayer(player);

						player.experienceLevel = player.experienceLevel
								- r.getXp();

						return true;

					}

				}
			}
		}
		return false;

	}
}
