package studyb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 入力フォームから送信した内容を表示するサーブレット
 * @author kim.sunhye
 */

@WebServlet("/B26")
public class B26_HelloWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public B26_HelloWeb() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		request.setCharacterEncoding("utf8");
		
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>kadaiB-26</title></head>");
		out.write("<body>");
		out.write("<form method=\"post\" action=\"B26\">");
		out.write("<input type=\"text\" name=\"text\" autofocus=\"autofocus\">");
		out.write("<button type=\"submit\">クエリ送信</button>");
		
		HttpSession session = request.getSession(false); 

		String text = request.getParameter("text");
		
		if (session == null){
			session = request.getSession(true);
			session.setAttribute("text", "");
		}
		
		if (text != null) {
			session.setAttribute("text", (String)session.getAttribute("text") + "\n" + text);
			if ((String)session.getAttribute("text") != null) {
				out.write("<pre>" + (String)session.getAttribute("text") + "</pre>");
			}
		}
		
		out.write("</form>");
		out.write("</body></html>");
		out.close();
	}

}
