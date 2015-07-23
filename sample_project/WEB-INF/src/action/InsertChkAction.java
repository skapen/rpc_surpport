package action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import logic.DateStringFormat;
import logic.dao;
import logic.syain_id_make;
import model.human;
import form.InsertChkForm; 

public class InsertChkAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
			
			if(this.isCancelled(request)){
			
				//html:cancelが押された場合の処理
				return mapping.findForward("back");
			
			}else{
			
				dao dao = new dao();
				
				//syain_id_makeクラスより、新しいIDを生成
				String new_id = new syain_id_make().make();
				
				//insertChkFormにあたら言いID、登録時間、登録ユーザーを設定
				InsertChkForm insertfm = (InsertChkForm)form;
				insertfm.setSyain_id(new_id);
    			insertfm.setEntry_date(new DateStringFormat()._date2string(new Date()));
				insertfm.setEntry_user(new_id);
			
				//DB更新処理
				if(dao.insert_db(insertfm)){
					human insert_result = dao.view_user(new_id);
					request.getSession().setAttribute("human",insert_result);
				}else{
					Exception e = new Exception();
					throw e;
				}
			
				return mapping.findForward("insert_ok");
			}
		}
		
	}
