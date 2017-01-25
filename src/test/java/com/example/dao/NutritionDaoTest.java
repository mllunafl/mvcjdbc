package com.example.dao;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.NutritionCrudTest;

public class NutritionDaoTest extends NutritionCrudTest {

	@Autowired
	NutritionDao nutritionDao;

	@Before
	public void before(){
		nutritionCrud = nutritionDao;
	}

}
