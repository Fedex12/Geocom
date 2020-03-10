import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

import {DataStorageService} from "../../shared/data-storage.service";
import {Ingredient} from "../ingredient.model";
import {IngredientsService} from "../ingredients.service";

@Component({
  selector: 'app-ingredients-list',
  templateUrl: './ingredients-list.component.html',
  styleUrls: ['./ingredients-list.component.css']
})
export class IngredientsListComponent implements OnInit {

  ingredients: Ingredient[]=[];
  subscription: Subscription;

  constructor(private dataSourceService: DataStorageService, private ingredientService:IngredientsService) {

  }

  ngOnInit() {

    this.subscription = this.ingredientService.ingredientsChanged
      .subscribe(
        (ingredients: Ingredient[]) => {
          this.ingredients = ingredients;
          console.log(ingredients);
        }
      );
    this.ingredients = this.ingredientService.getIngredients();
    this.dataSourceService.getIngredients();
 //   console.log(this.ingredients);

  }

}
