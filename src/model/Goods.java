package model;

public class Goods {

	private int itemID;
	private String itemName;
	private int itemQuantity;
	
	public Goods(int iID, String iName, int iQuantity) {
		this.itemID = iID;
		this.itemName = iName;
		this.itemQuantity = iQuantity;
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

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
	
}
