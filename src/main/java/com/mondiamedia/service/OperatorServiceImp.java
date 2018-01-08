package com.mondiamedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mondiamedia.entity.Operator;
import com.mondiamedia.repo.OperatorRepository;

@Service("operatorService")
@Transactional
public class OperatorServiceImp implements OperatorService {

	@Autowired
	private OperatorRepository operatorRepo;
	
	
	public Operator findOperatorById(int operatorID) {
		return operatorRepo.findOne(operatorID);
	}

	
	public List<Operator> findOperatorByName(String operatorName) {
		List<Operator> operator = operatorRepo.findOperatorByName(operatorName);
		return operator;
	}

	
	public void saveOperator(Operator operator) {
		operatorRepo.save(operator);
	}

	
	public void updateOperator(Operator operator) {
		saveOperator(operator);
	}

	
	public void deleteOperatorById(int operatorID) {
		operatorRepo.delete(operatorID);
	}

	
	public void deleteAllOperators() {
		operatorRepo.deleteAll();
	}

	
	public List<Operator> findAllOperators() {
		List<Operator> operators = new ArrayList<>();
		operatorRepo.findAll()
		.forEach(operators::add);;
		return operators;
	}

	
	public boolean isOperatorExist(Operator operator) {
		return findOperatorById(operator.getID()) != null;
	}

}
