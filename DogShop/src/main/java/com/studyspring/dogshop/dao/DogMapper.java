package com.studyspring.dogshop.dao;

import java.util.ArrayList;

import com.studyspring.dogshop.vo.Dog;

public interface DogMapper {

	ArrayList<Dog> getDogList(Dog vo);

	void addHit(Dog vo);
	
}
