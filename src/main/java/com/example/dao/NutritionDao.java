package com.example.dao;

import java.util.List;

import com.example.domain.Nutrition;

public interface NutritionDao {
	
	int add(Nutrition nutrition);
	List<Nutrition> findAll();
	Nutrition find(int id);
	void update(Nutrition nutrition);
	void delete(long id);
	void delete(List<Long> ids);
}
