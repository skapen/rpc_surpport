
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.updatelistForm;
import logic.dao;
import model.human;

public class updatelistAction extends Action {
	
	
  	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
  		ArrayList<human> list;
  		
		if(this.isCancelled(request)){
			
			//html:cancelが押された場合の処理
			return mapping.findForward("back");
			
		}else{
		
			try{
				String search_name = ((updatelistForm)form).getSearch_name();


				if("".equals(search_name)){
					list = null;
				}else{
					dao dao = new dao();
					list = dao.getHumans(search_name);
				}
				
				request.setAttribute("list",list);
			
			}catch(Exception e){
				e.toString();
			}
			
			return mapping.findForward("list_ok");
		}
  	}
}

