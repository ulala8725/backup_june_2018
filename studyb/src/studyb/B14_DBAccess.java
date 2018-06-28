package studyb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class B14_DBAccess{
	Connection conn;
	private String url = "jdbc:mysql://localhost/kadaidb?useUnicode=true&characterEncoding=utf8"; 
	//文字化け回避のため：　?useUnicode=true&characterEncoding=utf8
	private String user = "root";
	private String password = "root";
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	//DBに接続し、トランザクション開始
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			System.out.println("データベースに接続成功");
			System.out.println("conn: " + conn);
		} catch (ClassNotFoundException e) {
			System.out.println("トランザクションを読み込めませんでした"+ e);
		} catch (SQLException e) {
			System.out.println("データベースに接続エラー" + e);
		}
	}
	
	//DBから切断、トランザクション終了
	public void disconnect() {
		if(conn != null) {
			try {
				if (pstmt != null) pstmt.close();
				if (rs != null)rs.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("データベースに切断エラー" + e);
//				e.printStackTrace();
			}
		}
	}
	
	//コミット
	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("commitエラー" + e);
//			e.printStackTrace();
		}
	}
	
	//ロールバック
	public void rollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			System.out.println("rollbackエラー" + e);
//			e.printStackTrace();
		}
	}
	
	public int updateExec(String sql) {
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate(); // 0>=success, -1=fail
			System.out.println("##########result###########" + result);
		} catch (SQLException e) {
			System.out.println("updateエラー" + e);
//			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pstmt = null;
			}
		}
			System.out.println("メソッドを外すばかりです。");
		return result;
	}
	
	public String[][] selectExec(String sql){
		int fromIdx = 0;
		String[][] result = new String[10][2];
		rs = null; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name"); //カラムの名前またはインデックス
				String kana = rs.getString("kana");
				
				result[fromIdx][0] = name;
				result[fromIdx][1] = kana;
				
				fromIdx++;
			}
			
		} catch (SQLException e) {
			System.out.println("selectエラー" + e);
//			e.printStackTrace();
		}
		return result;
	}
	
	public String[][] selectExec(String sql, int fromIdx){
		String[][] result = new String[10-fromIdx+1][2];
		int fromIdx2 = fromIdx;
		ResultSet rs = null; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fromIdx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name"); //カラムの名前またはインデックス
				String kana = rs.getString("kana");
				
				result[fromIdx-fromIdx2][0] = name;
				result[fromIdx-fromIdx2][1] = kana;
				
				fromIdx++;
			}
			
		} catch (SQLException e) {
			System.out.println("selectエラー" + e);
//			e.printStackTrace();
		}
		return result;
	}
	
	public String[][] selectExec(String sql, int fromIdx, int toIdx){
		String[][] result = new String[toIdx-fromIdx+1][2];
		ResultSet rs = null; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fromIdx);
			pstmt.setInt(2, toIdx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name"); //カラムの名前またはインデックス
				String kana = rs.getString("kana");
				
				result[fromIdx-1][0] = name;
				result[fromIdx-1][1] = kana;
				
				fromIdx++;
			}
		} catch (SQLException e) {
			System.out.println("selectエラー" + e);
//			e.printStackTrace();
		}
		return result;
	}
}//class

