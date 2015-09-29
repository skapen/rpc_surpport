package test.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

/**
 * データベースからjspで入力したログインIDからセットとなっているパスワード
 * を探し出し、jspで入力したパスワードと合致していればログインします。
 * @author hattori
 * @version 1.0
 * */
public class LoginAction extends ActionSupport {
	Connection conn = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/KADAI";
	String user = "root";
	String password = "nin2db40";
	private String id;
	private String pass;
	private String loginID;
	private String loginPass;

	/**
	 * データベース接続-SQL文の実行を行いSELECT文によってカラムを取得します。
	 * @return "success" 登録画面へ推移。
	 * @return "error" エラーMSGの返却とログイン画面へ推移。
	 * */
	public String execute() throws Exception {
		try {
				final String SELECT = "SELECT LOGIN_ID, PASSWORD FROM M_USER WHERE LOGIN_ID = '"
									 + this.getId() + "';";

				//JDBCドライバ読み込み
				Class.forName("com.mysql.jdbc.Driver");

				//DB接続
				conn = DriverManager.getConnection(url, user, password);

				//SELECT文実行でログインIDとパスワードを検索
				pStmt = conn.prepareStatement(SELECT);
				rs = pStmt.executeQuery();

				//カラムの取得
				rs.next();
				loginID = rs.getString("LOGIN_ID");
				loginPass = rs.getString("PASSWORD");
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

		/*
		 * ログイン認証
		 * jspで入力したパスワードとSELECT文で取得したパスワードが合致していれば認証。
		 * 合致していなければエラーを返します。
		 */
		if (loginID.equals(getId()) && loginPass.equals(getPass()) ) {
			return "success";
		} else {
			addActionError("認証に失敗しました。");
			return "error";
		}
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass セットする pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
