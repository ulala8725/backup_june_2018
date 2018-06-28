package com.study.spring.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.study.spring.util.PageNavigator;
import com.study.spring.vo.BoardVO;

public interface BoardService {
	public ArrayList<BoardVO> getList(BoardVO vo, PageNavigator navi, Map<String, String> map, String startFrom);

	public int modifyBoard(BoardVO vo);

	public int deleteBoard(int no);

	public int writeBoard(BoardVO vo);

	public PageNavigator getNavi(int currentPage, Map<String, String> map);

	public void downloadFile(BoardVO boardVO, HttpServletResponse response);


}
