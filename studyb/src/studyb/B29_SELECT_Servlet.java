package studyb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 入力フォームから送信した内容をDBと連結するサーブレット
 * @author kim.sunhye
 */

//@WebServlet("/B29")
public class B29_SELECT_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public B29_SELECT_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB接続
		B14_DBAccess ac = new B14_DBAccess();
		ac.connect();
		
		//全ての行を取得して表示
		System.out.println("###########全ての行を取得して表示");
		String[][] outArray = ac.selectExec("SELECT * FROM testtable1");
		ArrayList<String> list = new ArrayList<>();
		if (outArray[0][0] != null) {
			for (int i = 0; i < outArray.length; i++) {
				list.add(i+1 + "," + outArray[i][0] + "," + outArray[i][1]);
			}
		} else {
			System.out.println("#出力するデータがありません。");
		}

		for (int i = 0; i < list.size(); i++) {
			System.out.println("#: " + list.get(i));
		}
		
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("B29_SELECT.jsp");
		dispatcher.forward(request, response);
		
		//DB切断
		System.out.println("#DB切断実行");
		ac.disconnect();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
