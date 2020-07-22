package com.cafe.dto;

public class CommentDto {
	private int cno;
	private String contents;
	private int pno;
	private String uid;
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommentDto [cno=").append(cno).append(", contents=").append(contents).append(", pno=")
				.append(pno).append(", uid=").append(uid).append("]");
		return builder.toString();
	}
	
	
}
