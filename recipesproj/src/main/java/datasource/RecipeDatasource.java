package datasource;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import recipe.Ingredient;
import recipe.Recipe;

@Repository
public interface RecipeDatasource extends CrudRepository<Recipe, Long>{

	@Override
	Iterable<Recipe> findAll();
	public List<Ingredient> listAllIngredient() ;
	
	
	
	
}
