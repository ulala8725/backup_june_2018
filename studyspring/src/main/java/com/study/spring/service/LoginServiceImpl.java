package com.study.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.spring.dao.LoginDAO;
import com.study.spring.vo.UserVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAO dao;

	@Override
	public UserVO login(UserVO vo) {
		return dao.login(vo);
	}

}
