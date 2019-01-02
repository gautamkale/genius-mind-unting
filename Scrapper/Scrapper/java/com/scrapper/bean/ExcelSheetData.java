package com.scrapper.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ExcelData",schema="scrapper")
public class ExcelSheetData {
	
	@Id
	@GeneratedValue
	private Long accId;
	
	private String accountID;
	private String accountName;
	private String website;
	private String firstName;
	private String lastName;
	private String title;
	private String email;
	private String location;
	private String phone;
	private String alternativePhon;
	private String linkedInProfile;
	private boolean validationDone;
	private boolean validationStatus;
	
	
/*	public ExcelSheetData(String accountId, String accountName, String website, String firstName, String lastName, String title,
			String email, String phone, String alternativePhon, String linkedInProfile) {
		
		this.accountID=accountID;
		this.accountName=accountName;
		this.website=website;
		this.firstName=firstName;
		this.lastName=lastName;
		this.title=title;
		this.email=email;
		this.phone=phone;
		this.alternativePhon=alternativePhon;
		this.linkedInProfile=linkedInProfile;
	
	}*/
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAlternativePhon() {
		return alternativePhon;
	}
	public void setAlternativePhon(String alternativePhon) {
		this.alternativePhon = alternativePhon;
	}
	public String getLinkedInProfile() {
		return linkedInProfile;
	}
	public void setLinkedInProfile(String linkedInProfile) {
		this.linkedInProfile = linkedInProfile;
	}
	public boolean isValidationDone() {
		return validationDone;
	}
	public void setValidationDone(boolean validationDone) {
		this.validationDone = validationDone;
	}
	public boolean isValidationStatus() {
		return validationStatus;
	}
	public void setValidationStatus(boolean validationStatus) {
		this.validationStatus = validationStatus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
