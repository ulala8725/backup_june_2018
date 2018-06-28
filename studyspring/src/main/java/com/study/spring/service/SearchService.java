package com.study.spring.service;

import com.study.spring.vo.UserVO;

public interface SearchService {
	public UserVO searchUser(UserVO vo);

	public int insertUser(UserVO vo);

	public int modifyUser(UserVO vo);

	public int deleteUser(String id);

}
