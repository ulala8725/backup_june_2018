package com.study.spring.vo;

public class BoardVO {
	private int no;
	private String title;
	private String contents;
	private String id;
	private String write_date;
	private int hit;
	private String save_file;
	private String save_file_sys;
	
	public BoardVO() {
		super();
	}
	
	public BoardVO(int no) {
		super();
		this.no = no;
	}

	public BoardVO(int no, String title, String id, String write_date, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.id = id;
		this.write_date = write_date;
		this.hit = hit;
	}
	
	public BoardVO(int no, String title, String contents, String id, String write_date, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.contents = contents;
		this.id = id;
		this.write_date = write_date;
		this.hit = hit;
	}
	
	public BoardVO(String title, String contents, String id, String save_file, String save_file_sys) {
		super();
		this.title = title;
		this.contents = contents;
		this.id = id;
		this.save_file = save_file;
		this.save_file_sys = save_file_sys;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public String getSave_file() {
		return save_file;
	}

	public void setSave_file(String save_file) {
		this.save_file = save_file;
	}

	public String getSave_file_sys() {
		return save_file_sys;
	}

	public void setSave_file_sys(String save_file_sys) {
		this.save_file_sys = save_file_sys;
	}

	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", contents=" + contents + ", id=" + id + ", write_date="
				+ write_date + ", hit=" + hit + ", save_file=" + save_file + ", save_file_sys=" + save_file_sys + "]";
	}
	
}
