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

@WebServlet("/B23")
public class B23_HelloWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public B23_HelloWeb() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		
		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>kadaiB-23</title></head>");
		out.write("<body>");
		out.write("<p style=\"font-size:20pt; color:blue;\">HelloServlet</p>");
		out.write("</body></html>");
		out.close();
	}

}
