package com.mondiamedia.service;

import java.util.List;


import com.mondiamedia.entity.Product;

public interface ProductService {

	Product findProductById(int productID);
	
	List<Product> findProductByName(String productName);
	
	void saveProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProductById(int productID);
	
	void deleteAllProducts();

	List<Product> findAllProducts();
	
	boolean isProductExist(Product product);
}
