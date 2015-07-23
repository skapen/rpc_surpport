package form;

import org.apache.struts.action.ActionForm;


public class LoginChkForm extends ActionForm{
	private String user;
	private String password;
	
	public String getUser(){
		return user;
	}
	
	public void setUser(String user){
		this.user = user;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}

}
