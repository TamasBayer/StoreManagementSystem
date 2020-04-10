package model;

public class OrderedGoods {

	private int orderGoodsID;
	private int orderedItemID;
	private String orderedItemName;
	private int orderedItemQuantity;
	private int shippedQuantity;
	
	public OrderedGoods(int oGoodsID, int oItemID, String oItemName, int oItemQuantity, int sQuantity) {
		this.orderGoodsID = oGoodsID;
		this.orderedItemID = oItemID;
		this.orderedItemName = oItemName;
		this.orderedItemQuantity = oItemQuantity;
		this.shippedQuantity = sQuantity;
	}

	public int getOrderGoodsID() {
		return orderGoodsID;
	}

	public void setOrderGoodsID(int orderGoodsID) {
		this.orderGoodsID = orderGoodsID;
	}

	public int getOrderedItemID() {
		return orderedItemID;
	}

	public void setOrderedItemID(int orderedItemID) {
		this.orderedItemID = orderedItemID;
	}

	public String getOrderedItemName() {
		return orderedItemName;
	}

	public void setOrderedItemName(String orderedItemName) {
		this.orderedItemName = orderedItemName;
	}

	public int getOrderedItemQuantity() {
		return orderedItemQuantity;
	}

	public void setOrderedItemQuantity(int orderedItemQuantity) {
		this.orderedItemQuantity = orderedItemQuantity;
	}

	public int getShippedQuantity() {
		return shippedQuantity;
	}

	public void setShippedQuantity(int shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}

}
