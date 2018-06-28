package com.study.spring.vo;

public class ReplyVO {
	private int no;
	private int boardNo;
	private String comments;
	private String id;
	private String write_date;
	
	public ReplyVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWrite_date() {
		return write_date;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

	@Override
	public String toString() {
		return "ReplyVO [no=" + no + ", boardNo=" + boardNo + ", comments=" + comments + ", id=" + id + ", write_date="
				+ write_date + "]";
	}
	
}