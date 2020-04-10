package model;

public class Goods {

	private int goodsID;
	private String goodsName;
	private int goodsQuantity;
	
	public Goods(int gID, String gName, int gQuantity) {
		this.goodsID = gID;
		this.goodsName = gName;
		this.goodsQuantity = gQuantity;
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

	public int getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(int goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	
	
}
