package studyb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作成したコードをhtmlでウェーブページに表示するサーブレット
 * @author kim.sunhye
 */

@WebServlet("/B24")
public class B24_HelloWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public B24_HelloWeb() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		
		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>kadaiB-24</title></head>");
		out.write("<body>");
		out.write("<table border=\"1\">");
		
		for (int i = 0; i < 10; i++) {
			out.write("<tr>");
			for (int j = 0; j < 10; j++) {
				out.write("<td>" + i + "-" + j);
				out.write("</td>");
			}
			out.write("</tr>");
		}
		
		out.write("</table>");
		out.write("</body></html>");
		out.close();
	}

}
