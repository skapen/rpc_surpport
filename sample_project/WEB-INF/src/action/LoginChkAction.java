package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.dao;
import model.human;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import form.LoginChkForm;

public class LoginChkAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		//ログインIDとパスワードを取得
		String syain_id = ((LoginChkForm)form).getUser();
		String password = ((LoginChkForm)form).getPassword();
		
		String username;
		
		dao lcdao = new dao();
		
		//ログインIDを元にパスワードの整合性を確認
		if( lcdao.loginChk(syain_id,password)){
			
			human hum = lcdao.view_user(syain_id);
			username = hum.getName();
			HttpSession session = request.getSession(true);
			session.setAttribute("login" , username);
			session.setAttribute("login_id", syain_id);

			return mapping.findForward("menu");
		}else{
			ActionMessages errors = new ActionMessages();

			
			//ログイン失敗の場合、Form初期化後、エラーメッセージを表示
			LoginChkForm lcForm = (LoginChkForm)form;
			lcForm.setUser("");
			lcForm.setPassword("");
			errors.add("login_err",new ActionMessage("errors.login"));
			saveErrors(request,errors);
			return mapping.findForward("error");
		}
		
		
	}

}
