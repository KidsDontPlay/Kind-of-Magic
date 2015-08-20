package mrriegel.rwl.item;

import java.util.HashMap;
import java.util.List;

import mrriegel.rwl.reference.Reference;
import mrriegel.rwl.utility.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemDecor extends ItemTalisman {
	public final HashMap<Integer, String> map;

	public ItemDecor() {
		super();
		this.setUnlocalizedName(Reference.MOD_ID + ":" + "decor");
		this.setTextureName(Reference.MOD_ID + ":" + "decor");
		map = new HashMap<Integer, String>();
		initMap(map);
	}

	private void initMap(HashMap<Integer, String> map2) {
		map2.put(0, "happyVillager");
		map2.put(1, "cloud");
		map2.put(2, "dripWater");
		map2.put(3, "dripLava");
		map2.put(4, "flame");
		map2.put(5, "heart");
		map2.put(6, "instantSpell");
		map2.put(7, "magicCrit");
		map2.put(8, "smoke");
		map2.put(9, "spell");
		map2.put(10, "witchMagic");

	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_,
			EntityPlayer p_77659_3_) {
		if (!(NBTHelper.getInt(p_77659_1_, "enum") > map.size() - 2))
			NBTHelper.setInteger(p_77659_1_, "enum",
					NBTHelper.getInt(p_77659_1_, "enum") + 1);
		else
			NBTHelper.setInteger(p_77659_1_, "enum", 0);
		if (!p_77659_2_.isRemote)
			p_77659_3_.addChatMessage(new ChatComponentText("Mode: "
					+ map.get(NBTHelper.getInt(p_77659_1_, "enum"))));
		return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
	}

	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_,
			List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(map.get(NBTHelper.getInt(p_77624_1_, "enum")));
	}

	@Override
	public void perform(ItemStack stack, EntityPlayer player) {
		if (player.motionX != 0.0D || player.motionZ != 0.0D) {
			if (!player.capabilities.isFlying)
				for (int i = 0; i < 10; i++)
					player.worldObj.spawnParticle(
							map.get(NBTHelper.getInt(stack, "enum")),
							player.posX, player.posY - 1.3, player.posZ, 0, 0,
							0);
		}
	}
}
