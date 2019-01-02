package guru.springframework.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Job", schema = "scrapper")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
	 
	@Id
    @GeneratedValue
    @Column(name="jobId")
	private Long jobId;
	
	private String title;
	private String company;
	private String dateRange;
	private String location;
	
	@ManyToOne
    @JoinColumn(name = "experinceId")
	private Experince experince;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Experince getExperince() {
		return experince;
	}
	public void setExperince(Experince experince) {
		this.experince = experince;
	}
}
