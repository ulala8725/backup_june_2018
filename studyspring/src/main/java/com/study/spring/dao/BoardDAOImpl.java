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
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	private BoardMapper mapper;
	
	@Override
	public ArrayList<BoardVO> getList(BoardVO vo) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.getList(vo);
	}

	@Override
	public void addHit(int no) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.addHit(no);
		
	}

	@Override
	public int modifyBoard(BoardVO vo) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.modifyBoard(vo);
	}

	@Override
	public int deleteBoard(int no) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.deleteBoard(no);
	}

	@Override
	public int writeBoard(BoardVO vo) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.writeBoard(vo);
	}

	@Override
	public int getTotalBoard(Map<String, String> map) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.getTotalBoard(map);
	}

	@Override
	public ArrayList<BoardVO> getBoardList(BoardVO vo, int startRecord, int countPerPage, Map<String, String> map) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		//startRecordからcountPerPageほど持ってくる
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		return mapper.getBoardList(map, rb);
	}

	@Override
	public ArrayList<ReplyVO> getReplyList(BoardVO vo) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.getReplyList(vo);
	}

	@Override
	public int writeReply(ReplyVO vo) {
		mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.writeReply(vo);
	}

	
	
}
