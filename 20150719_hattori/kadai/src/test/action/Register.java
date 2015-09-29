package test.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 新たにデータベース(T_SYAIN)へ社員を登録するクラスです。
 * @author hattori
 * @vers2ion 1.0
 */
public class Register {
	Connection conn = null;
	PreparedStatement pStmt1 = null;
	ResultSet rs1 = null;
	PreparedStatement pStmt2 = null;
	ResultSet rs2 = null;
	String url = "jdbc:mysql://localhost:3306/KADAI";
	String user = "root";
	String password = "nin2db40";
	int pStmt2_rs =0;
	private String name1;
	private String gender1;
	private String birthday1;
	String dateAndtime = null;		//日時生成メソッドで生成した値を格納

	//※作成中！
	/**
	 * jspで入力した値(氏名、性別、生年月日)をデータベースへ登録し、
	 * 社員IDを生成するメソッドです。
	 * @return registered 登録完了画面へ推移
	 * @throws Exception
	 */
	public String execute() throws Exception {
		try {
		final String SELECT = "SELECT COUNT(*) FROM T_SYAIN;";	//レコード数を数える
		final String INSERT = "INSERT INTO T_SYAIN VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		//インスタンス生成：日時生成メソッドの使用のためMethodクラスを使用
		Methods methods = new Methods();

		//JDBCドライバ読み込み
		Class.forName("com.mysql.jdbc.Driver");

		//DB接続
		conn = DriverManager.getConnection(url, user, password);

		//SELECT文でSYAIN_ID を検索
		pStmt1 = conn.prepareStatement(SELECT);
		rs1 = pStmt1.executeQuery();

		//結果を変数へ格納
		rs1.next();
		int rcNum = rs1.getInt(1);

		//INSERT文を実行し、T_SYAINへデータを登録
		pStmt2 = conn.prepareStatement(INSERT);
		pStmt2.setString(1, methods.now_year+ "-" + methods.makeSyain_id(rcNum));	//社員ID：makeSyain_id()で生成
		pStmt2.setString(2, getName1());		//名前：getter で取得した値を登録
		pStmt2.setString(3, getGender1());		//性別：getter で取得した値を登録
		pStmt2.setString(4, getBirthday1());	//生年月日：getter で取得した値を登録
		pStmt2.setString(5, methods.makeDateandtime(dateAndtime));		//登録日時：makeDt()メソッドで生成した値を使用
		pStmt2.setString(6, "test");		//登録ユーザ：仮で""の名前で登録→後にセッションなどでログイン後のユーザ名を使用
		pStmt2.setString(7, methods.makeDateandtime(dateAndtime));
		pStmt2.setString(8, "test");

		//データベースの更新処理を行う
		pStmt2_rs = pStmt2.executeUpdate();
		if (pStmt2_rs != 0) {
			pStmt2.close();
		}

		//登録完了画面へregister.jspで受け取ったデータを受け渡す。
		this.setName1(getName1());
		this.setGender1(getGender1());
		this.setBirthday1(getBirthday1());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			//db 切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		 //登録完了画面へ推移
		return "registered";
	}

	/**
	 * @return name1
	 */
	public String getName1() {
		return name1;
	}

	/**
	 * @param name1 セットする name1
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}

	/**
	 * @return gender1
	 */
	public String getGender1() {
		return gender1;
	}

	/**
	 * @param gender1 セットする gender1
	 */
	public void setGender1(String gender1) {
		this.gender1 = gender1;
	}

	/**
	 * @return birthday1
	 */
	public String getBirthday1() {
		return birthday1;
	}

	/**
	 * @param birthday1 セットする birthday1
	 */
	public void setBirthday1(String birthday1) {
		this.birthday1 = birthday1;
	}


}
