package datamodel;

public class OrderItem {
	protected String description;
	protected final Article article;
	protected int unitsOrdered;
	
	protected OrderItem(String descr, Article article, int unitsOrdered) {
		super();
		this.description = descr;
		this.article = article;
		this.unitsOrdered = unitsOrdered;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Article getArticle() {
		return article;
	}

	public int getUnitsOrdered() {
		return unitsOrdered;
	}

	public void setUnitsOrdered(int unitsOrdered) {
		this.unitsOrdered = unitsOrdered;
	}

	
}
