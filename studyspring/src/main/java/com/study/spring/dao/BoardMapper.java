package com.study.spring.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.study.spring.vo.BoardVO;
import com.study.spring.vo.ReplyVO;

public interface BoardMapper {
	public ArrayList<BoardVO> getList(BoardVO vo);

	public void addHit(int no);

	public int modifyBoard(BoardVO vo);

	public int deleteBoard(int no);

	public int writeBoard(BoardVO vo);

	public int getTotalBoard(Map<String, String> map);

	public ArrayList<BoardVO> getBoardList(Map<String, String> map, RowBounds rb);

	public ArrayList<ReplyVO> getReplyList(BoardVO vo);

	public int writeReply(ReplyVO vo);
}
