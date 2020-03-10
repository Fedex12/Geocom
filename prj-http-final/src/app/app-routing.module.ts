import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RecipesComponent } from './recipes/recipes.component';
import { RecipeStartComponent } from './recipes/recipe-start/recipe-start.component';
import { RecipeDetailComponent } from './recipes/recipe-detail/recipe-detail.component';
import {RecipeFormComponent} from "./recipes/recipe-form/recipe-form.component";
import {IngredientsListComponent} from "./ingredients/ingredients-list/ingredients-list.component";


const appRoutes: Routes = [
  { path: '', redirectTo: '/recipes', pathMatch: 'full' },
  { path: 'recipes', component: RecipesComponent, children: [
    { path: '', component: RecipeStartComponent },
    { path: ':id', component: RecipeDetailComponent },
  ] },
  { path: 'ingredients', component: IngredientsListComponent},
  { path: 'recipes/form', component: RecipeFormComponent},
  { path: 'recipes/form/:id', component: RecipeFormComponent},
  {path: '**', pathMatch:'full',redirectTo: '/recipes'}

];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
