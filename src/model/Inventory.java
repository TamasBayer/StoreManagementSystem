package model;

public class Inventory {

	private String StockInOrOut;
	private String stockName;
	private int itemID;
	private String itemName;
	private int itemQuantityInStock;
	
	public Inventory(String sName, int iID, String iName, int iQuantityInStock) {
		this.stockName = sName;
		this.itemID = iID;
		this.itemName = iName;
		this.itemQuantityInStock = iQuantityInStock;
	}
	
	public Inventory(String SInOrOut, String sName, int iID, String iName, int iQuantityInStock) {
		this.StockInOrOut = SInOrOut;
		this.stockName = sName;
		this.itemID = iID;
		this.itemName = iName;
		this.itemQuantityInStock = iQuantityInStock;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
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

	public int getItemQuantityInStock() {
		return itemQuantityInStock;
	}

	public void setItemQuantityInStock(int itemQuantityInStock) {
		this.itemQuantityInStock = itemQuantityInStock;
	}

	public String getStockInOrOut() {
		return StockInOrOut;
	}

	public void setStockInOrOut(String stockInOrOut) {
		StockInOrOut = stockInOrOut;
	}

	
}
