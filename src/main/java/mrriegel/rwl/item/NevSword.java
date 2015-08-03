package mrriegel.rwl.item;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.ContainerNevTool;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.gui.InventoryNevTool;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;

public class NevSword extends ItemSword {
	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 0, 2222, 10.0F, 6.0F, 30);

	ItemStack sword = null;
	IIcon icon_f = null;
	EntityPlayer player1 = null;
	int damage = -1;

	public NevSword() {
		super(MATERIAL);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevsword");
	}

	@Override
	public void registerIcons(IIconRegister p_94581_1_) {

		this.icon_f = p_94581_1_.registerIcon(Reference.MOD_ID + ":"
				+ "nevsword_fire");

		this.itemIcon = p_94581_1_.registerIcon(Reference.MOD_ID + ":"
				+ "nevsword");

	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int p_77617_1_) {
		if (sword == null || sword.getTagCompound() == null) {
			return itemIcon;
		}
		if (sword
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						sword.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage") == 6) {
			return icon_f;
		} else {
			return itemIcon;
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity p_77663_3_,
			int p_77663_4_, boolean p_77663_5_) {
		sword = stack;
		if (!(p_77663_3_ instanceof EntityPlayer)) {
			return;
		}
		player1 = (EntityPlayer) p_77663_3_;
		if (stack.getTagCompound() == null) {
			return;
		}
		int tar = stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage");
		if (tar != damage)
			damage = tar;
		super.onUpdate(stack, world, p_77663_3_, p_77663_4_, p_77663_5_);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		player.openGui(RWL.instance, GuiIDs.NEVTOOL, world, 0, 0, 0);
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean boo) {
		if (stack.getTagCompound() == null)
			return;
		if (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getStringTagAt(0)
				.equals("")) {
			return;
		}
		switch (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage")) {
		case 4:
			list.add("loot");
			break;
		case 6:
			list.add("fire");
			break;
		case 7:
			list.add("poison");
			break;
		case 11:
			list.add("slow");
			break;
		case 12:
			list.add("wither");
			break;
		case 13:
			list.add("xp");
			break;
		}

	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase victim,
			EntityLivingBase player) {
		if (stack.getTagCompound() == null)
			return false;
		if (player.worldObj.isRemote) {
			return false;
		}
		switch (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage")) {
		case 6:
			victim.setFire(7);
			break;
		case 7:
			victim.addPotionEffect(new PotionEffect(Potion.poison.id, 140, 3));
			break;
		case 11:
			victim.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,
					140, 2));
			break;
		case 12:
			victim.addPotionEffect(new PotionEffect(Potion.wither.id, 140, 2));
			break;
		default:
			break;

		}
		return super.hitEntity(stack, player, victim);
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		if (sword == null || sword.getTagCompound() == null) {
			return super.getItemAttributeModifiers();
		}
		switch (sword
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						sword.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage")) {
		case 9:
			multimap.put(SharedMonsterAttributes.attackDamage
					.getAttributeUnlocalizedName(),
					new AttributeModifier(field_111210_e, "Weapon modifier",
							(double) 8.0F + MATERIAL.getDamageVsEntity(), 0));
			return multimap;
		case 10:
			multimap.put(SharedMonsterAttributes.attackDamage
					.getAttributeUnlocalizedName(), new AttributeModifier(
					field_111210_e, "Weapon modifier", (double) 12.0F
							+ MATERIAL.getDamageVsEntity(), 0));
			return multimap;
		default:
			return super.getItemAttributeModifiers();
		}
	}
}
