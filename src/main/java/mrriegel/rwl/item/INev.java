package mrriegel.rwl.item;

import mrriegel.rwl.handler.ConfigurationHandler;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public interface INev {
	public static ToolMaterial MATERIAL = EnumHelper.addToolMaterial(
			"MATERIAL", 3, 2222, 10.0F, 5.0F, 1);
}
