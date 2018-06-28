package com.study.spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.spring.service.LoginService;
import com.study.spring.vo.UserVO;

/**
 * クラスの説明.
 * <pre>
 * ログインに関する処理を扱うコントローラ
 * </pre>
 * @author kim.sunhye
 */
@Controller
public class LoginController {
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, UserVO vo, HttpServletRequest request, HttpSession session) {
		return "kadai/login";
	}// home
	
	@RequestMapping(value = "/login") // , method = RequestMethod.POST
	public String login(UserVO vo, Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, String autoLogin) {
		
		UserVO result = service.login(vo);
		
		if (result != null) {
			session = request.getSession();
			session.setAttribute("loginId", vo.getId());
			
			if (autoLogin != null && autoLogin.equals("true")) {
				session.setAttribute("autoLogin", "true");
				Cookie cookieId = new Cookie("id", vo.getId());
				Cookie cookiePass = new Cookie("pass", vo.getPass());
				cookieId.setMaxAge(60*10);
				cookiePass.setMaxAge(60*10);
				response.addCookie(cookieId);
				response.addCookie(cookiePass);
			}
			return "kadai/search";
		}
		model.addAttribute("message", "idとpasswordを確認してください");
		return "kadai/login";
	}// login
	
}// class
