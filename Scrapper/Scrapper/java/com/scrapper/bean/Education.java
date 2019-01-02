package com.scrapper.bean;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Education", schema = "scrapper")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Education {
	
	
	private Long educationId;
	 
	@Column(name="schooleName")
	private String name;
	private String degree;
	private String grades;
	private String field_of_study;
	public Education(String name, String degree, String grades, String field_of_study, String date_range) {
		super();
		this.name = name;
		this.degree = degree;
		this.grades = grades;
		this.field_of_study = field_of_study;
		this.date_range = date_range;
	}
	public Education() {
		// TODO Auto-generated constructor stub
	}
	private String date_range;
	 

	private Experince experince;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getGrades() {
		return grades;
	}
	public void setGrades(String grades) {
		this.grades = grades;
	}
	public String getField_of_study() {
		return field_of_study;
	}
	public void setField_of_study(String field_of_study) {
		this.field_of_study = field_of_study;
	}
	public String getDate_range() {
		return date_range;
	}
	public void setDate_range(String date_range) {
		this.date_range = date_range;
	}
	
	@Id
    @GeneratedValue
    @Column(name="educationId")
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	
	@ManyToOne
    @JoinColumn(name = "experinceId")
	public Experince getExperince() {
		return experince;
	}
	public void setExperince(Experince experince) {
		this.experince = experince;
	}
	
}

