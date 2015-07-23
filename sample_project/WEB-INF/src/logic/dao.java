package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Statement;

import form.InsertChkForm;
import form.updateForm;
//import javax.sql.DataSource;
//import javax.naming.*;

import model.human;

/*
データアクセスする処理を記入
他のプログラムへはデータで渡す。

*/

public class dao{
	
	Connection con = null;
	PreparedStatement pStmt = null;
	PreparedStatement pStmt2 = null;
	ResultSet rs = null;
	
	private static final String SELECT_LIST			= "SELECT SYAIN_ID,NAME,SEIBETU,BIRTHDAY FROM t_syain WHERE NAME like ?";
	private static final String SELECT_OLD_ID		= "SELECT SYAIN_ID FROM t_syain ORDER BY SYAIN_ID desc limit 1";
	private static final String SELECT_USER_ID		= "SELECT SYAIN_ID,NAME,SEIBETU,BIRTHDAY FROM t_syain WHERE SYAIN_ID = ?";
	private static final String INSERT_SYAIN		= "INSERT INTO t_syain (SYAIN_ID,NAME,SEIBETU,BIRTHDAY,ENTRY_DATE,ENTRY_USER) VALUES (?,?,?,?,?,?)";
	private static final String INSERT_USER			= "INSERT INTO m_user (SYAIN_ID,PASSWORD2) VALUES (?,?)";
	private static final String UPDATE_SYAIN		= "UPDATE t_syain SET NAME = ?,SEIBETU = ?,BIRTHDAY = ?,UPDATE_DATE=?,UPDATE_USER=? where SYAIN_ID = ?";
	private static final String UPDATE_USER			= "UPDATE m_user  SET PASSWORD2 = ? where SYAIN_ID = ?";
	private static final String DELETE_USER			= "DELETE FROM t_syain WHERE SYAIN_ID = ?";
	private static final String DELETE_USER2		= "DELETE FROM m_user  WHERE SYAIN_ID = ?";
	private static final String LOGIN_USER_SELECT 	= "SELECT PASSWORD2 FROM m_user WHERE SYAIN_ID = ?";
	
