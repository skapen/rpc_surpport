package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class updatelistForm extends ActionForm{
	private String search_name;

	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}

	public String getSearch_name() {
		return search_name;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request){
		//Form初期化
		search_name = "";
	}


}