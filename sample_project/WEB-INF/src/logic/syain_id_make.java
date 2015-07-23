package logic;

import java.sql.SQLException;
import java.util.*;
import logic.dao;



public class syain_id_make{
	String new_id;
	
	public String make(){
		//DB登録されている最終行の社員IDを抽出
			String old_id;
			
				old_id = new dao().getOld_id();
				
			
			//String yearに今年の年を入れる
			Calendar cal1 = Calendar.getInstance();
			String year = Integer.toString(cal1.get(Calendar.YEAR));
			
			//yearの値がold_idの先頭4文字(年号部分)と等しい場合
			//old_idの後ろ3文字を数値化し、更新。
			if(year.equals(old_id.substring(0,4))){
				new_id = year + "-" + String.format("%03d",new Integer(old_id.substring(6,8))+1);
			}else{
				new_id = year + "-" + "001";
			}
			

			
		return new_id;
	}
}	