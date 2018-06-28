package com.studyspring.dogshop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyspring.dogshop.dao.DogDAO;
import com.studyspring.dogshop.vo.Dog;

@Service
public class DogServiceImpl implements DogService{
	@Autowired
	private DogDAO dao;
	
	@Override
	public ArrayList<Dog> getDogList(Dog vo) {
		ArrayList<Dog> result = dao.getDogList(vo);
		if (vo.getId() != 0 && result.size() == 1) {
			dao.addHit(vo);
			result.get(0).setReadcount(result.get(0).getReadcount()+1);
		}
		return result;
	}

}
