package mrriegel.rwl.init;

import mrriegel.rwl.RWL;
import mrriegel.rwl.entity.Ice;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void init() {
		EntityRegistry.registerModEntity(Ice.class, "ice", 0, RWL.instance,
				128, 5, true);
	}
}
