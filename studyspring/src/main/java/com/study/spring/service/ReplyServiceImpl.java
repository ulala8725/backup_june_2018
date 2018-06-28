package com.study.spring.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.spring.dao.BoardDAO;
import com.study.spring.dao.ReplyDAO;
import com.study.spring.util.PageNavigator;
import com.study.spring.vo.BoardVO;
import com.study.spring.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO dao;
	
	private final int countPerPage = 5;
	private final int pagePerGroup = 5;

	@Override
	public ArrayList<ReplyVO> getReplyList(int boardNo, PageNavigator replyNavi) {
		return dao.getReplyList(boardNo, replyNavi.getStartRecord(), replyNavi.getCountPerPage());
	}

	@Override
	public int writeReply(ReplyVO vo) {
		return dao.writeReply(vo);
	}

	@Override
	public int modifyReply(ReplyVO vo) {
		return dao.modifyReply(vo);
	}

	@Override
	public int deleteReply(ReplyVO vo) {
		return dao.deleteReply(vo);
	}

	@Override
	public PageNavigator getNavi(int currentPage, int boardNo) {
		int totalRecordsCount = dao.getTotalReply(boardNo);
		PageNavigator replayNavi = new PageNavigator(countPerPage, pagePerGroup, currentPage, totalRecordsCount);
		return replayNavi;
	}

}
