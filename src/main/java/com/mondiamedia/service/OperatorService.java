package com.mondiamedia.service;

import java.util.List;

import com.mondiamedia.entity.Operator;

public interface OperatorService {

Operator findOperatorById(int operatorID);
	
	List<Operator> findOperatorByName(String operatorName);
	
	void saveOperator(Operator operator);
	
	void updateOperator(Operator operator);
	
	void deleteOperatorById(int operatorID);
	
	void deleteAllOperators();

	List<Operator> findAllOperators();
	
	boolean isOperatorExist(Operator operator);
	
}

