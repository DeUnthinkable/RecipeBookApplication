package com.example.domain;

import java.util.*;

public class Recipe
{
    private Map<String, Ingredient> nameIngredientMap;
    private Map<String, Integer> nameIngredientAmountMap;

    private List<String> recipeSteps;
    private int prepTime;
    private String description;

    public Recipe(){
        this.nameIngredientMap = new HashMap<>();
        this.nameIngredientAmountMap = new HashMap<>();
        this.recipeSteps = new ArrayList<>();
        this.prepTime = 0;
        this.description = "";
    }

    public List<String> getRecipeSteps()
    {
        return recipeSteps;
    }

    public int getPrepTime()
    {
        return prepTime;
    }

    public String getDescription()
    {
        return description;
    }

    public Collection<Ingredient> getIngredients(){
        return this.nameIngredientMap.values();
    }

    public int getIngredientAmount(String ingredient){
        return this.nameIngredientAmountMap.getOrDefault(ingredient, 0);
    }
}
