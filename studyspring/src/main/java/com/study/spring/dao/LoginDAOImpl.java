package com.study.spring.dao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.spring.vo.UserVO;

@Repository
public class LoginDAOImpl implements LoginDAO{
	@Autowired
	private SqlSession sqlSession;
	private LoginMapper mapper;

	@Override
	public UserVO login(UserVO vo) {
		mapper = sqlSession.getMapper(LoginMapper.class);
		return mapper.login(vo);
	}

}
