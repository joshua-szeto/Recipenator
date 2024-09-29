package com.sunhacks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RecipinatorConfig {

	@Value("${api.recipe-search.base-url}")
	private String recipeSearchUrl;
	
	@Bean
	WebClient initializeWebClient() {
		final int size = 16 * 1024 * 1024;
		final ExchangeStrategies strategies = ExchangeStrategies.builder()
		        .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
		        .build();
		
		return WebClient.builder().exchangeStrategies(strategies).baseUrl(recipeSearchUrl).build();
	}
}
