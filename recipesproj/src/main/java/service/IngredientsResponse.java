package service;

import java.util.ArrayList;
import java.util.List;

import recipe.Ingredient;


public class IngredientsResponse extends Response{

	private List<Ingredient> ingredients;

	public List<Ingredient> getIngredient() {
		if(ingredients==null)
			ingredients= new ArrayList<Ingredient>();
		return ingredients;
	}

	public void setIngredient(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
