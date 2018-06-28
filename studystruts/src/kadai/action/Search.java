package kadai.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import kadai.actionForm.SearchForm;

/**
 * search.jspで実行したロジック遂行
 * @param mapping 移動するページのキーワードが入る
 * @param form パラメータが入る
 * @return 条件を入れて検索するときの移動ページのキーワード 
 * @author kim.sunhye
 */

public class Search extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("Search.java");
		
		SearchForm searchForm = (SearchForm) form;
		String id = searchForm.getId().trim();
		String name = searchForm.getName().trim();
		String kana = searchForm.getKana().trim();
		String searchBtn = searchForm.getSearch();
		
		String logout = request.getParameter("logout");
		String enroll =  request.getParameter("enroll");
		String modify =  request.getParameter("modify");
		String delete =  request.getParameter("delete");
		
		String returnModifyPage = request.getParameter("returnModifyPage");
		String returnSearchPage = request.getParameter("returnSearchPage");
		String modifyConfirmBtn =  searchForm.getModifyConfirmBtn();
		String deleteUser = request.getParameter("deleteUser");
		
		
		//DBの情報と比較するためDBと連結する
		DBAccess ac = new DBAccess();
		
		// DB変更をする
		String modifyUser = request.getParameter("modifyUser");
		
		HttpSession session = request.getSession();
		
		//エラーメッセージを送る
		ActionErrors errors = new ActionErrors();
		//成功メッセージを送る
		ActionMessages messages = new ActionMessages();
		
		
		//ログアウトボタンを押したとき
		if (logout != null) {
			System.out.println("search.jsp-logoutBtn");
			session.invalidate();
			return mapping.findForward("logout");
		} 
		
		// DBの更新をしなくて更新ページに戻る
		if (returnModifyPage != null && returnModifyPage.equals("returnModifyPage")) {
			System.out.println("search.jsp-returnModifyPage");
			returnModifyPage = null;
			return mapping.findForward("modify");
		}
		
		// DBの更新をしなくて検索ページに戻る
		if (returnSearchPage != null) {
			System.out.println("search.jsp-returnSearchPage");
			returnSearchPage = null;
			return mapping.findForward("result");
		}
		
		// DBの情報を変更するとき
		if (modifyUser != null) {
			System.out.println("search.jsp-modifyUser");
			modifyUser = null;
			
			String mId = searchForm.getmId().trim();
			String mName = request.getParameter("name").trim();
			String mKana = request.getParameter("kana").trim();
			String mBirth = searchForm.getmBirth().trim();
			String mClub = request.getParameter("club").trim();
			
			//1.名前が入力されていない場合
			if (mName.equals("") || mName == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse009"));
			}
			//2.名前が全角以外の場合
			if (!mName.equals("") || mName != null) {	//全角regular expression:[^ -~｡-ﾟ]+
				boolean result = Pattern.compile("[^ -~｡-ﾟ]+").matcher(name).matches();
				if (!result) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse010"));
				}
			}
			//3.カナが入力されていない場合
			if (mKana.equals("") || mKana == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse012"));
			}
			//4.カナが半角以外の場合
			if (!mKana.equals("") || mKana != null) {	//半角regular expression:[ -~｡-ﾟ]+
				boolean result = Pattern.compile("[ -~｡-ﾟ]++").matcher(kana).matches();
				if (!result) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse013"));
				}
			}
			
			
			//5.生年月日が入力されていない場合
			if (mBirth.equals("") || mBirth == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse016"));
			}
			//6.生年月日が半角英数字以外の場合
			String[] birthSplit = mBirth.split("-");
			for (int i = 0; i < birthSplit.length; i++) {
				if (!Pattern.compile("[0-9a-zA-z]+").matcher(birthSplit[i]).matches()) {
					System.out.println(birthSplit[i] + ": string exist");
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse017"));
				}
			}
			//7.生年月日が正しくない年月日の場合
			try {
				SimpleDateFormat format =  new SimpleDateFormat("yyyy-mm-dd");
				format.setLenient(false);
				Date date =  format.parse(mBirth);
				boolean result = format.format(date).equals(mBirth);
				if (!result) {
					System.out.println("birth format false");
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse018"));
				}
			} catch (ParseException e) {
				// 正しくない形式に日付が渡されたとき　ex)2018/01/
				e.printStackTrace();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse018"));
			}
			
			//委員会が全角以外の場合
			if (!Pattern.compile("[^ -~｡-ﾟ]+").matcher(mClub).matches()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse019"));
			}
			
			//errorがない場合変更確認画面に移動
			if (errors.isEmpty()) {
//				request.setAttribute("mId", mId);
//				request.setAttribute("mName", mName);
//				request.setAttribute("mKana", mKana);
//				request.setAttribute("mBirth", mBirth);
//				request.setAttribute("mClub", mClub);
				
				searchForm.setmId(mId);
				searchForm.setmName(mName);
				searchForm.setmKana(mKana);
				searchForm.setmBirth(mBirth);
				searchForm.setmClub(mClub);
				return mapping.findForward("modifyConfirm");
			} 
			//errorがある場合変更入力画面に戻る
			else {
				saveErrors(request, errors);
				return mapping.findForward("modify");
			}
			
		}
		
		//DBのユーザデータを変更する
		if (modifyConfirmBtn != null && modifyConfirmBtn.equals("modifyConfirm")) {
			System.out.println("search.jsp-modifyConfirmBtn");
			searchForm.setModifyConfirmBtn("");
			ac.connect();
			String sql = "update user u, userdetail d "
					+ "set u.name = ?, u.kana = ?, "
					+ "d.birth = ?, d.club = ? "
					+ "where u.id = ? and u.id = d.id";
			SearchForm updateUser = new SearchForm(searchForm.getmId(), searchForm.getmName(), 
								searchForm.getmKana(), searchForm.getmBirth(), searchForm.getmClub());
			int result = ac.updateDB(sql, updateUser);
			
			if (result != -1) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("guide.msl002"));
				System.out.println("db update success");
				saveMessages(request, messages);
			} else {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse020"));
				System.out.println("db update fail");
				saveErrors(request, errors);
			}
			
			return mapping.findForward("result");
		}
		
		//新規登録ボタンを押したとき
		if (enroll != null) {
			System.out.println("search.jsp-enrollBtn");
			enroll = null;
			return mapping.findForward("enroll");
		} 
		
		//更新ボタンを押したとき
		if (modify != null) {
			System.out.println("search.jsp-modifyBtn");
			modify = null;
			String list = request.getParameter("list");
			String[] listSplit = list.split(",");

			//javaに特殊記号を書くときに
			//[]に絡む必要がある：* + $ |
			//\\をつける必要がある：( ) { } ^ [ ] \
			String mId = listSplit[0].replaceAll("\\[", "");
			String mClub = listSplit[4].replaceAll("\\]", "");
			
			searchForm.setmId(mId);
			searchForm.setmName(listSplit[1]);
			searchForm.setmKana(listSplit[2]);
			searchForm.setmBirth(listSplit[3]);
			searchForm.setmClub(mClub);
			
			return mapping.findForward("modify");
		} 
		
		//削除ボタンを押したとき
		if (delete != null && delete.equals("delete")) {
			System.out.println("search.jsp-deleteBtn");
			delete = null;
			return mapping.findForward("delete");
		} 
		
		//DBのデータを削除する
		if (deleteUser != null && deleteUser.equals("deleteUser")) {
			System.out.println("search.jsp-deleteUser");
			deleteUser = null;
			
			ac.connect();
			String sql1 = "delete from user where id = ?";
			String sql2 = "delete from userdetail where id = ?";
			int result = ac.deleteDB(sql1, sql2, searchForm.getmId());
			if (result != -1) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("guide.msl009"));
				System.out.println("db delete success");
				saveMessages(request, messages);
			} else {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse020"));
				System.out.println("db delete fail");
				saveErrors(request, errors);
			}
			return mapping.findForward("result");
		}
		
		//検索ボタンを押したとき
		if (searchBtn != null && searchBtn.equals("searchBtn")) {
			System.out.println("search.jsp-searchBtn");
			searchForm.setSearch("");
			
			ac.connect();
			
			//1.どのテキストボックスにも何も入力されていない場合	
			if (id.equals("")||name.equals("")||kana.equals("")
					||id == null||name == null||kana == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse015"));
			}// 1
			
			//2.ユーザーIDが半角英数字以外の場合
			if (!Pattern.compile("[0-9a-zA-z]+").matcher(id).matches()) {	//半角英数字regular expression:[0-9a-zA-z]+
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse002"));
			}// 2
			
			//3.名前が全角以外の場合
			if (!name.equals("") || name != null) {	//全角regular expression:[^ -~｡-ﾟ]+
				boolean result = Pattern.compile("[^ -~｡-ﾟ]+").matcher(name).matches();
				if (!result) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse010"));
				}
			}// 3
			
			//4.カナが半角以外の場合
			if (!kana.equals("") || kana != null) {	//半角regular expression:[ -~｡-ﾟ]+
				boolean result = Pattern.compile("[ -~｡-ﾟ]++").matcher(kana).matches();
				if (!result) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse013"));
				}
			}// 4
			
			//5.検索を失敗した場合
			if (errors.isEmpty()) {
				ArrayList<String> list = new ArrayList<>();
				String sql = "select u.id, name, kana, birth, club from user u, userdetail d "
						+ "where u.id like ? and name like ? and kana like ? and u.id like d.id";
				list = ac.selectDB(sql, id, name, kana);
				if (list.isEmpty()) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.mse022"));
				} else {
					request.setAttribute("list", list);
					return mapping.findForward("result");
				}
			}
			
			//DBとの連結を切断する
			ac.disconnect();
		} 
		
		saveErrors(request, errors);
		return mapping.findForward("result");
	}

}
