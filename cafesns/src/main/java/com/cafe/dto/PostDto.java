package com.cafe.dto;

import java.sql.Date;

public class PostDto {
	private int pno;
	private String uid;
	private int cafeno;
	private String contents;
	private String image;
	private String taste;
	private String mood;
	private String clean;
	private Date date;
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getCafeno() {
		return cafeno;
	}
	public void setCafeno(int cafeno) {
		this.cafeno = cafeno;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getClean() {
		return clean;
	}
	public void setClean(String clean) {
		this.clean = clean;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostDto [pno=").append(pno).append(", uid=").append(uid).append(", cafeno=").append(cafeno)
				.append(", contents=").append(contents).append(", image=").append(image).append(", taste=")
				.append(taste).append(", mood=").append(mood).append(", clean=").append(clean).append(", date=")
				.append(date).append("]");
		return builder.toString();
	}
	
	
	
	
	
}
