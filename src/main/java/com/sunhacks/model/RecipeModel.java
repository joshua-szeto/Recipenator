package com.sunhacks.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeModel {

	private String recipe;
	private Integer calories;
	private String url;
}
