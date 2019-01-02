package com.scrapper.bean;


import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;



@Entity
@Table(name = "Company", schema = "scrapper")
public class Company {
	
	@Id
    @GeneratedValue
    @Column(name="companyId")
	private Long companyId;
	
	@Column(name="companyName")
	private String name;
	private String industry;
	private String type;
	private String size;
	private String website;
	
	@ElementCollection
	@CollectionTable(name="specialities", joinColumns=@JoinColumn(name="companyId"))
	@Column(name="specialities")
	private List<String> specialities;
	
	@Embedded
	private Address address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public List<String> getSpecialities() {
		return specialities;
	}
	public void setSpecialities(List<String> specialities) {
		this.specialities = specialities;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}
