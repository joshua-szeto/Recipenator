package com.sunhacks.model;

import java.util.List;

import lombok.Data;

@Data
public class RecipeSearchResult {

	private List<RecipeHit> hits;
}
