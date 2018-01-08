package com.mondiamedia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mondiamedia.entity.Product;
import com.mondiamedia.service.ProductServiceImp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "api/product")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
@Api(value = "ProductControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductServiceImp productService;

	@ApiOperation("List all the exist Products.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Product.class, responseContainer = "List") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAll() {
		return productService.findAllProducts();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Add new product.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED", response = String.class) })
	public ResponseEntity<?> newProduct(@RequestBody Product product) {

		logger.info("Creating Product : {}", product);

		if (productService.isProductExist(product)) {
			logger.error("Unable to create. A Product with name {} already exist", product.getName());
			return new ResponseEntity("Unable to create. A Product with name " + product.getName() + " already exist.",
					HttpStatus.CONFLICT);
		}
		System.out.println(product.getMax_Price());
		productService.saveProduct(product);
		return new ResponseEntity<String>("Product Added Successfully!", HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Find an product with id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "OK", response = Product.class) })
	public Product findByProductID(@PathVariable int id) {
		return productService.findProductById(id);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Delete an product with id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Void.class) })
	public void deleteProduct(@PathVariable("id") int id) {
		productService.deleteProductById(id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value = "/{id}")
	@ApiOperation("Update an  product with id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Product.class, responseContainer = "ResponseEntity") })
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {

		logger.info("Updating Product with id {}", id);

		Product currentProduct = productService.findProductById(id);

		if (currentProduct == null) {
			logger.error("Unable to update. Product with id {} not found.", id);
			return new ResponseEntity("Unable to upate. Product with id " + id + " not found.", HttpStatus.NOT_FOUND);
		}
		currentProduct.setID(id);
		currentProduct.setName(product.getName());
		currentProduct.setDescription(product.getDescription());
		currentProduct.setMax_Price(product.getMax_Price());
		currentProduct.setMin_Price(product.getMin_Price());

		productService.updateProduct(currentProduct);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
