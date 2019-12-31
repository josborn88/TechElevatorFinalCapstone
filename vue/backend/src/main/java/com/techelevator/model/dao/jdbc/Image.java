package com.techelevator.model.dao.jdbc;

public class Image {
	
	private long imageId;
	private String imageName;
	
	public long getImageId() {
		return imageId;
	}
	public String getImageName() {
		return imageName;
	}
	
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
