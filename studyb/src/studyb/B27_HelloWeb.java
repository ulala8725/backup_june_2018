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

@WebServlet("/B27")
public class B27_HelloWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public B27_HelloWeb() {
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
		
		out.write("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>kadaiB-27</title></head>");
		out.write("<body>");
		out.write("<script type=\"text/javascript\" charset=\"utf-8\">\n");
		out.write("function callAlert(){\n");
		out.write("var text = document.getElementById(\"textId\")\n");
		out.write("var form = document.getElementById(\"form\")\n");
		
		out.write("if (text.value.length != 0) {\n");
		
		out.write("form.submit()\n");
		out.write("} else {\n");
		out.write("alert(\"空です。\")\n");
		out.write("return false;\n");
		out.write("}\n");
		out.write("}\n");
		
		out.write("</script>");
		
		out.write("<form method=\"post\" action=\"B27\" id=\"form\">\n");
		out.write("<input type=\"text\" name=\"text\" id=\"textId\" autofocus=\"autofocus\">\n");
		out.write("<input type=\"button\" onclick=\"return callAlert()\" value=\"クエリ送信\">\n");
		out.write("</form>\n");
		
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
		
		out.write("</body></html>");
		out.close();
	}

}
