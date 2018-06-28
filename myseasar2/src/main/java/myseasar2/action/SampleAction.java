package myseasar2.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import myseasar2.form.SampleForm;

public class SampleAction {
	@Resource
	@ActionForm
	protected SampleForm sampleForm;
	
	@Execute(validator=false)
	public String index(){
		return "sampleView.jsp";
	}
	
	@Execute(validator=false)
	public String next(){
		return "sampleMessage.jsp";
	}
	
}
