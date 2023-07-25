package com.example.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RecipesList {
    private List<Recipe> recipes;

    public List<String> getRecipesNames(){
        return this.recipes.stream()
                .map(recipe -> recipe.getRecipeName())
                .collect(Collectors.toList());
    }
    public void addRecipe(Recipe newRecipe){
        if(!recipes.contains(newRecipe)) {
            this.recipes.add(newRecipe);
        }
    }
    public void remove(Recipe recipe){
        this.recipes.remove(recipe);
    }
    public Recipe getRecipe(String recipeName){
        for(int i = 0; i < this.recipes.size(); i++){
            if(this.recipes.get(i).getRecipeName().equals(recipeName)){
                return this.recipes.get(i);
            }
        }
        return null;
    }
}
