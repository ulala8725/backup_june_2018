package com.study.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.spring.service.BoardService;
import com.study.spring.service.ReplyService;
import com.study.spring.util.PageNavigator;
import com.study.spring.vo.BoardVO;
import com.study.spring.vo.ReplyVO;
import com.study.spring.vo.UserVO;

/**
 * クラスの説明.
 * <pre>
 * 掲示板に関する処理を扱うコントローラ
 * </pre>
 * @author kim.sunhye
 */
@Controller
@RequestMapping("/board")
public class BoardController {
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private BoardService service;
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String board(Model model, UserVO vo, HttpServletRequest request, HttpSession session) {
		String submit = request.getParameter("submit");
		
		switch (submit) {
		case "掲示板":
			System.out.println("go to board");
			return "forward:board/gotoboard";

		default:
			break;
		}
		
		return "kadai/board";
	}// board
	
	@RequestMapping(value = "gotoboard")
	public String gotoboard (Model model, BoardVO vo,
			@ModelAttribute("message") String message,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="searchKeyword", defaultValue="") String searchKeyword,
			String searchOption, Map<String, String> map) {
		System.out.println("gotoboard");
		vo.setNo(0);//contentsを除いて検索する場合
		//paging + search
		map.put("searchOption", searchOption);
		map.put("searchKeyword", searchKeyword);
		//全体のポスティングの数(daoで処理）を使ってPageNavigatorオブジェクトを生成する。(serviceで処理）
		PageNavigator navi = service.getNavi(currentPage, map);
		ArrayList<BoardVO> list = service.getList(vo,navi,map, null);
		if (list.size() == 0) {
			list = null;
		} 
		model.addAttribute("list", list);
		model.addAttribute("navi", navi);
		model.addAttribute("searchOption", searchOption);
		model.addAttribute("searchKeyword", searchKeyword);
		return "kadai/board";
	}
		
	@RequestMapping(value = "/readBoard")
	public String readBoard (Model model, BoardVO vo,
						@ModelAttribute("message") String message,
						@RequestParam(value="startFrom", defaultValue="") String startFrom,
						RedirectAttributes rttr,
						@RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		
		System.out.println("readBoard message: " + message);
		System.out.println("startFrom: " + startFrom);
		
		ArrayList<BoardVO> resultVo = service.getList(vo, null, null, startFrom);		// ポスティングの内容
		PageNavigator replyNavi = replyService.getNavi(currentPage, vo.getNo());		//　コメントのページング
		ArrayList<ReplyVO> replyVo = replyService.getReplyList(vo.getNo(), replyNavi);	//　コメントのリスト
		
		if (replyVo.isEmpty()) {
			replyVo = null;
		}
		
		System.out.println("start: " + replyNavi.getStartPageGroup());
		System.out.println("end: " + replyNavi.getEndPageGroup());
		
		model.addAttribute("vo", resultVo.get(0));
		model.addAttribute("replyVo", replyVo);
		model.addAttribute("replyNavi", replyNavi);
		
		startFrom = "";
		rttr.addFlashAttribute(startFrom, startFrom);
		
		return "kadai/readBoard";
	}
	
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadFile (BoardVO vo, HttpServletResponse response) {
		System.out.println("downloadFile");
		ArrayList<BoardVO> list = service.getList(vo, null, null, "");
		service.downloadFile(list.get(0), response);
	}
	
	@RequestMapping(value = "/modifyBoard", method = RequestMethod.POST)
	public String modifyBoard (Model model, BoardVO vo, RedirectAttributes rttr) {
		System.out.println("modifyBoard: " + vo);
		int result = service.modifyBoard(vo);
		if (result == 1) {
			ArrayList<BoardVO> resultVo = service.getList(vo, null, null, null);
			model.addAttribute("vo", resultVo.get(0));
			rttr.addFlashAttribute("message", "修正に成功しました。");
		} else rttr.addFlashAttribute("message", "修正に失敗しました。");
		return "redirect:readBoard?no="+vo.getNo();
	}
	
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard (int no, Model model, RedirectAttributes rttr) {
		System.out.println("deleteBoard : " + no);
		int result = service.deleteBoard(no);
		System.out.println("delete result: " + result);
		if (result == 1) {
			rttr.addFlashAttribute("message", "削除に成功しました。");
		} else rttr.addFlashAttribute("message", "削除に失敗しました。");
		return "redirect:gotoboard";
	}
	
	@RequestMapping(value = "gotowrite", method = RequestMethod.GET)
	public String gotowrite() {
		System.out.println("gotowrite");
		return "kadai/writeBoard";
	}
	
	@RequestMapping(value = "writeBoard", method = RequestMethod.POST)
	public String writeBoard(BoardVO vo, RedirectAttributes rttr,
					 MultipartFile uploadFile) { // , MultipartFile uploadFile
		System.out.println("writeBoard: " + vo);
		
		if (vo.getTitle() == null || vo.getContents() == null) {
			rttr.addFlashAttribute("message", "作成を取り消しました。");
			
		} else {
			if (!uploadFile.isEmpty()) {
				String save_file = uploadFile.getOriginalFilename();
				String save_file_sys = UUID.randomUUID().toString();
				try {
					uploadFile.transferTo(new File(
							"C:/Users/kim.sunhye/Desktop/JAVA/Spring/saveFileFolder/" + save_file_sys));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				vo.setSave_file(save_file);
				vo.setSave_file_sys(save_file_sys);
			}
			int result = service.writeBoard(vo);
			System.out.println("write result: " + result);
			if (result == 1) {
				rttr.addFlashAttribute("message", "作成に成功しました。");
			} else rttr.addFlashAttribute("message", "作成に失敗しました。");
		}
		
		return "redirect:gotoboard";
	}
	
}// class
