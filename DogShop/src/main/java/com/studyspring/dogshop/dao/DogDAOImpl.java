package com.studyspring.dogshop.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyspring.dogshop.vo.Dog;

@Repository
public class DogDAOImpl implements DogDAO{
	@Autowired
	private SqlSession sqlSession;
	private DogMapper mapper;
		
	@Override
	public ArrayList<Dog> getDogList(Dog vo) {
		mapper = sqlSession.getMapper(DogMapper.class);
		return mapper.getDogList(vo);
	}

	@Override
	public void addHit(Dog vo) {
		mapper = sqlSession.getMapper(DogMapper.class);
		mapper.addHit(vo);
	}


}
