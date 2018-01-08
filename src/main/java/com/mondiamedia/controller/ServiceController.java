package com.mondiamedia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mondiamedia.entity.Serv;
import com.mondiamedia.service.ServServiceImp;

@RestController
@RequestMapping(value= "api/service")
public class ServiceController {

	public static final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@Autowired
	ServServiceImp servService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Serv> getAll() {
		return servService.findAllServices();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value="/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newService(@RequestBody Serv service) {
		
		logger.info("Creating Service : {}", service);
		
		if(servService.isServiceExist(service)) {
			logger.error("Unable to create. A Service with name {} already exist", service.getName());
			return new ResponseEntity("Unable to create. A Service with name " + 
			service.getName() + " already exist.",HttpStatus.CONFLICT);
		}
		
		servService.saveService(service);
		return new ResponseEntity<String>("Service Added Successfully!", HttpStatus.CREATED);
	}

	@GetMapping(value="/{name}",  produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Serv> findByServiceName(@PathVariable String serviceName) {
		return servService.findServiceByName(serviceName);
	}
	
	@DeleteMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteService(@PathVariable("id") int serviceID){
		servService.deleteServiceById(serviceID);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateService(@PathVariable("id") int id,@RequestBody Serv service) {
		
		logger.info("Updating Service with id {}", id);
		
		Serv currentService = servService.findServiceById(id);
		
		if(currentService == null) {
			logger.error("Unable to update. Service with id {} not found.", id);
			return new ResponseEntity("Unable to upate. Service with id " + id + " not found.",
					HttpStatus.NOT_FOUND);
		}
		
		currentService.setName(service.getName());
		currentService.setOperator(service.getOperator());
		currentService.setProduct(service.getProduct());
		currentService.setType(service.getType());
		currentService.setOperatorPackage_ID(service.getOperatorPackage_ID());
		currentService.setOperatorService_ID(service.getOperatorService_ID());
		
		
		servService.updateService(currentService);
		return new ResponseEntity<Serv>(service,HttpStatus.OK);
	}
}
