package com.scrapper.bean;

import java.util.Date;
import java.util.Map;

public class EmailValidationDetails {
	private String email;
	private Map<String,Integer> inicator;
	private String timestamp;
	private String validationStatus;
	private String report;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Map<String, Integer> getInicator() {
		return inicator;
	}
	public void setInicator(Map<String, Integer> inicator) {
		this.inicator = inicator;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((inicator == null) ? 0 : inicator.hashCode());
		result = prime * result + ((report == null) ? 0 : report.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((validationStatus == null) ? 0 : validationStatus.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailValidationDetails other = (EmailValidationDetails) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (inicator == null) {
			if (other.inicator != null)
				return false;
		} else if (!inicator.equals(other.inicator))
			return false;
		if (report == null) {
			if (other.report != null)
				return false;
		} else if (!report.equals(other.report))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (validationStatus == null) {
			if (other.validationStatus != null)
				return false;
		} else if (!validationStatus.equals(other.validationStatus))
			return false;
		return true;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getValidationStatus() {
		return validationStatus;
	}
	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}

}
