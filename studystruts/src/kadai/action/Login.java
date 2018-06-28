package kadai.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import kadai.actionForm.LoginForm;

/**
 * login.jspで実行したロジック遂行
 * @param mapping 移動するページのキーワードが入る
 * @param form パラメータが入る
 * @return ログインするときの移動ページのキーワード 
 * @author kim.sunhye
 */

public class Login extends Action{
	//log使用
	static Log log = LogFactory.getLog(Login.class.getName()); 
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession(true);
		
		LoginForm loginForm = (LoginForm) form;
		String id = loginForm.getId().trim();
		String pass = loginForm.getPass().trim();

		
		//DBの情報と比較するためDBと連結する
		DBAccess ac = new DBAccess();
		ac.connect();
		
		//エラーメッセージを送る
//		ActionMessages messages = new ActionMessages();
		ActionErrors errors = new ActionErrors();

		//1.ユーザーIDが入力されていない場合
		if (id.equals("") || id == null) {
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.mse001"));
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse001"));
		}
		
		//2.ユーザーIDが半角英数字以外の場合
		if (!id.matches(("[0-9a-zA-Z]+"))) {
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.mse002"));
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse002"));
		}
		
		//3.ユーザーIDが存在しない場合
		String sqlId = "select * from user where id like ?";
		int resultId = ac.selectDB(sqlId, id);
		if (resultId == 0 ) {
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.mse004"));
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse004"));
		}
		
		//4.パスワードが入力されていない場合
		if (pass.equals("") || pass == null) {
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.mse005"));
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse005"));
		}
		
		//5.パスワードが半角英数字以外の場合
		if (!pass.matches(("[0-9a-zA-Z]+"))) {
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.mse006"));
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse006"));
		}
		
		//6.パスワードが存在しない場合
		String sqlPass = "select * from user where id like ? and pass like ?";
		int resultPass = ac.selectDB(sqlPass, id, pass);
		if (resultPass == 0 ) {
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.mse007"));
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse007"));
		}
		
		//DBとの連結を切断する
		ac.disconnect();
		
		//エラーメッセージを入れてログイン結果を伝送
//		saveMessages(request, messages);
		saveErrors(request, errors);
		if (errors.size() == 0) {
			session.setAttribute("id", id);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("fail");
		}
	}// execute
}
