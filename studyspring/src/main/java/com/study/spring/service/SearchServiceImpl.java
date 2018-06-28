package com.study.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.spring.dao.SearchDAO;
import com.study.spring.vo.UserVO;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDAO dao;

	@Override
	public UserVO searchUser(UserVO vo) {
		return dao.searchUser(vo);
	}

	@Override
	public int insertUser(UserVO vo) {
		return dao.insertUser(vo);
	}

	@Override
	public int modifyUser(UserVO vo) {
		return dao.modifyUser(vo);
	}

	@Override
	public int deleteUser(String id) {
		return dao.deleteUser(id);
	}

}
