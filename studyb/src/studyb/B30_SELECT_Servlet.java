package studyb;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.ToLongBiFunction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.ResultSetMetaData;

/**
 * 入力フォームから送信した内容を表示するサーブレット
 * @author kim.sunhye
 */

//@WebServlet("/B30")
public class B30_SELECT_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	B14_DBAccess ac = null;
	HttpSession session;
	PreparedStatement pstmt;
	ResultSet rs;
	java.sql.ResultSetMetaData rsmd;
	ArrayList<String> list;
       
    public B30_SELECT_Servlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		session = request.getSession(false);
		
		if(session != null) {
			//javascriptから渡された値
			String contentArray = request.getParameter("contentArray");
			
			//contentArrayに複数の値入れてある場合、contentの内容を確認する。
			String[] contentCount = null;
			if(contentArray != null) contentCount = contentArray.split(",");
	
			String text = request.getParameter("text");
			String select = request.getParameter("select");
			String sort = request.getParameter("sort");
			
			//DBで検索する値を入れる変数
			String testno = null;
			String name = null;
			String kana = null;
			
			//DBに接続
			ac = new B14_DBAccess();
			ac.connect();
			
			String sql = "SELECT " + contentArray + " FROM testtable1 WHERE name LIKE ? ORDER BY " + select + " " + sort;
			
			int rowNum = 0;
			
			try {
				pstmt = ac.conn.prepareStatement(sql);
				pstmt.setString(1, text + "%");
				
				rs = pstmt.executeQuery();
				list = new ArrayList<>();
				
				while (rs.next()) {
					String resString = "";
					rowNum = rs.getRow(); // データベースの結果がemptyの場合は0
					for (int i = 0; i < contentCount.length; i++) {
						if (contentCount[i].equals("TESTNO")) {
							testno = rs.getString("testno");
						}
						if (contentCount[i].equals("NAME")) {
							name = rs.getString("name");
						}
						if (contentCount[i].equals("KANA")) {
							kana = rs.getString("kana");
						}
					}//for
					
					if (testno != null) {
						resString += testno + ":";
					}
					if (name != null) {
						resString += name + ":";
					}
					if (kana != null) {
						resString += kana;
					}
					
					list.add(resString);
				}//while
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//DBから切断
				ac.disconnect();
			}
			
			if(rowNum == 0) {
				//データベースの結果がemptyの場合、listにnullを入れる
				list = null;
			} 
			
			else {
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				
			}
			

			//javascriptに渡す値
			session.setAttribute("list", list);
			session.setAttribute("contentCount", contentCount);
			session.setAttribute("contentArray", contentArray); // String
			session.setAttribute("preText", text);
			session.setAttribute("preSelect", select);
			session.setAttribute("preSort", sort);
		
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("B30_SELECT.jsp");
		dispatcher.forward(request, response);
	}

}
