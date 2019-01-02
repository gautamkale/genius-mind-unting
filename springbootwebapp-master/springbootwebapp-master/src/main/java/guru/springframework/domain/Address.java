package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
//@Table(name = "Address", schema = "scrapper")
public class Address {
	
/*	@Id
    @GeneratedValue
    @Column(name="Id")
	private Long Id;*/
	
	private String street;
	private String zip;
	private String state;
	private String city;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
