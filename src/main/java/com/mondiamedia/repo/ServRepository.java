package com.mondiamedia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mondiamedia.entity.Serv;

public interface ServRepository extends CrudRepository<Serv, Integer>{
	
	List<Serv> findServiceByName(String name);
}
