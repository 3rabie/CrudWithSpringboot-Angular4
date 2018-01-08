package com.mondiamedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mondiamedia.entity.Serv;
import com.mondiamedia.repo.ServRepository;

@Service("servService")
@Transactional
public class ServServiceImp implements ServService {

	@Autowired
	private ServRepository ServiceRepo;

	public Serv findServiceById(int serviceID) {
		return ServiceRepo.findOne(serviceID);
	}

	
	public List<Serv> findServiceByName(String serviceName) {
		List<Serv> service = ServiceRepo.findServiceByName(serviceName);
		return service;
	}

	
	public void saveService(Serv service) {
		ServiceRepo.save(service);	
	}

	
	public void updateService(Serv service) {
		saveService(service);
	}

	
	public void deleteServiceById(int serviceID) {
		ServiceRepo.delete(serviceID);
	}

	
	public void deleteAllServices() {
		ServiceRepo.deleteAll();
	}

	
	public List<Serv> findAllServices() {
		List<Serv> services = new ArrayList<>();
		ServiceRepo.findAll().forEach(services::add);
		return services;
	}

	
	public boolean isServiceExist(Serv service) {
		return findServiceById(service.getID()) != null;
	}

}
