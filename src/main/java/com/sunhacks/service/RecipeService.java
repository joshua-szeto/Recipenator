package com.sunhacks.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.sunhacks.model.RecipeModel;
import com.sunhacks.model.RecipeSearchResult;

@Service
public class RecipeService {
	
	@Value("${api.recipe-search.app-key}")
	private String recipeSearchAppKey;
	
	@Value("${api.recipe-search.base-url}")
	private String recipeSearchUrl;
	
	@Value("${api.recipe-search.app-id}")
	private String recipeSearchAppId;
	
	@Autowired
	WebClient webClient;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public List<RecipeModel> generateRecipe( MultipartFile ingredientFile) throws IOException {
		// TODO: parse ingredient file
		// TODO: make recipe API call
		// TODO: return recipe
		String delimiter = ",";
		InputStream inputStream = ingredientFile.getInputStream();
		String fileContent = new String(inputStream.readAllBytes());
		//Convert csv to array list
        String[] foodArray = fileContent.split(delimiter);
        List<String> foodList = Arrays.asList(foodArray);
        RecipeSearchResult recipeSearchResult = getRecipes(foodList);
        List<RecipeModel> recipeList = recipeSearchResult.getHits().stream()
        		.map(recipe -> new RecipeModel(recipe.getRecipe().getLabel(),(int) recipe.getRecipe().getCalories(),recipe.getRecipe().getUri()))
        .collect(Collectors.toList());
        return recipeList;		
	}
	
	public RecipeSearchResult getRecipes(List<String> ingredientList) {
		
		logger.info("list: {}", ingredientList);
		String ingredients = String.join(",", ingredientList.toArray(new String[0]));
		logger.info("ingredients: {}", ingredients);
		logger.info("Calling {}",recipeSearchUrl);
		RecipeSearchResult response = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/recipes/v2/")
				.queryParam("app_id", recipeSearchAppId)
				.queryParam("app_key", recipeSearchAppKey)
				.queryParam("q", ingredients)
				.queryParam("type", "any") 	
				.build()).exchangeToMono(r -> r.bodyToMono(RecipeSearchResult.class)).block();
		return response;
	}
}
