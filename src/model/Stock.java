package model;

public class Stock {

	private int goodsID;
	private String goodsName;
	private int goodsQuantityInStock;
	
	public Stock(int gID, String gName, int gQuantityInStock) {
		this.goodsID = gID;
		this.goodsName = gName;
		this.goodsQuantityInStock = gQuantityInStock;
	}

	public int getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsQuantityInStock() {
		return goodsQuantityInStock;
	}

	public void setGoodsQuantityInStock(int goodsQuantityInStock) {
		this.goodsQuantityInStock = goodsQuantityInStock;
	}
	
	
}
