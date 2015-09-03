package mrriegel.rwl.item;

import java.util.List;
import java.util.Random;

import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.init.ModItems;
import mrriegel.rwl.inventory.InventoryNevTool;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class NevSword extends ItemSword implements INev {

	ItemStack sword = null;

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
		this.itemIcon = p_94581_1_.registerIcon(Reference.MOD_ID + ":"
				+ "nevsword");

	}

	// @Override
	// public int getMaxItemUseDuration(ItemStack itemstack) {
	// return 1;
	// }

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
		boolean res = false;
		for (ItemStack s : player.inventory.mainInventory)
			if (s != null && s.getItem().equals(ModItems.bag)) {
				res = true;
				break;
			}

		if (player.isSneaking())
			if (!res)
				player.openGui(RWL.instance, GuiIDs.NEVTOOL, world, 0, 0, 0);
			else
				player.openGui(RWL.instance, GuiIDs.COMBOBAG, world, 0, 0, 0);
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean boo) {
		list.add(NBTHelper.getInt(stack, "kill")
				+ "/"
				+ String.valueOf((NBTHelper.getBoolean(stack, "tier1") || NBTHelper
						.getBoolean(stack, "tier2")) ? 1000 : 100));
		if (stack.getTagCompound() == null)
			return;

		if (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.toString().equals("{}")) {
			return;
		}

		switch (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage")) {
		case 0:
			list.add("AoE I");
			break;
		case 1:
			list.add("AoE II");
			break;
		case 2:
			list.add("AoE III");
			break;
		case 4:
			list.add("Loot");
			break;
		case 6:
			list.add("Fire");
			break;
		case 7:
			list.add("Poison");
			break;
		case 9:
			list.add("Keen I");
			break;
		case 10:
			list.add("Keen II");
			break;
		case 11:
			list.add("Slowness");
			break;
		case 12:
			list.add("Wither");
			break;
		case 13:
			list.add("XP");
			break;
		}

	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase victim,
			EntityLivingBase playe) {
		if (stack.getTagCompound() == null)
			return false;
		EntityPlayer player = (EntityPlayer) playe;
		if (player.worldObj.isRemote) {
			return false;
		}
		if (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.toString().equals("{}")) {
			return false;
		}
		Random rand = player.worldObj.rand;
		switch (stack.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage")) {
		case 0:
			aoe(player, victim, 1.1f, rand);
			return true;
		case 1:
			aoe(player, victim, 2.1f, rand);
			return true;
		case 2:
			aoe(player, victim, 3.1f, rand);
			return true;
		case 6:
			victim.setFire(7);
			break;
		case 7:
			if (rand.nextInt(3) == 3)
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

	private void aoe(EntityPlayer player, EntityLivingBase victim, float f,
			Random rand) {
		List<EntityLivingBase> lis = player.worldObj.getEntitiesWithinAABB(
				EntityLivingBase.class, AxisAlignedBB.getBoundingBox(
						victim.posX - f, victim.posY - f, victim.posZ - f,
						victim.posX + f, victim.posY + f, victim.posZ + f));

		for (EntityLivingBase e : lis)
			if (!(e instanceof EntityPlayer)) {
				double d = ((AttributeModifier) getItemAttributeModifiers()
						.get(SharedMonsterAttributes.attackDamage
								.getAttributeUnlocalizedName()).toArray()[0])
						.getAmount();
				e.attackEntityFrom(new EntityDamageSource("player", player),
						(float) (d / 4));
				if (rand.nextInt(4) == 0)
					if (!player.capabilities.isCreativeMode)
						player.getHeldItem().setItemDamage(
								player.getHeldItem().getItemDamage() + 1);

			}
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		if (sword == null)
			return super.getItemAttributeModifiers();
		int plus = 0;
		plus = NBTHelper.getBoolean(sword, "tier1") ? 1 : 0;
		plus = NBTHelper.getBoolean(sword, "tier2") ? 2 : plus;
		Multimap multimap = HashMultimap.create();

		if (sword.getTagCompound() == null) {
			multimap.put(SharedMonsterAttributes.attackDamage
					.getAttributeUnlocalizedName(), new AttributeModifier(
					field_111210_e, "Weapon modifier", (double) 4.0F + plus
							+ MATERIAL.getDamageVsEntity(), 0));
			return multimap;
		}
		switch (sword.getTagCompound().getCompoundTag(InventoryNevTool.tagName)
				.getShort("Damage")) {
		case 9:
			multimap.put(SharedMonsterAttributes.attackDamage
					.getAttributeUnlocalizedName(), new AttributeModifier(
					field_111210_e, "Weapon modifier", (double) 7.0F + plus
							+ MATERIAL.getDamageVsEntity(), 0));
			return multimap;
		case 10:
			multimap.put(SharedMonsterAttributes.attackDamage
					.getAttributeUnlocalizedName(), new AttributeModifier(
					field_111210_e, "Weapon modifier", (double) 13.0F + plus
							+ MATERIAL.getDamageVsEntity(), 0));
			return multimap;
		default:
			multimap.put(SharedMonsterAttributes.attackDamage
					.getAttributeUnlocalizedName(), new AttributeModifier(
					field_111210_e, "Weapon modifier", (double) 4.0F + plus
							+ MATERIAL.getDamageVsEntity(), 0));
			return multimap;
		}
	}
}
