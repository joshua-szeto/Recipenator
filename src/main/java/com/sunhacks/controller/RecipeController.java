package com.sunhacks.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunhacks.service.RecipeService;

@RestController()
public class RecipeController {

	@Autowired
	RecipeService recipeService;
	
	@GetMapping("/generate")
	public String generateRecipe(File ingredientFile) {
		// TODO: generates the recipe list
		return recipeService.generateRecipe(ingredientFile);
	}
}