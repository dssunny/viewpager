package com.example.BE;

public class ClsZipBE {
	 private double dbLatitude=0;  
	private String code = null;
	private String name = null;
	 private boolean selected = false;
	 
	
	 private double dbLongitude=0;
	 /**
	 * @return the latitude
	 */
	public double getLatitude() {
		return dbLatitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		dbLatitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return dbLongitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		dbLongitude = longitude;
	}
	
	 
	  
	 public ClsZipBE(String code, String name, boolean selected, double Latitude, double Longitude) {
	  super();
	  this.code = code;
	  this.name = name;
	  this.selected = selected;
	  this.dbLatitude=Latitude;
	  this.dbLongitude=Longitude;
	  
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