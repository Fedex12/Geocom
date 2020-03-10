package service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import datasource.RecipeDatasource;
import recipe.Ingredient;
import recipe.Recipe;

public class IngredientServiceImpl implements IngredientService{
	
	private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
	
	@Autowired
	private RecipeDatasource datasource;

	@Override
	public IngredientsResponse getIngredients() {
		IngredientsResponse response = new IngredientsResponse();
		response.setCode("200");
		response.setDescription("ok");
		
		List<Ingredient> ingredients = datasource.listAllIngredient();
		response.setIngredient(ingredients);
		
		return response;
	}
	

}
