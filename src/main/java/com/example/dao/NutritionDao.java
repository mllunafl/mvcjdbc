package com.example.dao;

import java.util.List;

import com.example.domain.Nutrition;

public interface NutritionDao {
	
	void add(Nutrition nutrition);
	List<Nutrition> findAll();
}
