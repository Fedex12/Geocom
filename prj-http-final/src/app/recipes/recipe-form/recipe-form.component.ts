import { Component, OnInit } from '@angular/core';
//import Swal from 'sweetalert2'
import { Recipe } from '../recipe.model';
import {DataStorageService} from "../../shared/data-storage.service";
import {Ingredient} from "../../ingredients/ingredient.model";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent implements OnInit {

  model = new Recipe("","","",[]);

  id:string = 'new';
  ingredient = new Ingredient("",null);

  constructor(private dataSourceService: DataStorageService,
              private route: ActivatedRoute,
              private router: Router) {

    const id = route.snapshot.paramMap.get("id");
    if(id!=null){

      this.dataSourceService.getRecipe(id).subscribe(resp => this.model = resp.json().recipes[0]);
    }
  }

  ngOnInit() {
  }
  addIngredient(){
    if(this.ingredient.name !=null && this.ingredient.amount > 0) {
      this.model.ingredients.push(this.ingredient);
      this.ingredient = new Ingredient("", null);
    }
  }

  createRecipe() {
    if(this.id==='new') {
      this.dataSourceService.addRecipes(this.model).subscribe(resp => {
          this.dataSourceService.getRecipes();
          //Swal.fire(`Se creao receta correctamente. `);
          this.router.navigateByUrl("recipe")
        },
        error => {
          //Swal.fire(`Error al crear Receta: `, error, 'error');
        });
    }else{
      this.dataSourceService.updateRecipes(this.model,this.id).subscribe(resp => {
          this.dataSourceService.getRecipes();
          //Swal.fire(`Se actualizo receta correctamente. `);
          this.router.navigateByUrl("recipe")
        },
        error => {
          //Swal.fire(`Error al actualizar Receta: `, error, 'error');
        });

    }
  }

  deleteIngredient(index:number){
    console.log("delete:"+index)
    this.model.ingredients.splice(index,1)
  }


}
