package datamodel;

public class Article {
	String id;
	String description;
	long unitPrice;
	int unitsInStore;



	protected Article(String id, String descr, long price, int units) {
		super();
		this.id = id;
		this.description = descr;
		this.unitPrice = price;
		this.unitsInStore = units;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		if (description == null) {
			description = "";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUnitPrice() {
		if(unitPrice < 0) {
			unitPrice = 0;
		}
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		if(unitPrice == Long.MAX_VALUE || unitPrice == Long.MIN_VALUE || unitPrice < 0) {
			unitPrice = 0;
		}
		this.unitPrice = unitPrice;
	}

	public int getUnitsInStore() {
		if(unitsInStore < 0) {
			unitsInStore = 0;
		}
		return unitsInStore;
	}

	public void setUnitsInStore(int unitsInStore) {
		if(unitsInStore == Integer.MAX_VALUE || unitsInStore == Integer.MIN_VALUE || unitsInStore < 0) {
			unitsInStore = 0;
		}
		this.unitsInStore = unitsInStore;
	}

}
