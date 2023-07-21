package com.example.domain;

import java.util.HashMap;
import java.util.Map;

public class IngredientListWithAmounts extends IngredientList
{
    private Map<String, Integer> nameIngredientAmountMap;

    public IngredientListWithAmounts(){
        super();
        this.nameIngredientAmountMap = new HashMap<>();
    }

    public int getIngredientAmount(String ingredient){
        return this.nameIngredientAmountMap.getOrDefault(ingredient, 0);
    }
}
