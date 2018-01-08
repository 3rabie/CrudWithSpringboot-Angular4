package com.mondiamedia.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mondiamedia.entity.Serv;

@SuppressWarnings("serial")
@Entity
@Table(name = "operator")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Operator implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "name",  nullable=false)
	private String name ;
	
	
	@Column(name ="country")
	private String country ;
	
	@OneToMany( mappedBy="operatorID", cascade=CascadeType.ALL)
    @OrderBy("name ASC")
    private List<Serv> services;
	
	
	public Operator() {
		services = new ArrayList<Serv>();
    }
	
	public int getID() {
		return id;
	}


	public void setID(int iD) {
		this.id = iD;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public List<Serv> getServices() {
		return services;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void addService(Serv service) {
		getServices().add(service);
		if(service.getOperator() != null) {
			service.getOperator().getServices().remove(service.getName());
		}
		service.setOperator(this);
	}
	
	
	public String toString() {
        StringBuffer sBuffer = new StringBuffer("Operator ");
        sBuffer.append(" Id: ");
        sBuffer.append(id);
        sBuffer.append(" Name: ");
        sBuffer.append(name);
        sBuffer.append(" Country: ");
        sBuffer.append(country);
        sBuffer.append(" Services Count: ");
        if(null != services) {           
            sBuffer.append(services.size());
        }
        return sBuffer.toString();
    }
	
}
