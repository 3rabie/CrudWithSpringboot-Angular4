package com.mondiamedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mondiamedia.entity.Product;
import com.mondiamedia.repo.ProductRepository;

@Service("productService")
@Transactional
public  class ProductServiceImp implements ProductService{

	@Autowired
	private ProductRepository productRepo;

	public Product findProductById(int productID) {
		return productRepo.findOne(productID);
	}

	public List<Product> findProductByName(String productName) {
		List<Product> product = productRepo.findProductByName(productName);
		return product;
	}

	public void saveProduct(Product product) {
		productRepo.save(product);
	}

	public void updateProduct(Product product){
		saveProduct(product);
	}

	public void deleteProductById(int productID){
		productRepo.delete(productID);
	}

	public void deleteAllProducts(){
		productRepo.deleteAll();
	}

	public List<Product> findAllProducts(){
		List<Product> products = new ArrayList<>();
		productRepo.findAll()
		.forEach(products::add);
		return products;
	}

	public boolean isProductExist(Product product) {
		return findProductById(product.getID()) != null;
	}
	
	
}
