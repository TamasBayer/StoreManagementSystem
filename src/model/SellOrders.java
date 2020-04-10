package model;

public class SellOrders {

	private int sellOrderID;
	private String soldFor;
	private String sellOrderDatum;
	
	public SellOrders(int sOID, String sFor, String sODatum) {
		this.sellOrderID = sOID;
		this.soldFor = sFor;
		this.sellOrderDatum = sODatum;
				
	}

	public int getSellOrderID() {
		return sellOrderID;
	}

	public void setSellOrderID(int sellOrderID) {
		this.sellOrderID = sellOrderID;
	}

	public String getSoldFor() {
		return soldFor;
	}

	public void setSoldFor(String soldFor) {
		this.soldFor = soldFor;
	}

	public String getSellOrderDatum() {
		return sellOrderDatum;
	}

	public void setSellOrderDatum(String sellOrderDatum) {
		this.sellOrderDatum = sellOrderDatum;
	}
	
}
