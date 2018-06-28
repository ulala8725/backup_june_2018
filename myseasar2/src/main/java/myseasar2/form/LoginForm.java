package myseasar2.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Required;

public class LoginForm extends ActionForm {
	@Required
	@Minlength(minlength=3)
	@Maxlength(maxlength=12)
	public String id;
	@Required
	@Minlength(minlength=5)
	@Maxlength(maxlength=15)
	public String password;
//	@Required(target="enroll")
//	@Validwhen(test="(rePassword==password)", 
//		msg=@Msg(key="errors.rePassword"),
//		target="enroll")
	public String rePassword;
	@Required(target="enroll")
	@EmailType(target="enroll")
	public String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ActionMessages validate(){
		ActionMessages errors = new ActionMessages();
		if (!rePassword.equals(password)) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rePassword"));
		}
		return errors;
	}
	
	public ActionMessages validateLogin(){
		ActionMessages errors = new ActionMessages();
		if(id.equals("") || id==null){
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required"));
		}
		if(password.equals("") || password==null){
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required"));
		}
		return errors;
		
	}
	
}
