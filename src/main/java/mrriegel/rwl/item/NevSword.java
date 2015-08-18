package mrriegel.rwl.item;

import java.util.List;
import java.util.Random;

import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NevSword extends ItemSword implements INev {
	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 3, 2222, 10.0F, 5.0F, 1);

	ItemStack sword = null;
	IIcon icon_f = null;

	public NevSword() {
		super(MATERIAL);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevsword");
	}

	@Override
	public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
		if (p_82789_2_.getItem().equals(ModItems.nev)) {
			return true;
		}
		return false;
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
		super.onUpdate(stack, world, p_77663_3_, p_77663_4_, p_77663_5_);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if (player.isSneaking())
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
		case 9:
			list.add("sharp 1");
			break;
		case 10:
			list.add("sharp 2");
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
		Random rand = new Random();
		switch (stack
				.getTagCompound()
				.getTagList(InventoryNevTool.tagName,
						stack.getTagCompound().getId()).getCompoundTagAt(0)
				.getShort("Damage")) {
		case 6:
			victim.setFire(7);
			break;
		case 7:
			if (rand.nextInt(4) == 3)
				victim.addPotionEffect(new PotionEffect(Potion.poison.id, 140,
						3));
			break;
		case 11:
			victim.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,
					140, 2));
			break;
		case 12:
			if (rand.nextInt(5) == 3)
				victim.addPotionEffect(new PotionEffect(Potion.wither.id, 140,
						2));
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
