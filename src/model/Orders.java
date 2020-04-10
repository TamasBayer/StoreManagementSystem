package model;

public class Orders {

	private int orderID;
	private String orderedFrom;
	private String orderDatum;
	
	public Orders(int oID, String oFrom, String oDatum) {
		this.orderID = oID;
		this.orderedFrom = oFrom;
		this.orderDatum = oDatum;
				
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getOrderedFrom() {
		return orderedFrom;
	}

	public void setOrderedFrom(String orderedFrom) {
		this.orderedFrom = orderedFrom;
	}

	public String getOrderDatum() {
		return orderDatum;
	}

	public void setOrderDatum(String orderDatum) {
		this.orderDatum = orderDatum;
	}
	
	
}
