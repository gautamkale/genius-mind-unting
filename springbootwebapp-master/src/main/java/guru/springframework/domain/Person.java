package guru.springframework.domain;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "person", schema = "scrapper")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	
	@Id
    @GeneratedValue
    @Column(name="personId")
	private Long personId;
	@Embedded
	private PersonInfo personal_info;
	
	   @OneToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "experinceId", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	private Experince experiences;
	
/*	@Embedded
	private Education education;*/
	public PersonInfo getPersonal_info() {
		return personal_info;
	}
	public void setPersonal_info(PersonInfo personal_info) {
		this.personal_info = personal_info;
	}
	public Experince getExperiences() {
		return experiences;
	}
	public void setExperiences(Experince experiences) {
		this.experiences = experiences;
	}
/*	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}*/
	

}

