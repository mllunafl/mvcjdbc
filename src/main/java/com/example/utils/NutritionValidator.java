package com.example.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.domain.Nutrition;

@Component
public class NutritionValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Nutrition.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Nutrition nutrition = (Nutrition)target;
		if(nutrition.getCarbs()>nutrition.getCalories()){
			errors.rejectValue("carbs", "carbsGreaterThanCalories", "Carbs can not be greater than Calories");
		}
	}

}
