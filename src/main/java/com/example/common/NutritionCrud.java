package com.example.common;

import java.util.List;

import com.example.domain.Nutrition;

public interface NutritionCrud {
	Nutrition add(Nutrition nutrition);
	List<Nutrition> findAll();
	Nutrition find(long id);
	void update(Nutrition nutrition);
	void delete(long id);
	void delete(List<Long> ids);
}
