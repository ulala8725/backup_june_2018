package com.study.spring.dao;

import java.util.ArrayList;
import java.util.Map;

import com.study.spring.util.PageNavigator;
import com.study.spring.vo.BoardVO;
import com.study.spring.vo.ReplyVO;

public interface ReplyDAO {

	ArrayList<ReplyVO> getReplyList(int boardNo, int startRecord, int countPerPage);

	int writeReply(ReplyVO vo);

	int modifyReply(ReplyVO vo);

	int deleteReply(ReplyVO vo);

	int getTotalReply(int boardNo);

}
