package com.study.spring.vo;

public class UserVO {
	private String id;
	private String pass;
	private String passCheck;
	private String name;
	private String kana;
	private String birth;
	private String club;
	
	public UserVO() {
		super();
	}
	
	public UserVO(String id) {
		super();
		this.id = id;
	}

	public UserVO(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}
	
	public UserVO(String id, String name, String kana) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
	}

	public UserVO(String id, String name, String kana, String birth, String club) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.birth = birth;
		this.club = club;
	}
	
	public UserVO(String id, String pass, String name, String kana, String birth, String club) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.kana = kana;
		this.birth = birth;
		this.club = club;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassCheck() {
		return passCheck;
	}

	public void setPassCheck(String passCheck) {
		this.passCheck = passCheck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	
}
