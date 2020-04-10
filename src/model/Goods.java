package model;

public class Goods {

	private int itemID;
	private String itemName;
	
	public Goods(int iID, String iName) {
		this.itemID = iID;
		this.itemName = iName;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
