package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

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
	public String nutritionAll(Model model) {
		model.addAttribute("nutritions", nutritionService.findAll());
		return "nutritions";
	}
	@PostMapping("/nutritions")
	public String deleteNutritions(@RequestParam(value="deleteIds", required=true) List<Long> ids,Model model, WebRequest webRequest) {
		System.out.println(ids);
		nutritionService.delete(ids);
		model.addAttribute("nutritions", nutritionService.findAll());
		return "nutritions";
	}
	

	@PostMapping("/nutrition")
	public String nutritionSubmit(@Valid Nutrition nutrition, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "nutrition";
		}
		nutritionService.add(nutrition);
		model.addAttribute("nutritions", nutritionService.findAll());
		return "nutritions";
	}

	@GetMapping("/nutr")
	public String viewNutr(Nutrition nutrition, Model model) {
		model.addAttribute("nutr", nutritionService.find((int) nutrition.getId()));
		return "nutr";
	}

	@GetMapping("/view-nutrition/{id}")
	public String nutritionView(@PathVariable("id") Integer id, Model model) {
		System.out.println("we got the id!!!" + id);
		model.addAttribute("nutrition",nutritionService.find(id));
		return "nutr";
	}
	
	@GetMapping("/edit-nutrition/{id}")
	public String nutritionViewUpdate(@PathVariable("id") Integer id, Model model){
		System.out.println("get edit nutrition" + id);
		nutritionService.find(id);
		model.addAttribute("nutrition",nutritionService.find(id));
		return "update-nutr";
	}
	
	@PostMapping("/edit-nutrition/{id}")
	public String nutritionUpdate(Nutrition nutrition, Model model){
		System.out.println("in nutrition" + nutrition);
		nutritionService.update(nutrition);
		model.addAttribute("nutrition", nutritionService.find((int) nutrition.getId()));
		return "nutr";
	}
	
	@RequestMapping("/delete-nutrition/{id}")
	public String nutritonDelete(@PathVariable("id") Integer id, Model model){
		nutritionService.delete(id);
		model.addAttribute("nutritions", nutritionService.findAll());
		return "/nutritions";
	}
}
