package com.poseidon.dto;

public class CommentsDTO {
	private int cno,  member_no, rno,commentcount;
	private String rccontent, rcdate, member_name;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getRccontent() {
		return rccontent;
	}
	public void setRccontent(String rccontent) {
		this.rccontent = rccontent;
	}
	public String getRcdate() {
		return rcdate;
	}
	public void setRcdate(String rcdate) {
		this.rcdate = rcdate;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentscount) {
		this.commentcount = commentscount;
	}
}
