package kadai.actionForm;

import org.apache.struts.action.ActionForm;

/**
 * login.jspからもらうパラメータ
 * @param id ユーザーのID
 * @param password idのパスワード
 * @author kim.sunhye
 */

public class LoginForm extends ActionForm {
	private String id;		//ログインID
	private String pass;	//パスワード
	
	//　以下はgetterとsetterメソッドです。
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

}
