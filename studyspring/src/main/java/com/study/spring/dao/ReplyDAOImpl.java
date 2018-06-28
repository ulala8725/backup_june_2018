package com.study.spring.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.spring.vo.BoardVO;
import com.study.spring.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Autowired
	private SqlSession sqlSession;
	private ReplyMapper mapper;
	
	@Override
	public ArrayList<ReplyVO> getReplyList(int boardNo, int startRecord, int countPerPage) {
		mapper = sqlSession.getMapper(ReplyMapper.class);
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		return mapper.getReplyList(boardNo, rb);
	}

	@Override
	public int writeReply(ReplyVO vo) {
		mapper = sqlSession.getMapper(ReplyMapper.class);
		return mapper.writeReply(vo);
	}

	@Override
	public int modifyReply(ReplyVO vo) {
		mapper = sqlSession.getMapper(ReplyMapper.class);
		return mapper.modifyReply(vo);
	}

	@Override
	public int deleteReply(ReplyVO vo) {
		mapper =  sqlSession.getMapper(ReplyMapper.class);
		return mapper.deleteReply(vo);
	}

	@Override
	public int getTotalReply(int boardNo) {
		mapper =  sqlSession.getMapper(ReplyMapper.class);
		return mapper.getTotalReply(boardNo);
	}

	
	
}
