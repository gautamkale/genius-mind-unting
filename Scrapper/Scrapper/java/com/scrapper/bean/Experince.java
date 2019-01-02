package com.scrapper.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "experince", schema = "scrapper")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Experince {
	
	@Id
    @GeneratedValue
    @Column(name="experinceId")
	private Long experinceId;
	
	@OneToMany(mappedBy = "jobId", cascade = CascadeType.ALL)
	private List<Job> jobs;
	
	@OneToMany(mappedBy = "educationId", cascade = CascadeType.ALL)
	private List<Education> education;
	
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Education> getEducation() {
		return education;
	}
	public void setEducation(List<Education> education) {
		this.education = education;
	}
	
	
}
