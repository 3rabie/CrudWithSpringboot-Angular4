package com.mondiamedia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mondiamedia.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

	List<Product> findProductByName(String name);

}

