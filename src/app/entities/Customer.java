package app.entities;

public class Customer {

	private long id;
	private String name;
	private int tier;

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

	public void setTier(int tier) {
		this.tier = tier;
	}

	public int getTier() {
		return tier;
	}

	public Customer(long id, String name, int tier) {
		setId(id);
		setName(name);
		setTier(tier);
	}
}
