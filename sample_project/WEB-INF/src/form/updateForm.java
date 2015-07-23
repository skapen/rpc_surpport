package form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import logic.hexDigest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class updateForm extends ActionForm{
	private String syain_id;
	private String name;
	private String seibetsu;
	private String birthday;
	private String update_time;
	private String update_user;
	private String password;
	private String password_anather;


	public String getSyain_id() {
		return syain_id;	
	}


	public void setSyain_id(String syain_id) {
		this.syain_id = syain_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSeibetsu() {
		return seibetsu;
	}


	public void setSeibetsu(String seibetsu) {
		this.seibetsu = seibetsu;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getUpdate_time() {
		return update_time;
	}


	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}


	public String getUpdate_user() {
		return update_user;
	}


	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPassword_anather() {
		return password_anather;
	}


	public void setPassword_anather(String password_anather) {
		this.password_anather = password_anather;
	}
	
	public byte[] getPass256(){
		hexDigest hex = new hexDigest();
		return hex.makeDigest(getPassword());
		
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request){
		//Formの初期化
		syain_id = "";
		name = "";
		seibetsu = "";
		birthday = "";
		password = "";
		password_anather = "";
		update_time = "";
		update_user = "";
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		
		ActionErrors errors = new ActionErrors();
	//氏名空白チェック
		if("".equals(getName())){
			errors.add("name_req",new ActionMessage("errors.required","名前"));
		}

	//氏名全角チェック
		int moji_len = getName().length();
		int byte_len = getName().getBytes().length;
		
		if(byte_len != (moji_len * 2)){
			errors.add("name_zen",new ActionMessage("errors.zenkaku"));
		}
	//氏名範囲チェック	
		if(getName().length()>= 20){
			errors.add("name_len",new ActionMessage("errors.length","名前"));
		}
		

	//誕生日フォーマットチェック
	
		try{
			SimpleDateFormat fmtBirth = new SimpleDateFormat("yyyy-MM-dd");
			fmtBirth.setLenient(false);
			
			Date d = fmtBirth.parse(getBirthday());
		}catch(ParseException pe){
				errors.add("date",new ActionMessage("errors.length","生年月日"));
		}
		
	//性別文字チェック
		if(!(seibetsu.equals("1") || seibetsu.equals("2"))){
			errors.add("seibetsu_unmatch",new ActionMessage("性別の値が不正です"));
		}
		
	//パスワード空白チェック
		if("".equals(getPassword())){
			errors.add("pwd_req",new ActionMessage("errors.required","パスワード"));
		}
		
	//パスワード英数字チェック
		if(!((getPassword().matches("[0-9a-zA-Z]+")))){
			errors.add("pwd_ei",new ActionMessage("errors.eisuji"));
		}
		
	//?パスワード範囲チェック
	if(getPassword().length() >= 10 || getPassword().length() <= 5){
			errors.add("pwd_len",new ActionMessage("errors.length","パスワード"));	
	}
	
	//パスワード確認空白チェック
		if("".equals(getPassword_anather())){
			errors.add("pwd_req2",new ActionMessage("errors.required","パスワード"));
		}
	
	//パスワードマッチチェック
		
		if(!(getPassword_anather().equals(getPassword()))){
			errors.add("pwd_match",new ActionMessage("errors.match"));
		}
		
		return errors;
	}

	

}