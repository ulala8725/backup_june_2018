package com.study.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.spring.vo.UserVO;

@Repository
public class SearchDAOImpl implements SearchDAO {
	@Autowired
	private SqlSession sqlsession;
	private SearchMapper mapper;

	@Override
	public UserVO searchUser(UserVO vo) {
		mapper = sqlsession.getMapper(SearchMapper.class);
		return mapper.searchUser(vo);
	}

	@Override
	public int insertUser(UserVO vo) {
		mapper = sqlsession.getMapper(SearchMapper.class);
		return mapper.insertUser(vo);
	}

	@Override
	public int modifyUser(UserVO vo) {
		mapper = sqlsession.getMapper(SearchMapper.class);
		return mapper.modifyUser(vo);
	}

	@Override
	public int deleteUser(String id) {
		mapper = sqlsession.getMapper(SearchMapper.class);
		return mapper.deleteUser(id);
	}

}