	public void getConnect(){
		try{
			final String url = "jdbc:mysql://localhost:3306/mydb";
			final String user = "namatya";
			final String pass = "nama1010";

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, pass);
		}catch (Exception e) {
			 System.out.println("エラーが出力されました：" + e.toString());
		}
	}
	
	//updatelistのリスト出力
	public ArrayList<human> getHumans(String search_name) throws Exception,SQLException{
		
			ArrayList<human> list = new ArrayList<human>(30);
			this.getConnect();
			pStmt = con.prepareStatement(SELECT_LIST); 
			pStmt.setString(1, search_name + "%");
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				list.add(this.getHuman(rs));
			}
			
			rs.close();
			pStmt.close();
			
			try {
				con.close();
			} catch (Exception e) {}
			
			return list;
		
	
	}
	
	
	//resultsetをhumanクラスへ変換
	private human getHuman(ResultSet rs){
	
		human hum = new human();

		try {
			hum.setId(rs.getString("SYAIN_ID"));
			hum.setName(rs.getString("NAME"));
			hum.setSeibetsu((rs.getString("SEIBETU")));
			hum.setBirthday(rs.getString("BIRTHDAY"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return hum;
	}
	
	//DBのレコードを削除
	public void delete(String search_id){
		try{
			this.getConnect();
			pStmt = con.prepareStatement(DELETE_USER);
			pStmt.setString(1,search_id);
			pStmt.execute();
			pStmt.close();
			
			pStmt2 = con.prepareStatement(DELETE_USER2);
			pStmt2.setString(1,search_id);
			pStmt2.execute();
			pStmt2.close();
			
		}catch(SQLException sqle){}
	}

	//DSYAIN_IDよりレコード抽出
	public human view_user(String search_id) throws SQLException{
		
		try{	
			this.getConnect();
			
			pStmt = con.prepareStatement(SELECT_USER_ID);
			pStmt.setString(1,search_id);
			rs    = pStmt.executeQuery();
			rs.next();
			
			human hum = getHuman(rs);
			rs.close();
			pStmt.close();
			
			return hum;
			
		}catch(Exception e){
			System.out.println("エラー：" + e.toString());
		}finally{
			try {
				con.close();
			} catch (Exception e) {}
		}
	return null;

}
	
	
		//insertよりDBにレコ－ドを追加
		public boolean insert_db(InsertChkForm up_user){
			
			try {
				
				this.getConnect();
				pStmt = con.prepareStatement(INSERT_SYAIN);
			
				pStmt.setString(1,up_user.getSyain_id());
				pStmt.setString(2,up_user.getName());
				pStmt.setString(3,up_user.getSeibetsu());
				pStmt.setString(4,up_user.getBirthday());
				pStmt.setString(5,up_user.getEntry_date());
				pStmt.setString(6,up_user.getEntry_user());
				
				pStmt.execute();
				pStmt.close();
				
				pStmt2 = con.prepareStatement(INSERT_USER);
				pStmt2.setString(1,up_user.getSyain_id());
				pStmt2.setBytes(2,up_user.getPass256());
				
				pStmt2.execute();
				pStmt2.close();
				
				return true;
				
			}catch(SQLException sqle){
				System.out.println("sqlエラーが出力されました：" + sqle.toString());
			}catch(Exception e){
				System.out.println("エラー：" + e.toString());
			}finally{
				try {
					con.close();
				} catch (Exception e) {}
			}
				
			return false;
		
		}
		
				//insertよりDBにレコ－ドを追加
		public boolean update(updateForm up_form) throws SQLException,Exception {
			
			int pstmt_result = 0;
			int pstmt2_result = 0;
			
				
			try{
				this.getConnect();
				pStmt = con.prepareStatement(UPDATE_SYAIN);
			
				pStmt.setString(1,up_form.getName());
				pStmt.setString(2,up_form.getSeibetsu());
				pStmt.setString(3,up_form.getBirthday());
				pStmt.setString(4,up_form.getUpdate_time());
				pStmt.setString(5,up_form.getUpdate_user());
				
				pStmt.setString(6,up_form.getSyain_id());
				
				pstmt_result = pStmt.executeUpdate();
				pStmt.close();
				
				pStmt2 = con.prepareStatement(UPDATE_USER);
				pStmt2.setBytes(1, up_form.getPass256());
				pStmt2.setString(2, up_form.getSyain_id());
				
				pstmt2_result = pStmt2.executeUpdate();
				
				pStmt2.close();
				
			}catch(SQLException sqle){
				throw sqle;
			}catch(Exception e){
				throw e;
			}finally{
				try {
					con.close();
				} catch (Exception e) {}
			}
				
			boolean success_chk = (pstmt_result != 0 && pstmt2_result != 0);
			return success_chk;
		
		}
		
		//ログイン認証チェック用
		public boolean loginChk(String syain_id,String password){
			
			boolean loginchk_flg = false;

			try {
				
				this.getConnect();
				
				pStmt = con.prepareStatement(LOGIN_USER_SELECT);
				pStmt.setString(1,syain_id);
				rs = pStmt.executeQuery();
				
				if(rs.next()){
					byte[] passchk  = new hexDigest().makeDigest(password);
					byte[] passchk_rs = rs.getBytes("PASSWORD2");
					
						if(passchk.length == passchk_rs.length){
						
						loginchk_flg = true;
						//passchk配列の各要素がひとつでもあってない場合、falseに変換
						for(int i = 0;i < passchk.length;i++){
							if(passchk[i] != passchk_rs[i]){
								loginchk_flg =false;
							}
						}
					}
				}
				
				} catch (SQLException e) {
					System.out.println("SQLエラー：" + e.toString());
			}

			return loginchk_flg;
		}
		
			
	//DB内で一番最後に登録したIDを出力
	//date型のがいい?
	public String getOld_id(){
		String id = null;
		
		try{
			getConnect();
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_OLD_ID);
			if(rs.next()){
				id = rs.getString("SYAIN_ID");
			}
		
			rs.close();
			stmt.close();
			
			return id;
		
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try {
				con.close();
			} catch (Exception e) {}
		}
		
		return null;
			
		
	}

}

