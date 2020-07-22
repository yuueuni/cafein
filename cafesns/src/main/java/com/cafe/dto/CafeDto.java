package com.cafe.dto;

public class CafeDto {
	private int cafeno;
	private String name;
	private String status;
	private String address;
	private String tel;
	private String business;
	private double lat;
	private double lng;
	private double scale;
	private String opendate;
	private String business_hours;
	
	public int getCafeno() {
		return cafeno;
	}
	public void setCafeno(int cafeno) {
		this.cafeno = cafeno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getScale() {
		return scale;
	}
	public void setScale(double scale) {
		this.scale = scale;
	}
	public String getOpendate() {
		return opendate;
	}
	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}
	public String getBusiness_hours() {
		return business_hours;
	}
	public void setBusiness_hours(String business_hours) {
		this.business_hours = business_hours;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CafeDto [cafeno=").append(cafeno).append(", name=").append(name).append(", status=")
				.append(status).append(", address=").append(address).append(", tel=").append(tel).append(", business=")
				.append(business).append(", lat=").append(lat).append(", lng=").append(lng).append(", scale=")
				.append(scale).append(", opendate=").append(opendate).append(", business_hours=").append(business_hours)
				.append("]");
		return builder.toString();
	}
	
	
}
