package app.entities;

public class Product {

	private long id;
	private String name;
	private String category;
	private double price;

	public void setId(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public Product(long id, String name, String category, Double price) {
		setId(id);
		setName(name);
		setCategory(category);
		setPrice(price);
	}

}
