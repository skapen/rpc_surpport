package test.action;

import java.util.Calendar;

public class Methods {
	Calendar cal = Calendar.getInstance();
	public String now_year = String.valueOf(cal.get(Calendar.YEAR) );
	public String now_month = String.valueOf( cal.get(Calendar.MONTH) +1 );		//※1月分追加する必要あり。
	public String now_date = String.valueOf( cal.get(Calendar.DATE) );
	public String now_hour = String.valueOf( cal.get(Calendar.HOUR_OF_DAY) );
	public String now_minute = String.valueOf( cal.get(Calendar.MINUTE) );
	public String now_second = String.valueOf( cal.get(Calendar.SECOND) );

	/**
	 * 日時を生成するメソッドです。  表示形式：yyyy/mm/dd hh.mm.ss
	 * @param dateAndtime
	 * @return dateAndtime
	 */
	public String makeDateandtime(String dateAndtime) {
		String[] dateTime = new String[6];	//Calendarクラスの各値を格納する
		dateTime[0] = now_year;
		dateTime[1] = now_month;
		dateTime[2] = now_date;
		dateTime[3] = now_hour;
		dateTime[4] = now_minute;
		dateTime[5] = now_second;

		//2桁未満(2文字以下)であれば10の位へ文字"0"を詰める
		for (int i=0; i< dateTime.length; i++) {
			while (dateTime[i].length() < 2) {
				dateTime[i] = "0"+ dateTime[i];
			}
		}

		//日時を"yyyy-mm-dd hh.mm.ss"の表示形式で生成
		String rcNumw_dt = dateTime[0]+"-"+ dateTime[1]+"-"+ dateTime[2]+ " "+ dateTime[3]
				+"."+ dateTime[4]+ "."+ dateTime[5];

		//引数へ日時を格納
		dateAndtime = rcNumw_dt;
		return dateAndtime;
	}

	/**
	 * 社員ID(yyyy-nnn)を生成するメソッドです。社員IDは、数字3桁(nnn)の部分を生成します。
	 * @param rcNum 既存のレコード数
	 * @return newId  新たに作成した 社員ID を返す。
	 */
	public String makeSyain_id(int rcNum) {
		rcNum += 1;	//新しいIDの番号：数えたレコード件数+1
		String newId = String.valueOf(rcNum);
		if(rcNum < 10) {
			newId = "00"+ newId;
		} else if (10 < rcNum && rcNum < 100 ) {
			newId = "0"+ newId;
		}
		return newId;
	}
}
