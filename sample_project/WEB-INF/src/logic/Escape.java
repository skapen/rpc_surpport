package logic;


//現在未使用

public class Escape{

	//htmlエスケープ処理
	public String HTMLEscape(String input) {
	
		input = substitude(input, "&",  "&amp;");
		input = substitude(input, "<",  "&lt;");
		input = substitude(input, ">",  "&gt;");
		input = substitude(input, "\"",  "&quot;");
		
		return input;
	}
	
	//SQLエスケープ処理
	public String SQLEscape(String input) {
	
		input = substitude(input, "'",  "''");
		input = substitude(input, "\\" , "\\\\");
		input = substitude(input, "\"",  "\"\"");
		return input;
	}
	
	//文字列を置換
	//@param input 対象文字列
	//@param pattern 変換前の文字
	//@param replacement 返還後の文字
	public String substitude(String input, String pattern, String replacement){
		
		int index = input.indexOf(pattern);
	
		if(index == -1){
			return input;
		}
	
		StringBuffer buf = new StringBuffer();
	
		buf.append(input.substring(0,index) + replacement);
	
		if(index + pattern.length() < input.length()){
		
			String rest = input.substring(index + pattern.length(),input.length());
			buf.append(substitude(rest,pattern,replacement));
		}
		return buf.toString();
		
	}
	
	
} 

