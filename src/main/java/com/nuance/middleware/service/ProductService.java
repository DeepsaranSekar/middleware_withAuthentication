package com.nuance.middleware.service;

import com.nuance.middleware.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
	private static final Logger logger = LogManager.getLogger(ProductService.class);

	private static Map<String, Product> productRepo = new HashMap<>();
	
	static {
	      Product honey = new Product();
	      honey.setId("P001");
	      honey.setName("Honey");
	      productRepo.put(honey.getId(), honey);

	      Product almond = new Product();
	      almond.setId("P002");
	      almond.setName("Almond");
	      productRepo.put(almond.getId(), almond);
	   }
	
		/*
		 public Product getProduct() { 
			 logger.info("getProduct() called"); return new
			 Product("P003", "Chocolate"); 
		 }
		 */

	public Product updateProduct(String id, Product product) {
		logger.info("updateProduct() called with ID: {}", id);
		productRepo.remove(id);
	    product.setId(id);
	    productRepo.put(id, product);
		return product;
	}

	public void deleteProduct(String id) {
		logger.info("deleteProduct() called with ID: {}", id);
		productRepo.remove(id);
	}

	public Product createProduct(String id, Product product) {
		logger.info("createProduct() called with ID: {}", id);
		productRepo.put(product.getId(), product);
		return product;
	}
	
	@Override
	   public Collection<Product> getProducts() {
	      return productRepo.values();
	   }

}

