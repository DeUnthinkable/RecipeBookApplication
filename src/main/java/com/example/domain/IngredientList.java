package com.example.domain;

import java.util.*;

public class IngredientList
{
    private Map<String, Ingredient> nameIngredientMap;

    public IngredientList(){
        this.nameIngredientMap = new HashMap<>();
    }

    public Set<String> getIngredients(){
        return this.nameIngredientMap.keySet();
    }
}
