package com.example.common;

import java.util.List;

import com.example.domain.Nutrition;

public interface NutritionCrud {
	int add(Nutrition nutrition);
	List<Nutrition> findAll();
	Nutrition find(int id);
	void update(Nutrition nutrition);
	void delete(long id);
	void delete(List<Long> ids);
<<<<<<< HEAD
}
=======
}
>>>>>>> 01f244a60d2d5ebde693c26555864c7aa56682db
