
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import logic.dao;
import model.human;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import form.updateChkForm;

public class updateChkAction extends LookupDispatchAction {

	boolean success_chk = false;
	
	protected Map<String,String> getKeyMethodMap(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("button.update","update");
		map.put("button.delete","delete");
		return map;
	}
	
	//更新ボタン押下時処理
	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		String search_id = ((updateChkForm)form).getSearch_id();
		
		
		try{
			dao dao = new dao();
			human hum =  dao.view_user(search_id);
			
			HttpSession session = ((HttpServletRequest)request).getSession();
			
			session.setAttribute("human", hum);

			return mapping.findForward("update_open");
		
		}catch(SQLException sqle){
			System.out.println(sqle.toString());
		}
		
		return mapping.findForward("delete_open");

	}
	
	//削除ボタン押下時処理
	public ActionForward delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		//search_idの値と同じidのレコードを削除する
		dao dao = new dao();
		dao.delete(((updateChkForm)form).getSearch_id());
		
		return mapping.findForward("delete_open");

	}

}

