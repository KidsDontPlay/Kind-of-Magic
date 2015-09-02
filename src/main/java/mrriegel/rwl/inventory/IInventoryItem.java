package mrriegel.rwl.inventory;

import mrriegel.rwl.gui.IContainer;

public interface IInventoryItem {
	public void updateItems();
	public IContainer getContainer();
	public void setContainer(IContainer con);
}
