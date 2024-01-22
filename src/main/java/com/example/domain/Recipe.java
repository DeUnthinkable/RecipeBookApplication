package com.example.domain;

import java.util.*;

public class Recipe
{
    private String recipeName;
    private List<String> ingredientList;
    private List<String> recipeSteps;
    private int prepTime;
    private String description;

    public Recipe(String recipeName){
        this.recipeName = recipeName;
        this.ingredientList = new ArrayList<>();
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

    public List<String> getIngredientList(){
        return this.ingredientList;
    }

    public void setIngredientList(List<String> ingredientList){
        this.ingredientList = ingredientList;
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


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return prepTime == recipe.prepTime
                && recipeName.equals(recipe.recipeName)
                && ingredientList.equals(recipe.ingredientList)
                && recipeSteps.equals(recipe.recipeSteps)
                && description.equals(recipe.description);
    }

    @Override
    public int hashCode()
    {
        return getRecipeName().hashCode();
    }
}
