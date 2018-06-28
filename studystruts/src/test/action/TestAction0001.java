package test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import test.actionForm.TestActionForm0001;

/**
 * TEST0001.jspで実行したロジック遂行
 * @param mapping 移動するページのキーワードが入る
 * @param form パラメータが入る
 * @return ログインするときの移動ページのキーワード 
 * @author kim.sunhye
 */

public class TestAction0001 extends Action {
	
	// log使用
	static Log log = LogFactory.getLog(TestAction0001.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm form, 
							HttpServletRequest req, HttpServletResponse res) {
		
		//ActionFormを取得する。
		TestActionForm0001 test = (TestActionForm0001) form;
		
		//とりあえずとグインIDとパスワードに同じものが入ったらログイン成功としましょう。
		if (test.getLoginId().equals(test.getPassWord())) {
			
			//ログイン成功時のに遷移先
			log.warn("エラー:　" + test.getLoginId());		// log使用
			return (mapping.findForward("success"));	
		}
			//ログイン失敗時のに遷移先
			log.warn("エラー:　" + test.getLoginId());		// log使用
			return (mapping.findForward("fail"));
	}// execute
}// class
