package com.nuance.middleware.model;

public class Product {
	
	private String id;
	private String name;

	public Product() {
	}

	public Product(String id, String name) {
		this.id = id;
		this.name = name;
	}


	// Getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	// toString method
	@Override
	public String toString() {
		return "Product{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
	}
}
