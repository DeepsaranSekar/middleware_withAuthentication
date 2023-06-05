package com.nuance.middleware.service;

import java.util.Collection;

import com.nuance.middleware.model.Product;

public interface IProductService {
	
	   public abstract Product updateProduct(String id, Product product);
	   public abstract void deleteProduct(String id);
	   public abstract Product createProduct(String id, Product product);
	   public abstract Collection<Product> getProducts();

}
