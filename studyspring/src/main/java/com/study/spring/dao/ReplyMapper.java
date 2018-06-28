package com.study.spring.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.study.spring.vo.BoardVO;
import com.study.spring.vo.ReplyVO;

public interface ReplyMapper {
	public ArrayList<ReplyVO> getReplyList(int boardNo, RowBounds rb);

	public int writeReply(ReplyVO vo);

	public int modifyReply(ReplyVO vo);

	public int deleteReply(ReplyVO vo);

	public int getTotalReply(int boardNo);
}
