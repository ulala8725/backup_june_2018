package test.actionForm;

import org.apache.struts.action.ActionForm;

/**
 * TEST0001.jspからもらうパラメータ
 * @param loginId ユーザーのID
 * @param passWord IDのパスワード
 * @author kim.sunhye
 */

public final class TestActionForm0001 extends ActionForm {
	/*
		ログインアソシエイトID
	*/	
	private String loginId;
	
	/*
	パスワード
	*/	
	private String passWord;
	
	//　以下はgetterとsetterメソッドです。
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}// class
