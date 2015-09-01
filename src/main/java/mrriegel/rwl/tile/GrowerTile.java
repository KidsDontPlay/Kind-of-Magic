package mrriegel.rwl.tile;

import mrriegel.rwl.RWL;
import mrriegel.rwl.handler.ConfigurationHandler;
import mrriegel.rwl.packet.ParticlePacket;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.IPlantable;

public class GrowerTile extends TileEntity {

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;
		if (advanced()) {
			int rad = ConfigurationHandler.floralRadiusAdvanced;
			for (int x = xCoord - rad; x < xCoord + rad; x++) {
				for (int y = yCoord - 1; y < yCoord + 4; y++) {
					for (int z = zCoord - rad; z < zCoord + rad; z++) {
						if (x == xCoord && y == yCoord && z == zCoord)
							continue;
						Block block = worldObj.getBlock(x, y, z);
						if (block instanceof IPlantable
								|| block instanceof IGrowable) {
							int meta1 = worldObj.getBlockMetadata(x, y, z);
							if (worldObj.rand
									.nextInt(ConfigurationHandler.floralSpeedAdvanced) == 0) {
								block.updateTick(worldObj, x, y, z,
										worldObj.rand);
							}
							if (worldObj.getBlockMetadata(x, y, z) != meta1
									&& block instanceof IGrowable)
								RWL.net.sendToAll(new ParticlePacket(x, y, z,
										worldObj.provider.dimensionId));
						}
					}
				}
			}
		} else {
			int rad = ConfigurationHandler.floralRadius;
			for (int x = xCoord - rad; x < xCoord + rad; x++) {
				for (int y = yCoord - 1; y < yCoord + 4; y++) {
					for (int z = zCoord - rad; z < zCoord + rad; z++) {
						if (x == xCoord && y == yCoord && z == zCoord)
							continue;
						Block block = worldObj.getBlock(x, y, z);
						if (block instanceof IPlantable
								|| block instanceof IGrowable) {
							if (worldObj.rand
									.nextInt(ConfigurationHandler.floralSpeed) == 0) {
								block.updateTick(worldObj, x, y, z,
										worldObj.rand);
							}
						}
					}
				}
			}
		}
	}

	private boolean advanced() {
		boolean hot = worldObj.getBlock(xCoord, yCoord - 1, zCoord).equals(
				Blocks.fire)
				|| worldObj.getBlock(xCoord, yCoord - 1, zCoord).equals(
						Blocks.lava);
		int count = 0;
		if (worldObj.getBlock(xCoord, yCoord + 1, zCoord + 1).getMaterial()
				.equals(Material.water)
				&& worldObj.getBlockMetadata(xCoord, yCoord + 1, zCoord + 1) == 0)
			count++;
		if (worldObj.getBlock(xCoord, yCoord + 1, zCoord - 1).getMaterial()
				.equals(Material.water)
				&& worldObj.getBlockMetadata(xCoord, yCoord + 1, zCoord - 1) == 0)
			count++;
		if (worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord).getMaterial()
				.equals(Material.water)
				&& worldObj.getBlockMetadata(xCoord + 1, yCoord + 1, zCoord) == 0)
			count++;
		if (worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord).getMaterial()
				.equals(Material.water)
				&& worldObj.getBlockMetadata(xCoord - 1, yCoord + 1, zCoord) == 0)
			count++;

		boolean water = worldObj.getBlock(xCoord, yCoord + 1, zCoord).equals(
				Blocks.water)
				&& count > 1;
		return water && hot;
	}

}
