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


public class InsertChkForm extends ActionForm{

	
	//登録する氏名
	private String name;
	
	//登録する性別
	private String seibetsu;
	
	//登録する生年月日
	private String birthday;
	
	//登録するパスワード
	private String ins_pass;
	
	//パスワード(確認用)
	private String ins_pass_anather;
	
	//社員ID
	private String syain_id;
	
	//登録日時
	private String entry_date;
	
	//登録したユーザー名
	private String entry_user;

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
	
	public String getSeibetsu_str(){
		String[] sei = {"男","女"};
		
		return sei[Integer.parseInt(this.getSeibetsu())];
	}
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIns_pass() {
		return ins_pass;
	}
	public void setIns_pass(String ins_pass) {
		this.ins_pass = ins_pass;
	}
	public String getIns_pass_anather() {
		return ins_pass_anather;
	}
	
	public void setIns_pass_anather(String ins_pass_anather) {
		this.ins_pass_anather = ins_pass_anather;
	}
	
	public void setSyain_id(String syain_id){
	
		this.syain_id = syain_id;
	}
	
	public String getSyain_id(){
	
		return syain_id;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	
	public byte[] getPass256(){
		hexDigest hex = new hexDigest();
		return hex.makeDigest(getIns_pass());
		
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request){
		//Formクラス初期化
		name = "";
		seibetsu = "";
		birthday = "";
		ins_pass = "";
		ins_pass_anather = "";
		entry_date = "";
		entry_user = "";
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
		if("".equals(getIns_pass())){
			errors.add("pwd_req",new ActionMessage("errors.required","パスワード"));
		}
		
	//パスワード英数字チェック
		if(!((getIns_pass().matches("[0-9a-zA-Z]+")))){
			errors.add("pwd_ei",new ActionMessage("errors.eisuji"));
		}
		
	//パスワード範囲チェック
	if(getIns_pass().length() >= 10 || getIns_pass().length() <= 5){
			errors.add("pwd_len",new ActionMessage("errors.length","パスワード"));	
	}
	
	//パスワードマッチチェック
		
		if(!(getIns_pass_anather().equals(getIns_pass()))){
			errors.add("pwd_match",new ActionMessage("errors.match"));
		}
		
		System.out.println(errors.toString());
		return errors;
	}

}
