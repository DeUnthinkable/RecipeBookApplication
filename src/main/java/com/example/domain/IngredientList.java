package com.example.domain;

import java.util.*;

public class IngredientList
{
    private Map<String, Ingredient> nameIngredientMap;

    public IngredientList(){
        this.nameIngredientMap = new HashMap<>();
    }

    public Set<String> getIngredientsNames(){
        return this.nameIngredientMap.keySet();
    }
    public void add(Ingredient ingredient){
        this.nameIngredientMap.put(ingredient.getName(), ingredient);
    }
    public void remove(String ingredient){
        this.nameIngredientMap.remove(ingredient);
    }
    public Ingredient getIngredient(String ingredient){
        return this.nameIngredientMap.get(ingredient);
    }
}
