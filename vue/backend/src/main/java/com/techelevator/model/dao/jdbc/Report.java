package com.techelevator.model.dao.jdbc;

import java.util.Date;

public class Report {

	private String name;
	private String clientName;
	private String locationName;
	private String summary;
	private String riskReporter;
	private String imageName;
	private String locationAddress;
	private Date dateFiled;

	public String getName() {
		return name;
	}

	public String getClientName() {
		return clientName;
	}

	public String getLocationName() {
		return locationName;
	}

	public String getSummary() {
		return summary;
	}

	public String getRiskReporter() {
		return riskReporter;
	}

	public String getImageName() {
		return imageName;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public Date getDateFiled() {
		return dateFiled;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setRiskReporter(String riskReporter) {
		this.riskReporter = riskReporter;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public void setDateFiled(Date dateFiled) {
		this.dateFiled = dateFiled;
	}

}