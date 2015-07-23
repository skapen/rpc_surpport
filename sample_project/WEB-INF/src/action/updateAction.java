
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import form.updateForm;
import logic.DateStringFormat;
import logic.dao;
import model.human;

public class updateAction extends Action {

	boolean success_chk = false;
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		if(this.isCancelled(request)){
			
			//html:cancelが押された場合の処理
			return mapping.findForward("back");
		
		}else{
		
		
			updateForm up_form = (updateForm)form;
			dao dao = new dao();
		
			//update時間、最終更新者を取得
			up_form.setUpdate_time(new DateStringFormat()._date2string(new Date()));
			up_form.setUpdate_user((String)request.getSession().getAttribute("login_id"));
		
			//更新処理
			if(dao.update(up_form)){
				human update_result = dao.view_user(up_form.getSyain_id());
				request.getSession().setAttribute("human",update_result);
				return mapping.findForward("update_ok");
			}else{
				
				ActionMessages errors = new ActionMessages();
				errors.add("update_err",new ActionMessage("更新処理でエラーを検知しました。"));
				Exception e = new Exception();
				throw e;
				
			}
		}
				
	}
}


