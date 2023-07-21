package com.example.domain;

import java.util.*;

public class Recipe
{

    private IngredientListWithAmounts ingredientList;
    private List<String> recipeSteps;
    private int prepTime;
    private String description;

    public Recipe(){
        this.ingredientList = new IngredientListWithAmounts();
        this.recipeSteps = new ArrayList<>();
        this.prepTime = 0;
        this.description = "";
    }

    public Set<String> getIngredients(){
        return this.ingredientList.getIngredients();
    }

    public int getIngredientAmount(String ingredient){
        return this.ingredientList.getIngredientAmount(ingredient);
    }

    public List<String> getRecipeSteps() {
        return recipeSteps;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public String getDescription() {
        return description;
    }

    public void setIngredientList(IngredientListWithAmounts ingredientList){
        this.ingredientList = ingredientList;
    }

    public void setRecipeSteps(List<String> recipeSteps)
    {
        this.recipeSteps = recipeSteps;
    }

    public void setPrepTime(int prepTime)
    {
        this.prepTime = prepTime;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
