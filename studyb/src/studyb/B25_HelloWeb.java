package studyb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 入力フォームから送信した内容を表示するサーブレット
 * @author kim.sunhye
 */

@WebServlet("/B25")
public class B25_HelloWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public B25_HelloWeb() {
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
		out.write("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>kadaiB-25</title></head>");
		out.write("<body>");
		out.write("<form method=\"post\">");
		out.write("<input type=\"text\" name=\"text\">");
		out.write("<button type=\"summit\">クエリ送信</button>");
		
		String text = request.getParameter("text");
		
		if(text != null) {
			out.write("<p>" + text + "</p>");
		}
		out.write("</form>");
		out.write("</body></html>");
		out.close();
	}

}
