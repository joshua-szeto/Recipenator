package com.sunhacks.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sunhacks.model.RecipeModel;
import com.sunhacks.service.RecipeService;

@RestController()
public class RecipeController {

	@Autowired
	RecipeService recipeService;
	
	@GetMapping("/generate")
	public List<RecipeModel> generateRecipe(@RequestParam MultipartFile ingredientFile) throws IOException {
		// TODO: generates the recipe list
		return recipeService.generateRecipe(ingredientFile);
	}
}