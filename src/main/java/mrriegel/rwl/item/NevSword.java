package mrriegel.rwl.item;

import java.util.Collection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import mrriegel.rwl.RWL;
import mrriegel.rwl.creative.CreativeTab;
import mrriegel.rwl.gui.GuiIDs;
import mrriegel.rwl.gui.InventoryNevTool;
import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;

public class NevSword extends ItemSword {
	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 0, 2222, 10.0F, 6.0F, 30);

	ItemStack sword = null;

	public NevSword() {
		super(MATERIAL);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.tab1);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "nevsword");
		this.setTextureName(Reference.MOD_ID + ":" + "nevsword");
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
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_,
			Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		sword = p_77663_1_;
		super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_,
				p_77663_5_);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		player.openGui(RWL.instance, GuiIDs.NEVTOOL, world, 0, 0, 0);
		NBTHelper.setBoolean(stack, "opened", true);
		return stack;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase victim,
			EntityLivingBase player) {
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
		try {
			switch (sword
					.getTagCompound()
					.getTagList(InventoryNevTool.tagName,
							sword.getTagCompound().getId()).getCompoundTagAt(0)
					.getShort("Damage")) {
			case 9:
				multimap.put(SharedMonsterAttributes.attackDamage
						.getAttributeUnlocalizedName(), new AttributeModifier(
						field_111210_e, "Weapon modifier", (double) 8.0F
								+ MATERIAL.getDamageVsEntity(), 0));
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
		} catch (NullPointerException e) {
		}
		return super.getItemAttributeModifiers();
	}
}
