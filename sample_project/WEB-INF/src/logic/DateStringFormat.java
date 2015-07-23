package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateStringFormat {  
    //パターン定義  
    private static final String DATE_PATTERN = "yyyy'-'MM'-'dd' 'HH'.'mm'.'ss"; 
  
    //Date日付型をString文字列型へ変換  
    public String _date2string(Date date) {  
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);  
        return sdf.format(date);  
    }  
      
    //String文字列型をDate日付型へ変換  
    public Date _string2date(String value) {  
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);  
        try {  
            return sdf.parse(value);  
        } catch (ParseException e) {  
            return null;  
        }  
    } 
}
      