package com.study.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.study.spring.vo.UserVO;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		System.out.println("interceptor");
		HttpSession session =request.getSession(true);
		String loginId = (String) session.getAttribute("loginId");
		
		// 2.最初に接続したとき
		if (loginId == null) {
			loginId = request.getParameter("id");
		}
		
		// 1.自動ログイン設定如何の確認
		Cookie[] cookies = request.getCookies();
		UserVO vo = new UserVO();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("id")) {
					vo.setId(cookies[i].getValue());
				}
				if (cookies[i].getName().equals("cookiePass")) {
					vo.setPass(cookies[i].getValue());
				}
			}
			if (vo.getId() != null) {
				session.setAttribute("loginId", vo.getId());
				return true;
			}
		}

		// 3.sessionがない時
		if (session == null || loginId == null) {
			response.sendRedirect("/spring/"); 
			return false;
		}
		
		return true;
		
	}

}
