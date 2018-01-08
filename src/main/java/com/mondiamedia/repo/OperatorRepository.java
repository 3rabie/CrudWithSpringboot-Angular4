package com.mondiamedia.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mondiamedia.entity.Operator;

@Repository
public interface OperatorRepository extends CrudRepository<Operator, Integer> {
	
	List<Operator> findOperatorByName(String name);
	
}
