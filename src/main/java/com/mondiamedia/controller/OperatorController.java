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

import com.mondiamedia.entity.Operator;
import com.mondiamedia.service.OperatorServiceImp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "api/operator")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
@Api(value = "OperatorControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperatorController {

	public static final Logger logger = LoggerFactory.getLogger(OperatorController.class);

	@Autowired
	OperatorServiceImp operatorService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("List all the exist Operators.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Operator.class, responseContainer = "List") })
	public List<Operator> getAll() {
		return operatorService.findAllOperators();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Add new Operator.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED", response = String.class) })
	public ResponseEntity<?> newOperator(@RequestBody Operator operator) {

		logger.info("Creating Operator : {}", operator);

		if (operatorService.isOperatorExist(operator)) {
			logger.error("Unable to create. A Operator with name {} already exist", operator.getName());
			return new ResponseEntity(
					"Unable to create. A Operator with name " + operator.getName() + " already exist.",
					HttpStatus.CONFLICT);
		}

		operatorService.saveOperator(operator);
		return new ResponseEntity<String>("Operator Added Successfully!", HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Find an operator with his id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Operator.class) })
	public Operator findByOperatorName(@PathVariable int id) {
		return operatorService.findOperatorById(id);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Delete an operator with his id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Void.class) })
	public void deleteOperator(@PathVariable("id") int id) {
		operatorService.deleteOperatorById(id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value = "/{id}")
	@ApiOperation("Update an  operators with id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Operator.class, responseContainer = "ResponseEntity") })
	public ResponseEntity<?> updateOperator(@PathVariable("id") int id, @RequestBody Operator operator) {

		logger.info("Updating Operator with id {}", id);

		Operator currentOperator = operatorService.findOperatorById(id);

		if (currentOperator == null) {
			logger.error("Unable to update. Operator with id {} not found.", id);
			return new ResponseEntity("Unable to upate. Operator with id " + id + " not found.", HttpStatus.NOT_FOUND);
		}
		currentOperator.setID(id);
		currentOperator.setName(operator.getName());
		currentOperator.setCountry(operator.getCountry());

		operatorService.updateOperator(currentOperator);
		return new ResponseEntity<Operator>(operator, HttpStatus.OK);
	}

}
