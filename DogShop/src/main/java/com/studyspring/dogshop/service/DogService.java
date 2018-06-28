package com.studyspring.dogshop.service;

import java.util.ArrayList;

import com.studyspring.dogshop.vo.Dog;

public interface DogService {

	ArrayList<Dog> getDogList(Dog vo);

}
