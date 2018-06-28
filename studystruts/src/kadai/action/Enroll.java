package kadai.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import kadai.actionForm.EnrollForm;

/**
 * enroll.jspで実行したロジック遂行
 * @param mapping 移動するページのキーワードが入る
 * @param form パラメータが入る
 * @return 作業をしてから移動ページのキーワード 
 * @author kim.sunhye
 */

public class Enroll extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("Enroll.java");
		
		HttpSession session = request.getSession();
		
		EnrollForm enrollForm = (EnrollForm) form;
		String id = enrollForm.getId();
		String pass = enrollForm.getPass();
		String passCheck = enrollForm.getPassCheck();
		String name = enrollForm.getName();
		String kana = enrollForm.getKana();
		String birth = enrollForm.getBirth();
		String club = enrollForm.getClub();
		
		String idCheck = request.getParameter("idCheck");
		String enrollBtn = request.getParameter("enrollBtn");
		String returnBtn = request.getParameter("returnBtn");
		String returnPage = request.getParameter("returnPage");
		String enrollConfirmBtn = enrollForm.getEnrollConfirmBtn();
		System.out.println("#####enrollConfirmBtn" + enrollConfirmBtn);
		
		String checkReturnBtn = request.getParameter("checkReturnBtn");
		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		
		DBAccess ac = new DBAccess();
		
		if(returnPage != null && !returnPage.equals("")){
			System.out.println("##returnPage");
			request.setAttribute("passCanBeUsed", pass);
			request.setAttribute("passCheckCanBeUsed", passCheck);
			request.setAttribute("nameCanBeUsed", name);
			request.setAttribute("kanaCanBeUsed", kana);
			session.setAttribute("birthCanBeUsed", birth); // input tag
			request.setAttribute("clubCanBeUsed", club);
			return mapping.findForward("fail");
		}
		
		if(returnBtn != null){
			System.out.println("##returnBtn");
			return mapping.findForward("return");
		}//returnBtn
		
		//3.ユーザーIDが重複する場合
		if (idCheck != null && idCheck.equals("idCheck")) {
			System.out.println("##idCheck");
			//3-1.ユーザーIDが空の場合
			if (id.equals("") || id == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse001"));
			} 
			//3-2.ユーザーIDが半角数字以外の場合
			if (!Pattern.compile("[0-9a-zA-z]+").matcher(id).matches()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse002"));
			}
			
			saveErrors(request, errors);
			if (errors.size() > 0) {
				return mapping.findForward("fail");
			}
			
			ac.connect();
			String sql = "select * from user where id like ?";
			int result = ac.selectDB(sql, id);
			//3-3-2.データベースから検索して、重複がない場合
			if (result == 0) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("guide.msl004"));
				request.getSession().setAttribute("idCanBeUsed", id);
				saveMessages(request, messages);
				
				return mapping.findForward("fail");
			} 
			//3-3-1.ユーザーIDが重複の場合
			else {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse003"));
			}
			ac.disconnect();
		}// idCheck
		
		if (enrollBtn != null && !enrollBtn.equals("")) {
			System.out.println("enrollBtn");
			//1.ユーザーIDが入力されていない場合
			if (id.equals("") || id == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse001"));
			} 
			//2.ユーザーIDが半角英数字以外の場合
			if (!Pattern.compile("[0-9a-zA-z]+").matcher(id).matches()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse002"));
			}
			//4.パスワードが入力されていない場合
			if (pass.equals("") || pass == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse005"));
			}
			//5.パスワードが半角英数字以外の場合
			if (!Pattern.compile("[0-9a-zA-z]+").matcher(pass).matches()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse006"));
			}
			//6.パスワード再入力が入力されていない場合
			if (passCheck.equals("") || passCheck == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse007"));
			}
			//7.パスワード再入力が半角英数字以外の場合
			if (!Pattern.compile("[0-9a-zA-z]+").matcher(passCheck).matches()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse008"));
			}
			//8.パスワード再入力が入力したパスワードと一致しない場合
			if (!pass.equals(passCheck)) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse025"));
			}
			//9.名前が入力されていない場合
			if (name.equals("") || name == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse009"));
			}
			//10.名前が全角以外の場合
			if (!Pattern.compile("[^ -~｡-ﾟ]+").matcher(name).matches()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse010"));
			}
			//11.カナが入力されていない場合
			if (kana.equals("") || kana == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse012"));
			}
			//12.カナが半角以外の場合
			if (!Pattern.compile("[ -~｡-ﾟ]++").matcher(kana).matches()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse013"));
			}
			//13.生年月日が入力されていない場合
			if (birth.equals("") || birth == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse016"));
			}
			//14.生年月日が半角英数字以外の場合
			System.out.println("###birthday date: " + birth);
			String[] birthSplit = birth.split("/");
			for (int i = 0; i < birthSplit.length; i++) {
				if (!Pattern.compile("[0-9a-zA-z]+").matcher(birthSplit[i]).matches()) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse017"));
				}
			}
			//15.生年月日が正しくない年月日の場合
			try {
				SimpleDateFormat format =  new SimpleDateFormat("yyyy/mm/dd");
				format.setLenient(false);
				Date date =  format.parse(birth);
				boolean result = format.format(date).equals(birth);
				if (!result) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse018"));
				}
			} catch (ParseException e) {
				// 正しくない形式に日付が渡されたとき　ex)2018/01/
				e.printStackTrace();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse018"));
			}
			//16.委員会が全角以外の場合
			if (!Pattern.compile("[^ -~｡-ﾟ]+").matcher(club).matches()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse019"));
			}
		}// enrollBtn
		
		if (checkReturnBtn != null && !checkReturnBtn.equals("")) {
			System.out.println("##checkReturnBtn");
//			enrollForm.setCheckReturnBtn("");
			return mapping.findForward("fail");
		}// enrollConfirmBtn
		
		if (enrollConfirmBtn != null) {
			System.out.println("##enrollConfirmBtn");
			ac.connect();
			EnrollForm newUser = new EnrollForm(enrollForm.getId(), enrollForm.getPass(), 
					enrollForm.getName(), enrollForm.getKana(), enrollForm.getBirth(),
					enrollForm.getClub());
			
			System.out.println("###newUserID: " + enrollForm.getClub());
			
			String sql1 = "insert into user values (?, ?, ?, ?)";
			String sql2 = "insert into userdetail values (?, ?, ?, ?)";

			int result = ac.insertDB(sql1, sql2, newUser);
			System.out.println("###" + result);
			if (result != -1) {
				ac.commit();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("guide.msl007"));
				saveMessages(request, messages);
				return mapping.findForward("return");
			} else {
				ac.rollback();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse024"));
			}
			
		}// enrollConfirmBtn
		
		saveErrors(request, errors);
		if (errors.size() > 0) {
			//エラーの場合、今まで入力した項目を保存して入力ページへ戻る
			request.getSession().setAttribute("passCanBeUsed", pass);
			request.getSession().setAttribute("passCheckCanBeUsed", passCheck);
			request.getSession().setAttribute("nameCanBeUsed", name);
			request.getSession().setAttribute("kanaCanBeUsed", kana);
			request.getSession().setAttribute("birthCanBeUsed", birth);
			request.getSession().setAttribute("clubCanBeUsed", club);
			return mapping.findForward("fail");
		}
		
		request.setAttribute("passCanBeUsed", pass);
		request.setAttribute("passCheckCanBeUsed", passCheck);
		request.setAttribute("nameCanBeUsed", name);
		request.setAttribute("kanaCanBeUsed", kana);
		request.setAttribute("birthCanBeUsed", birth);
		request.setAttribute("clubCanBeUsed", club);
		return mapping.findForward("success");
		
	}
}
