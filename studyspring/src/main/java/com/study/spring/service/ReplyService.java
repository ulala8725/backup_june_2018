package com.study.spring.service;

import java.util.ArrayList;

import com.study.spring.util.PageNavigator;
import com.study.spring.vo.ReplyVO;

public interface ReplyService {
	public ArrayList<ReplyVO> getReplyList(int boardNo, PageNavigator replyNavi);

	public int writeReply(ReplyVO vo);

	public int modifyReply(ReplyVO vo);

	public int deleteReply(ReplyVO vo);

	public PageNavigator getNavi(int currentPage, int boardNo);

}
