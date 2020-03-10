package service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import recipe.Recipe;


@Path("recipe")
@Component
@Consumes("application/json")
@Produces("application/json")
public interface RecipeService {
	
	@GET
	public RecipesResponse getRecipes();
	
	@GET
	@Path("/{id}")
	public RecipesResponse getRecipe(@PathParam("id") long id);
	
	
	@POST
	public Response createRecipe(@RequestBody Recipe recipe);
	
	@PUT
	@Path("/{id}")
	public Response updateRecipe(@PathParam("id") long id,@RequestBody Recipe newRecipe);



}
