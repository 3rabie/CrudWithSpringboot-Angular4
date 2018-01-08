package com.mondiamedia.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mondiamedia.entity.Operator;
import com.mondiamedia.entity.Product;
import com.mondiamedia.entity.Type;

@SuppressWarnings("serial")
@Entity
@Table(name = "services")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Serv implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id")
	private int id ;
	
	@Column(name="name", nullable = false)
	private String name ;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name = "productID")
	private Product productID;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name = "operatorID")
	private Operator operatorID;
	
	@Column(name = "operatorPackageID")
	private String operatorPackageID ;
	
	@Column(name = "operatorServiceID")
	private String operatorServiceID ;
	

		
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Product getProduct() {
		return productID;
	}

	public void setProduct(Product productID) {
		this.productID = productID;
	}

	public Operator getOperator() {
		return operatorID;
	}

	public void setOperator(Operator operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorService_ID() {
		return operatorServiceID;
	}

	public void setOperatorService_ID(String operatorServiceID) {
		this.operatorServiceID = operatorServiceID;
	}

	public String getOperatorPackage_ID() {
		return operatorPackageID;
	}

	public void setOperatorPackage_ID(String operatorPackageID) {
		this.operatorPackageID = operatorPackageID;
	}

	@Override
	public String toString() {
		
		StringBuffer sBuffer = new StringBuffer("Service ");
        sBuffer.append(" ID: ");
        sBuffer.append(id);
        sBuffer.append(" Name: ");
        sBuffer.append(" Type: ");
        sBuffer.append(type);
        sBuffer.append(" Operator Name: ");
        if(null != operatorID) {
            sBuffer.append(operatorID.getName());
        }
        sBuffer.append(" Product Name: ");
        if(null != productID) {
        	sBuffer.append(productID.getName());
        }
        sBuffer.append(" Operator Service ID: ");
        sBuffer.append(operatorServiceID);
        sBuffer.append(" Operator Package ID: ");
        sBuffer.append(operatorPackageID);
                
        return sBuffer.toString();
	}
	
	
}
