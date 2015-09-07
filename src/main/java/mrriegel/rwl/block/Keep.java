package mrriegel.rwl.block;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.init.LandRecipe;
import mrriegel.rwl.init.LandRecipes;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.init.RitualRecipe;
import mrriegel.rwl.init.RitualRecipes;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.tile.MazerTile;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.RWLUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
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
		if (world.isRemote) {
			return false;
		}
		if (world.getTileEntity(x, y - 2, z) == null)
			return false;
		MazerTile tile = (MazerTile) world.getTileEntity(x, y - 2, z);
		if (!player.isSneaking() && player.getHeldItem() != null
				&& player.getHeldItem().getItem().equals(ModItems.catalyst)
				&& tile != null && tile.isActive()
				&& MazerB.isConstruct(world, x, y - 2, z)) {

			ItemStack stack = player.getHeldItem();
			for (RitualRecipe r : RitualRecipes.lis) {
				if (!tile.isProcessing()) {
					if (r.matches(tile.getInv(), world, player,
							stack.getItemDamage(), tile)) {
						if (player instanceof FakePlayer) {
							ForgeDirection fd = null;
							BlockLocation flui = tile.getXPContainer(r.getXp());
							if (flui == null)
								return false;

							IFluidHandler fh = null;
							if (world.getTileEntity(flui.x, flui.y, flui.z) != null
									&& world.getTileEntity(flui.x, flui.y,
											flui.z) instanceof IFluidHandler)
								fh = (IFluidHandler) world.getTileEntity(
										flui.x, flui.y, flui.z);
							if (fh == null)
								return false;
							fd = RWLUtils.getForgeDirectionOfBlock(
									new BlockLocation(x, y - 2, z),
									new BlockLocation(flui.x, flui.y, flui.z));
							if (fd == null)
								return false;
							for (BlockLocation bl : RWLUtils.getAroundBlocks(
									tile.xCoord, tile.yCoord, tile.zCoord)) {
								if (bl.x != tile.xCoord && bl.z != tile.zCoord) {
									continue;
								}
								if (world.getTileEntity(bl.x, bl.y, bl.z) != null
										&& world.getTileEntity(bl.x, bl.y, bl.z) instanceof IInventory) {
									IInventory inv = (IInventory) world
											.getTileEntity(bl.x, bl.y, bl.z);
									fd = RWLUtils
											.getForgeDirectionOfBlock(
													new BlockLocation(x, y - 2,
															z),
													new BlockLocation(bl.x,
															bl.y, bl.z));
									if (RWLUtils.canInsert(r.getOutput(), inv)) {
										tile.clear();
										tile.setProcessing(true);
										tile.setCooldown(75);
										tile.setStack(r.getOutput());
										tile.setPlayer(player);
										world.markBlockForUpdate(x, y - 2, z);
										if (fd == null)
											continue;
										fh.drain(fd, r.getXp() * 340, true);

										return true;
									}
								}
							}
						}
						if (!(player instanceof FakePlayer)) {
							tile.clear();
							tile.setProcessing(true);
							tile.setCooldown(75);
							tile.setStack(r.getOutput());
							tile.setPlayer(player);
							tile.setName(player.getDisplayName());
							world.markBlockForUpdate(x, y - 2, z);
							if (!player.capabilities.isCreativeMode)
								player.experienceLevel = player.experienceLevel
										- r.getXp();

							return true;
						}

					}

				}
			}
			for (LandRecipe r : LandRecipes.lis) {
				if (!tile.isProcessing()) {
					if (r.matches(tile.getInv(), world, player,
							stack.getItemDamage(), tile)) {
						if (player instanceof FakePlayer) {
							ForgeDirection fd = null;
							BlockLocation flui = tile.getXPContainer(r.getXp());
							if (flui == null)
								return false;

							IFluidHandler fh = null;
							if (world.getTileEntity(flui.x, flui.y, flui.z) != null
									&& world.getTileEntity(flui.x, flui.y,
											flui.z) instanceof IFluidHandler)
								fh = (IFluidHandler) world.getTileEntity(
										flui.x, flui.y, flui.z);
							if (fh == null)
								return false;
							fd = RWLUtils.getForgeDirectionOfBlock(
									new BlockLocation(x, y - 2, z),
									new BlockLocation(flui.x, flui.y, flui.z));
							tile.clear();
							world.markBlockForUpdate(x, y - 2, z);
							fh.drain(fd, r.getXp() * 340, true);
						}

						if (!(player instanceof FakePlayer)) {
							tile.clear();
							world.markBlockForUpdate(x, y - 2, z);
							player.experienceLevel = player.experienceLevel
									- r.getXp();
						}

						if (r.getOutput() instanceof String) {
							if (((String) r.getOutput()).equals("Day")
									&& !world.isRemote) {
								long ku = world.getWorldTime() % 24000;
								world.setWorldTime(world.getWorldTime() - ku);
								world.setWorldTime(world.getWorldTime() + 24000);
							} else if (((String) r.getOutput()).equals("Night")) {
								long ku = world.getWorldTime() % 24000;
								world.setWorldTime(world.getWorldTime() - ku);
								world.setWorldTime(world.getWorldTime() + 36000);
							}
							return true;
						} else if (r.getOutput() instanceof Class
								&& !world.isRemote) {
							for (int i = 0; i < r.getNumber(); i++) {
								Class<?> cl = (Class) r.getOutput();
								if (cl.getName().contains("boss"))
									return false;
								Constructor<?> ct = null;
								try {
									ct = cl.getConstructor(World.class);
								} catch (NoSuchMethodException e1) {
									e1.printStackTrace();
								} catch (SecurityException e1) {
									e1.printStackTrace();
								}
								EntityLivingBase e = null;
								try {
									e = (EntityLivingBase) ct
											.newInstance(new Object[] { world });
								} catch (InstantiationException e1) {
									e1.printStackTrace();
								} catch (IllegalAccessException e1) {
									e1.printStackTrace();
								} catch (IllegalArgumentException e1) {
									e1.printStackTrace();
								} catch (InvocationTargetException e1) {
									e1.printStackTrace();
								}
								double xtmp = world.rand.nextBoolean() ? -1.0d
										: 1.0d;
								double ztmp = world.rand.nextBoolean() ? -1.0d
										: 1.0d;
								e.posX = x + world.rand.nextDouble() * 1.5D
										* xtmp;
								e.posY = y + 0.5D;
								e.posZ = z + world.rand.nextDouble() * 1.5D
										* ztmp;
								if ((RWLUtils.double2int(e.posX) == x && RWLUtils
										.double2int(e.posZ) == z)
										|| (RWLUtils.double2int(e.posX) == x + 2 && RWLUtils
												.double2int(e.posZ) == z + 2)
										|| (RWLUtils.double2int(e.posX) == x - 2 && RWLUtils
												.double2int(e.posZ) == z + 2)
										|| (RWLUtils.double2int(e.posX) == x + 2 && RWLUtils
												.double2int(e.posZ) == z - 2)
										|| (RWLUtils.double2int(e.posX) == x - 2 && RWLUtils
												.double2int(e.posZ) == z - 2)) {
									i--;
									continue;
								}
								world.spawnEntityInWorld(e);
								e.setPositionAndUpdate(e.posX, e.posY, e.posZ);
							}
							return true;
						}
					}
				}
			}
		}

		return false;

	}
}
