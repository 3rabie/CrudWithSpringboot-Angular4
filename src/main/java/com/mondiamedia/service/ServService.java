package com.mondiamedia.service;

import java.util.List;

import com.mondiamedia.entity.Serv;

public interface ServService {

	Serv findServiceById(int serviceID);
	
	List<Serv> findServiceByName(String serviceName);
	
	void saveService(Serv service);
	
	void updateService(Serv service);
	
	void deleteServiceById(int serviceID);
	
	void deleteAllServices();

	List<Serv> findAllServices();
	
	boolean isServiceExist(Serv service);
	
}
