package com.study.spring.dao;

import com.study.spring.vo.UserVO;

public interface SearchMapper {
	public UserVO searchUser(UserVO vo);

	public int insertUser(UserVO vo);

	public int modifyUser(UserVO vo);

	public int deleteUser(String id);

}
