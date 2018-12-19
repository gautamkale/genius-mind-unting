package guru.springframework.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
/*@Entity
@Table(name = "PersonInfo", schema = "scrapper")*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonInfo {
	
/*	@Id
    @GeneratedValue
    @Column(name="personInfoId")
	private Long personInfoId;*/
	
	@Column(name="personName")
	private String name;
	private String headline;
	private String company;
	private String location;
	private boolean present;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	
}
