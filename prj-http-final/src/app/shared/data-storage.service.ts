import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/Rx';

import { RecipeService } from '../recipes/recipe.service';
import { Recipe } from '../recipes/recipe.model';
import {Ingredient} from '../ingredients/ingredient.model'
import {IngredientsService} from "../ingredients/ingredients.service";
import {RecipesResponse} from '../recipes/recipe.response.model';
import { environment } from 'environments/environment';

@Injectable()
export class DataStorageService {
  constructor(private http: Http, private recipeService: RecipeService,private ingredientService: IngredientsService) {}


  getRecipes() {
    this.http.get(`${environment.url}recipe/`)
      .map(
        (response: Response) => {
          const recipesResponse: RecipesResponse = response.json();
          const recipes = recipesResponse.recipes;
          for (const recipe of recipesResponse.recipes) {
            if (!recipe['ingredients']) {
              recipe['ingredients'] = [];
            }
          }
          return recipes;
        }
      )
      .subscribe(
        (recipes: Recipe[]) => {
          this.recipeService.setRecipes(recipes);
        }
      );
  }


  addRecipes( recipe:Recipe){

    //return null;
    return this.http.post(`${environment.url}recipe/`,recipe);

  }

  updateRecipes( recipe:Recipe,id:string){

    //return null;
    return this.http.put(`${environment.url}recipe/${id}`,recipe);

  }

  getRecipe( id:string){

    //return null;
    return this.http.get(`${environment.url}recipe/${id}`);

  }

  getIngredients(){

    //return null;
    return this.http.get(`${environment.url}ingredient/`).map(
      (response: Response) => {
        const ingredientsResponse = response.json();
        const ingredients = ingredientsResponse.ingredient;

        return ingredients;
      }
    )
      .subscribe(
        (ingredient: Ingredient[]) => {
          this.ingredientService.setIngredients(ingredient);
        }
      ); ;

  }
}
