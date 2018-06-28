package kadai.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kadai.actionForm.EnrollForm;
import kadai.actionForm.SearchForm;

class DBAccess {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	private final String URL = "jdbc:mysql://localhost/kadaidb?useUnicode=true&characterEncoding=utf8";
	private final String ID = "root";
	private final String PASSWORD = "root";
	
	public void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, ID, PASSWORD);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//connect
	
	public void disconnect(){
		try {
			if (conn != null) {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// disconnect
	
	public int selectDB(String sql, String id) {
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}// selectDB
	
	public int selectDB(String sql, String id, String pass) {
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}// selectDB
	
	public ArrayList<String> selectDB(String sql, String id, String name, String kana) {
		ArrayList<String> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id + "%");
			pstmt.setString(2, name + "%");
			pstmt.setString(3, kana + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list.add(rs.getString("id"));
				list.add(rs.getString("name"));
				list.add(rs.getString("kana"));
				list.add(rs.getString("birth"));
				list.add(rs.getString("club"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}// selectDB
	
	public int insertDB(String sql1, String sql2, EnrollForm newUser){
		int result = -1;
		try {
			//ユーザー番号(no)を取得する。
			pstmt = conn.prepareStatement("select max(no) from userdetail");
			rs = pstmt.executeQuery();
			int lastNo = 0;
			while (rs.next()) {
				lastNo = rs.getInt(1);
			}
		
			pstmt = conn.prepareStatement(sql1);
			//1番目のクエリ(user)
			pstmt.setString(1, newUser.getId());
			pstmt.setString(2, newUser.getPass());
			pstmt.setString(3, newUser.getName());
			pstmt.setString(4, newUser.getKana());
			result = pstmt.executeUpdate();

			if (result != -1) {
				System.out.println("###sql1 succeeded!!!!!");
				pstmt = conn.prepareStatement(sql2);
				//2番目のクエリ(userdetail)
				pstmt.setInt(1, lastNo+1);
				pstmt.setString(2, newUser.getId());
				pstmt.setString(3, newUser.getBirth());
				pstmt.setString(4, newUser.getClub());
				result = pstmt.executeUpdate();
				
				if (result != -1) {
					commit();
				} else rollback();
				
			} else rollback();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateDB(String sql, SearchForm updateUser){
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updateUser.getmName());
			pstmt.setString(2, updateUser.getmKana());
			pstmt.setString(3, updateUser.getmBirth());
			pstmt.setString(4, updateUser.getmClub());
			pstmt.setString(5, updateUser.getmId());
			
			result = pstmt.executeUpdate();
			if (result != -1) {
				commit();
			} else rollback();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteDB(String sql1, String sql2, String id){
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
			if (result != -1) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				result = pstmt.executeUpdate();
				if (result != -1) {
					commit();
				} else rollback();
			} else rollback();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void commit(){
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rollback(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}// DBACcess
