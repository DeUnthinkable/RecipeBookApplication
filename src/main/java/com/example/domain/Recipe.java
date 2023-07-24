package com.example.domain;

import java.util.*;

public class Recipe
{
    private String recipeName;
    private IngredientListWithAmounts ingredientListWithAmounts;
    private List<String> recipeSteps;
    private int prepTime;
    private String description;

    public Recipe(){
        this.recipeName = "";
        this.ingredientListWithAmounts = new IngredientListWithAmounts();
        this.recipeSteps = new ArrayList<>();
        this.prepTime = 0;
        this.description = "";
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public IngredientListWithAmounts getIngredientsList(){
        return this.ingredientListWithAmounts;
    }

    public List<String> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<String> recipeSteps)
    {
        this.recipeSteps = recipeSteps;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime)
    {
        this.prepTime = prepTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
