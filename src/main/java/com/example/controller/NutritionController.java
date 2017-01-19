package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.Nutrition;
import com.example.service.NutritionService;


@Controller
public class NutritionController {

	@Autowired
	NutritionService nutritionService;
	
	 @GetMapping("/nutrition")
	    public String nutritionForm(Model model) {
	        model.addAttribute("nutrition", new Nutrition());
	        return "nutrition";
	    }

	 @GetMapping("/nutritions")
	 	public String nutritionAll(Model model){
		 model.addAttribute("nutritions", nutritionService.findAll());
		 return "nutritions";
	 }
	 
	    @PostMapping("/nutrition")
	    public String nutritionSubmit(Nutrition nutrition, Model model) {
	    	nutritionService.add(nutrition);
			 model.addAttribute("nutritions", nutritionService.findAll());
	    	return "nutritions";
	    }
}
