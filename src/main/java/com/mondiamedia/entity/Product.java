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
@Table( name = "product")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id ;
	
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "description")
	private String description;
	
	
	@Column(name = "min_price", nullable = false)
	private double min_price ;
	
	
	@Column(name = "max_price", nullable = false)
	private double max_price ;

	
	@OneToMany(mappedBy="productID",cascade=CascadeType.ALL)
    @OrderBy("name ASC")
	private List<Serv> services;
	
	public Product() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMin_Price() {
		return min_price;
	}

	public void setMin_Price(double min_price) {
		this.min_price = min_price;
	}

	public double getMax_Price() {
		return max_price;
	}

	public void setMax_Price(double max_price) {
		this.max_price = max_price;
	}

	@Override
	public String toString() {
		return "Product [ID=" + id + ", Name=" + name + ", Description=" + description + ", Min_Price=" + min_price
				+ ", Max_Price=" + max_price + "]";
	}
	
	
	public List<Serv> getServices() {
		return services;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void addService(Serv service) {
		getServices().add(service);
		if(service.getProduct() != null) {
			service.getProduct().getServices().remove(service.getName());
		}
		service.setProduct(this);
	}
	
	
	
	
}
