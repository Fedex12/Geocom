package service;

import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import datasource.RecipeDatasource;
import recipe.Ingredient;
import recipe.Recipe;

public class RecipeServiceImpl implements RecipeService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
	
	@Autowired
	private RecipeDatasource datasource;
	
	@Override
	public RecipesResponse getRecipes() {
		
		RecipesResponse response = new RecipesResponse();
		response.setCode("200");
		response.setDescription("ok");
		
		Iterable<Recipe> recipes = datasource.findAll();
		for (Iterator iterator = recipes.iterator(); iterator.hasNext();) {
			Recipe recipe = (Recipe) iterator.next();
			response.getRecipes().add(recipe);
		}
		
		return response;
	}
	
	@Override
	public RecipesResponse getRecipe(long id) {
		RecipesResponse response = new RecipesResponse();
		response.setCode("200");
		response.setDescription("ok");
		
		Recipe recipe = datasource.findOne(id);
		if(recipe==null) {
			response.setCode("404");
			response.setDescription("Recipe not Found");
		}else{
			response.getRecipes().add(recipe);
		}
		
		return response;
	}
	
	@Override
	public Response createRecipe(Recipe recipe) {
		
		Response response = new Response();
		response.setCode("201");
		response.setDescription("ok");
		
		
		datasource.save(recipe);
		
		return response;
	}

	public RecipeDatasource getDatasource() {
		return datasource;
	}

	public void setDatasource(RecipeDatasource datasource) {
		this.datasource = datasource;
	}

	@Override
	public Response updateRecipe(long id,Recipe newRecipe) {
		Response response = new Response();
		response.setCode("200");
		response.setDescription("Recipe Updated");
		
		if(!datasource.exists((long)id)) {
			response.setCode("404");
			response.setDescription("Recipe not Found");
		}
		Recipe recipe = datasource.findOne((long)id);
		if(newRecipe.getName()!=null) {
			recipe.setName(newRecipe.getName());
		}
		if(newRecipe.getDescription()!=null) {
			recipe.setDescription(newRecipe.getDescription());
		}
		if(newRecipe.getImagePath()!=null) {
			recipe.setImagePath(newRecipe.getImagePath());
		}
		if(newRecipe.getIngredients()!=null) {
			recipe.setIngredients(newRecipe.getIngredients());
		}
		datasource.save(recipe);
		
		return response;
	}



}
