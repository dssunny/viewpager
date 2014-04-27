package com.example.BE;

public class ZipBE {
	  
	 String code = null;
	 String name = null;
	 boolean selected = false;
	 double Latitude=0;
	 /**
	 * @return the latitude
	 */
	public double getLatitude() {
		return Latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return Longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	double Longitude=0;
	 
	  
	 public ZipBE(String code, String name, boolean selected, double Latitude, double Longitude) {
	  super();
	  this.code = code;
	  this.name = name;
	  this.selected = selected;
	  this.Latitude=Latitude;
	  this.Longitude=Longitude;
	  
	 }
	  
	 public String getCode() {
	  return code;
	 }
	 public void setCode(String code) {
	  this.code = code;
	 }
	 public String getName() {
	  return name;
	 }
	 public void setName(String name) {
	  this.name = name;
	 }
	 
	 public boolean isSelected() {
	  return selected;
	 }
	 public void setSelected(boolean selected) {
	  this.selected = selected;
	 }
	  
	}