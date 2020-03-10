package datasource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import recipe.Ingredient;
import recipe.Recipe;


public class RecipeDatasourceImpl {
	

	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(Recipe recipe) {
		entityManager.persist(recipe);
		return recipe.getId();
	}

	public Recipe find(long id) {
		return entityManager.find(Recipe.class, id);
	}
	
	public List<Recipe> findAll() {
		Query query = entityManager.createQuery(
				"SELECT r FROM Recipe r",  Recipe.class);
		return query.getResultList();
	}
	
	public RecipeDatasourceImpl() {
	
	}
	
	
	public List<Ingredient> listAllIngredient() {
		List<Ingredient> response = new ArrayList<Ingredient>();
		
		Query query = entityManager.createQuery(
				"SELECT name, sum(amount) FROM Ingredient GROUP BY name");
		List<Object[]> result = query.getResultList();
		for (Object[] object : result) {
			Ingredient ingredient = new Ingredient();
			ingredient.setName((String)object[0]);
			ingredient.setAmount((double) object[1]);
			response.add(ingredient);
		}
		return response;

	}
		
	
}
