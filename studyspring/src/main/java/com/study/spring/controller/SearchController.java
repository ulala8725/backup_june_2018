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
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.spring.service.SearchService;
import com.study.spring.vo.UserVO;

/**
 * クラスの説明.
 * <pre>
 * searchに関する処理を扱うコントローラ
 * </pre>
 * @author kim.sunhye
 */
@Controller
@RequestMapping("/search")
public class SearchController {
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private SearchService service;
	
	@RequestMapping(value = "") // , method = RequestMethod.POST
	public String clickButton(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserVO vo, Model model) {
		System.out.println("search/search");
		String submit = request.getParameter("submit");
		if (submit != null) {
			switch (submit) {
			case "新規登録":
				System.out.println("enroll");
				if (vo != null) {
					model.addAttribute("vo", vo);
				}
				return "kadai/enroll";
				
			case "ログアウト":
				System.out.println("logout");
				session.invalidate();
				Cookie[] cookies = request.getCookies();
				for (int i = 0; i < cookies.length; i++) {
					cookies[i].setMaxAge(0); // cookieを削除する。
					response.addCookie(cookies[i]);
				}
				return "kadai/login";
				
			case "更新":
				System.out.println("modify");
				UserVO result = service.searchUser(vo);
				String[] birthArray = result.getBirth().split(" ");
				result.setBirth(birthArray[0]);
				model.addAttribute("vo", result);
				return "kadai/modify";
				
			case "削除":
				System.out.println("delete");
				UserVO resultD = service.searchUser(vo);
				String[] birthArrayD = resultD.getBirth().split(" ");
				resultD.setBirth(birthArrayD[0]);
				model.addAttribute("vo", resultD);
				return "kadai/delete";
			
			case "登録します":
				System.out.println("enroll try");
				model.addAttribute("vo", vo);
				
				return "kadai/enrollCheck";
				
			case "戻る":
				System.out.println("return");
				model.addAttribute("vo", vo);
				return "kadai/search";
				
			case "enrollReturn":
				System.out.println("enrollReturn");
				model.addAttribute("vo", vo);
				return "kadai/enroll";
				
			case "enrollConfirm":
				System.out.println("enrollConfirm");
				model.addAttribute("vo", vo);
				return "forward:search/enroll";
				
			case "更新確認":
				System.out.println("modifyCheck");
				model.addAttribute("vo", vo);
				return "kadai/modifyConfirm";
				
			case "更新処理確認":
				System.out.println("modifyConfirm");
				model.addAttribute("vo", vo);
				return "forward:search/modify";
				
			case "deleteConfirm":
				System.out.println("deleteConfirm");
				String id = request.getParameter("deleteUserId");
				System.out.println("idididi: " + id);
//				request.setAttribute("id", id);
				model.addAttribute("deleteUserId", id);
				return "forward:search/delete";				
			}// switch
		}
		return "kadai/search";
	}// login
	
	@RequestMapping(value ="/search", method = RequestMethod.POST)
	@ResponseBody
	public UserVO searchUser(UserVO vo) {
		System.out.println("searchUser");
		UserVO result = service.searchUser(vo);
		if (result == null) {
			System.out.println("result null");
			return null;
		}
		String[] birthSplit = result.getBirth().split(" ");
		result.setBirth(birthSplit[0]);
		return result;
	}
	
	@RequestMapping(value ="enroll", method = RequestMethod.POST)
	public String enroll(UserVO vo, HttpServletRequest request) {
		System.out.println("enrollDBDBDB");
		int result = service.insertUser(vo);
		if (result == 2) {
			request.setAttribute("message", "登録に成功しました。");
		} else {
			request.setAttribute("message", "登録に失敗しました。");
		}
		return "kadai/search";
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(UserVO vo, HttpServletRequest request) {
		System.out.println("modifyDBDBDB");
		int result = service.modifyUser(vo);
		System.out.println("modify result: " + result);
		if (result == -1 ) { // updateを二回するので、成功するときには-1がリターンされる。
			request.setAttribute("message", "更新に成功しました。");
		} else {
			request.setAttribute("message", "更新に失敗しました。");
		}
		return "kadai/search";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(String deleteUserId, HttpServletRequest request) {
		System.out.println("deleteDBDBDB: " + deleteUserId);
		int result = service.deleteUser(deleteUserId);
		System.out.println("delete result: " + result);
		if (result == -1 ) { 
			request.setAttribute("message", "削除に成功しました。");
		} else {
			request.setAttribute("message", "削除に失敗しました。");
		}
		return "kadai/search";
	}
	
	
}// class
