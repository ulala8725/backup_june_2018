package myseasar2.action;

import javax.annotation.Resource;

import org.apache.struts.action.Action;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import myseasar2.dao.LoginDao;
import myseasar2.form.LoginForm;
import myseasar2.service.LoginService;

public class LoginAction extends Action{
	
	@Resource
	@ActionForm
	protected LoginForm loginForm;
//	@Resource
//	protected LoginService loginService;
	
	@Resource
	protected LoginDao loginDao;
	
	
	@Execute(validator=true, input="login.jsp") //(input="検証エラー時の遷移先")
	public String login(){
		return "main.jsp";
	}
	
//	@Execute(validator=true, validate="validateLogin", input="login.jsp") //(input="検証エラー時の遷移先")
//	public String login(){
//		// DB check
//		if (loginService.login(loginForm.id, loginForm.password)) {
//			return "main.jsp";
//		} else {
//			throw new ActionMessagesException("either id or password is needed to check!", false);
//		}
//	}
	
//	@Execute(validator=true, validate="validateLogin", input="login.jsp") //(input="検証エラー時の遷移先")
//	public String login(){
//		// DB check
//		LoginEntity loginEntity = loginDao.selectByIdPwd(loginForm.id, loginForm.password);
//		if (loginEntity == null) {
//			return "login.jsp";
//		} else {
//			return "main.jsp";
//		}
//	}
	
	@Execute(validator=false)
	public String join(){
		return "join.jsp";
	}
	
//	@Execute(validator=true, input="join.jsp")
	@Execute(validate="validate", input="join.jsp")
	public String enroll(){
		return "main.jsp";
	}

}
