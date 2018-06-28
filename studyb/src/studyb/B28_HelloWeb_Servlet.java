package studyb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 入力フォームから送信した内容を表示するサーブレット
 * @author kim.sunhye
 */

//@WebServlet("/B28")
public class B28_HelloWeb_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public B28_HelloWeb_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf8");
		
		HttpSession session = request.getSession(false);
		String text = request.getParameter("text");
		
		if (session == null){
			session = request.getSession(true);
			session.setAttribute("text", "");
		}
		
		if (text != null) {
			session.setAttribute("text", (String)session.getAttribute("text") + "\n" + text);
		}
		
		request.setAttribute("text", (String)session.getAttribute("text"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("B28_HelloWeb_main.jsp");
		dispatcher.forward(request, response);
	}

}
