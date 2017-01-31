package com.example.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Nutrition;
import com.example.service.NutritionService;
import com.example.storage.StorageService;
import com.example.utils.NutritionValidator;

@Controller
public class NutritionController {

	private static final Logger logger = LoggerFactory.getLogger(NutritionController.class);

	@Autowired
	NutritionService nutritionService;

	@Autowired
	NutritionValidator validator;
	
	@Autowired
	StorageService storageService;

	@GetMapping("/view-nutrition/{id}/files")
	public String uploadFile(@PathVariable("id") Integer id, Model model) {
		if (id == 0) {
			throw new RuntimeException("I'll be back");
		}
		model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(NutritionController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
		model.addAttribute("id", id);
		return "upload";
	}
	
	 @GetMapping("/view-nutrition/{id}/files/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
			//model.addAttribute("id", id);
	        Resource file = storageService.loadAsResource(filename);
	        return ResponseEntity
	                .ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
	                .body(file);
	        
	 }
	
	@PostMapping("/view-nutrition/{id}/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,@PathVariable("id") Integer id,Model model,
                                   RedirectAttributes redirectAttributes) {
		model.addAttribute("id", id);
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/view-nutrition/{id}/files/";
    }

	@GetMapping("/nutrition")
	public String nutritionForm(Model model) {
		model.addAttribute("nutrition", new Nutrition());
		return "nutrition";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/logout")
	public String logout() {
		return "/";
	}

	@PostMapping("/login")
	public String nutritions(Model model) {
		model.addAttribute("nutritions", nutritionService.findAll());
		return "nutritions";
	}

	@GetMapping("/nutritions")
	public String nutritionAll(Model model) {
		model.addAttribute("nutritions", nutritionService.findAll());
		return "nutritions";
	}

	@PostMapping("/nutritions")
	public String deleteNutritions(@RequestParam(value = "deleteIds", required = true) List<Long> ids, Model model,
			WebRequest webRequest) {
		System.out.println(ids);
		nutritionService.delete(ids);
		model.addAttribute("nutritions", nutritionService.findAll());
		return "nutritions";
	}

	@PostMapping("/nutrition")
	public String nutritionSubmit(@Valid Nutrition nutrition, BindingResult bindingResult) {
		System.out.println("in submit nutrition" + nutrition);
		if (bindingResult.hasErrors()) {
			System.out.println("error in binding");
			return "nutrition";
		}
		validator.validate(nutrition, bindingResult);
		if (bindingResult.hasErrors()) {
			return "nutrition";
		}

		nutritionService.add(nutrition);
		// 3nutritionService.findAll();
		return "nutr";

	}

	@GetMapping("/nutr")
	public String viewNutr(Nutrition nutrition, Model model) {
		model.addAttribute("nutr", nutritionService.find((int) nutrition.getId()));
		return "nutr";
	}

	@GetMapping("/view-nutrition/{id}")
	public String nutritionView(@PathVariable("id") Integer id, Model model) {
		System.out.println("we got the id!!!" + id);
		if (id == 0) {
			throw new RuntimeException("I'll be back");
		}
		model.addAttribute("nutrition", nutritionService.find(id));
		return "nutr";
	}

	@GetMapping("/edit-nutrition/{id}")
	public String nutritionViewUpdate(@PathVariable("id") Integer id, Model model) {
		System.out.println("get edit nutrition" + id);
		nutritionService.find(id);
		model.addAttribute("nutrition", nutritionService.find(id));
		return "update-nutr";
	}

	@PostMapping("/edit-nutrition/{id}")
	public String nutritionUpdate(Nutrition nutrition, Model model) {
		System.out.println("in nutrition" + nutrition);
		nutritionService.update(nutrition);
		model.addAttribute("nutrition", nutritionService.find((int) nutrition.getId()));
		return "nutr";
	}

	@RequestMapping("/delete-nutrition/{id}")
	public String nutritonDelete(@PathVariable("id") Integer id, Model model) {
		nutritionService.delete(id);
		model.addAttribute("nutritions", nutritionService.findAll());
		return "/nutritions";
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleDefaultErrors(final Exception exception, final HttpServletRequest request,
			final HttpServletResponse resp) {
		logger.warn(exception.getMessage() + "\n" + stackTraceAsString(exception));
		return new ModelAndView("error", "message", exception.getMessage());
	}

	private String stackTraceAsString(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}
}