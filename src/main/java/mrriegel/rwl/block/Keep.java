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
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
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
	public void onEntityCollidedWithBlock(World world, int x, int y, int z,
			Entity entity) {
		if (entity instanceof EntityFX) {
			EntityFX e = (EntityFX) entity;
			e.motionY = -e.motionY;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		MazerTile tile = (MazerTile) world.getTileEntity(x, y - 2, z);

		if (!player.isSneaking() && player.getHeldItem() != null
				&& player.getHeldItem().getItem().equals(ModItems.catalyst)
				&& tile.isActive() && MazerB.isConstruct(world, x, y - 2, z)) {
			ItemStack stack = player.getHeldItem();
			for (RitualRecipe r : RitualRecipes.lis) {
				if (ItemStack.areItemStacksEqual(stack, r.getCat())) {
					if (r.matches(tile.getInv(), world)) {
						tile.clear();
						player.inventory
								.setInventorySlotContents(
										player.inventory.currentItem,
										new ItemStack(
												player.getHeldItem().getItem(),
												player.getCurrentEquippedItem().stackSize - 1));
						Random ran = new Random();
						for (int i = 0; i < 20; i++) {
							world.spawnParticle("happyVillager",
									x + ran.nextDouble(),
									(y + 0.6d + ran.nextDouble() / 1.5) - 1.8D,
									z + ran.nextDouble(), 0, 0, 0);
						}
						if (!world.isRemote) {
							EntityItem ei = new EntityItem(world, x + 0.5d,
									y + 0.5d, z - 1.5d, r.getOutput());

							world.spawnEntityInWorld(ei);
							ei.setPosition(player.posX, player.posY,
									player.posZ);
							player.addChatMessage(new ChatComponentText("Done!"));
						}
						world.markBlockForUpdate(x, y - 2, z);
						return true;
					}
				}
			}

			// back
		}
		return false;

	}
}
