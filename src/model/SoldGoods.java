package model;

public class SoldGoods {

	private int sellOrderGoodsID;
	private int soldItemID;
	private String soldItemName;
	private int soldItemQuantity;
	private int pickedQuantity;
	
	public SoldGoods(int sOGoodsID, int sItemID, String sItemName, int sItemQuantity, int pQuantity) {
		this.sellOrderGoodsID = sOGoodsID;
		this.soldItemID = sItemID;
		this.soldItemName = sItemName;
		this.soldItemQuantity = sItemQuantity;
		this.pickedQuantity = pQuantity;
	}

	public int getSellOrderGoodsID() {
		return sellOrderGoodsID;
	}

	public void setSellOrderGoodsID(int sellOrderGoodsID) {
		this.sellOrderGoodsID = sellOrderGoodsID;
	}

	public int getSoldItemID() {
		return soldItemID;
	}

	public void setSoldItemID(int soldItemID) {
		this.soldItemID = soldItemID;
	}

	public String getSoldItemName() {
		return soldItemName;
	}

	public void setSoldItemName(String soldItemName) {
		this.soldItemName = soldItemName;
	}

	public int getSoldItemQuantity() {
		return soldItemQuantity;
	}

	public void setSoldItemQuantity(int soldItemQuantity) {
		this.soldItemQuantity = soldItemQuantity;
	}

	public int getPickedQuantity() {
		return pickedQuantity;
	}

	public void setPickedQuantity(int pickedQuantity) {
		this.pickedQuantity = pickedQuantity;
	}
	
	
}
