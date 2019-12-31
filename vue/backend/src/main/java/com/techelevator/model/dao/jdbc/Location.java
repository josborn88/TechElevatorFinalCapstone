package com.techelevator.model.dao.jdbc;

public class Location {
	
	private long location_id;
	private String clientName;
	private String locationName;
	private String address;
	private String riskLocation;
	
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRiskLocation() {
		return riskLocation;
	}
	public void setRiskLocation(String riskLocation) {
		this.riskLocation = riskLocation;
	}
	
	

}
