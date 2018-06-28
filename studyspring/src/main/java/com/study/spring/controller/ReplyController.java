package com.study.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.spring.service.ReplyService;
import com.study.spring.util.PageNavigator;
import com.study.spring.vo.ReplyVO;

/**
 * クラスの説明.
 * <pre>
 * 掲示板に関する処理を扱うコントローラ
 * </pre>
 * @author kim.sunhye
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ReplyService service;
	
//	@RequestMapping(value = "getReplyList", method = RequestMethod.POST)
//	@ResponseBody
//	public ArrayList<ReplyVO> getReplyList(ReplyVO vo) {
//		System.out.println("getReplyList");
//		return service.getReplyList(vo);
//	}
	
//	@RequestMapping(value = "getReplyListPage")
//	@ResponseBody
//	public PageNavigator getReplyListPage(ReplyVO vo, 
//					@RequestParam(value="currentPage", defaultValue="1") int currentPage) {
//		System.out.println("getReplyListPage");
//		return service.getNavi(currentPage);
//	}
	
	@RequestMapping(value = "writeReply", method = RequestMethod.POST)
	public String writeReply(ReplyVO vo, RedirectAttributes rttr, String replyWriteId, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		vo.setId(loginId);
		System.out.println("writeReply: " + vo);
		int result = service.writeReply(vo);
		System.out.println("reply result: " + result);
		if (result == 1) { 
			rttr.addFlashAttribute("message", "コメント作成に成功しました。");
		} else rttr.addFlashAttribute("message", "コメント作成に失敗しました。");
		return "redirect:/board/readBoard?no=" + vo.getBoardNo();
	}
	
	@RequestMapping(value = "modifyReply", method = RequestMethod.POST)
	@ResponseBody
	public int modifyReply(ReplyVO vo) {
		System.out.println("modifyReply: " + vo);
		int result = service.modifyReply(vo);
		return result;
	}
	
	@RequestMapping(value = "deleteReply", method = RequestMethod.POST)
	@ResponseBody
	public int deleteReply(ReplyVO vo) {
		System.out.println("deleteReply: " + vo);
		int result = service.deleteReply(vo);
		return result;
	}
	
}// class
