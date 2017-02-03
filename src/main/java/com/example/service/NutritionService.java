package com.example.service;

import com.example.common.NutritionCrud;
import com.example.domain.Nutrition;

public interface NutritionService extends NutritionCrud{
	Nutrition addFile(Long id, String fileName);
}
