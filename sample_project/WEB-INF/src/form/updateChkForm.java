package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class updateChkForm extends ActionForm{
	private String search_id;

	public String getSearch_id() {
		return search_id;
	}

	public void setSearch_id(String search_id) {
		this.search_id = search_id;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request){
		//Form�N���X����
		search_id = "";
	}

}