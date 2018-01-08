package com.mondiamedia.entity;

public enum Type {

	Subscription ("SUBSCRIPTION"),
	Alacarte ("ALACARTE");
	
	private final String name ;
	
	Type (String name){
		this.name = name ;
	}

	public String getServiceType() {
		return name;
		}
}
