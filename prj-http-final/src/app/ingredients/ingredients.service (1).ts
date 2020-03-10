import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

import { Ingredient } from './ingredient.model';


@Injectable()
export class IngredientsService {
  ingredientsChanged = new Subject<Ingredient[]>();

  private ingredients: Ingredient[] = [];

  constructor() {}

  setIngredients(ingredients: Ingredient[]) {
    this.ingredients = ingredients;
    this.ingredientsChanged.next(this.ingredients.slice());
  }

  getIngredients() {
    return this.ingredients.slice();
  }

}
