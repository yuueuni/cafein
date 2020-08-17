package com.cafe.dto;

public class CafeDto {
	private int cafeno;
	private String name;
	private String status;
	private String address;
	private String tel;
	private String business_hours;
	private String thumb;
	private int like_count;
	private int stamp_count;
	
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
	public String getBusiness_hours() {
		return business_hours;
	}
	public void setBusiness_hours(String business_hours) {
		this.business_hours = business_hours;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getStamp_count() {
		return stamp_count;
	}
	public void setStamp_count(int stamp_count) {
		this.stamp_count = stamp_count;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CafeDto [cafeno=").append(cafeno).append(", name=").append(name).append(", status=")
				.append(status).append(", address=").append(address).append(", tel=").append(tel)
				.append(", business_hours=").append(business_hours).append(", thumb=").append(thumb)
				.append(", like_count=").append(like_count).append(", stamp_count=").append(stamp_count).append("]");
		return builder.toString();
	}
	
	
}
