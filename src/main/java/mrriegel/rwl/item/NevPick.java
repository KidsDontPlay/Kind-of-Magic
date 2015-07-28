package mrriegel.rwl.item;

import java.util.Vector;

import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.gui.InventoryNevPick;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.BlockLocation;
import mrriegel.rwl.utility.MyUtils;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class NevPick extends ItemPickaxe {
	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 2, 1999, 10.0F, 2.0F, 14);

	public NevPick() {
		super(MATERIAL);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevpick");
		this.setTextureName(Reference.MOD_ID + ":" + "nevpick");
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		player.openGui(RWL.instance, GuiIDs.NEVPICK, world, 0, 0, 0);
		return stack;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block,
			int x, int y, int z, EntityLivingBase player) {
		return super.onBlockDestroyed(stack, world, block, x, y, z, player);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z,
			EntityPlayer player) {
		if (player.worldObj.isRemote) {
			return false;
		}
		System.out.println("tags: "
				+ stack.getTagCompound()
						.getTagList(InventoryNevPick.tagName,
								stack.getTagCompound().getId())
						.getCompoundTagAt(0).getShort("Damage"));

		switch (stack
				.getTagCompound()
				.getTagList(InventoryNevPick.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage")) {
		case 0:
			radius(stack, x, y, z, player, 1);
			break;
		case 1:
			radius(stack, x, y, z, player, 2);
			break;
		case 2:
			radius(stack, x, y, z, player, 3);
			break;
		case 3:
			silk(stack, x, y, z, player);
			break;
		case 4:
			luck(stack, x, y, z, player);
			break;
		case 5:
			quick(stack, x, y, z, player);
			break;
		case 6:
			smelt();
			break;
		default:
			break;

		}

		return super.onBlockStartBreak(stack, x, y, z, player);
	}

	private void smelt() {
		System.out.println("smlet");

	}

	private void quick(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		System.out.println("quick");
	}

	private void luck(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		System.out.println("luck");

	}

	private void silk(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		System.out.println("silk");

	}

	private void radius(ItemStack stack, int x, int y, int z,
			EntityPlayer player, int radius) {
		World world = player.worldObj;
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		int direction = -1;
		Vector<BlockLocation> v = new Vector<BlockLocation>();
		MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity
				.rayTrace(6, 1.0F);

		if (mop == null) {
			return;
		}
		if (!ForgeHooks.isToolEffective(stack, block, meta)) {
			return;
		}
		if (stack
				.getTagCompound()
				.getTagList(InventoryNevPick.tagName,
						stack.getTagCompound().getId()).getStringTagAt(0)
				.equals("")) {
			return;
		}

		direction = mop.sideHit;
		for (int i = -radius; i <= radius; i++) {
			for (int j = -radius; j <= radius; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if (direction == 0 || direction == 1)
					v.add(new BlockLocation(i + x, y, j + z));
				if (direction == 2 || direction == 3)
					v.add(new BlockLocation(i + x, j + y, z));
				if (direction == 4 || direction == 5)
					v.add(new BlockLocation(x, i + y, j + z));
			}
		}
		for (BlockLocation b : v) {
			Block bl = world.getBlock(b.x, b.y, b.z);
			if (ForgeHooks.isToolEffective(stack, bl, meta)) {
				MyUtils.breakWithFortune(world, b.x, b.y, b.z, 0);
				stack.setItemDamage(stack.getItemDamage() + 1);
			}
		}

	}
}
