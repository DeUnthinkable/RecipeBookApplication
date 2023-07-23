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
    public void setIngredientAmount(String ingredient, int amount){
        if(this.nameIngredientAmountMap.containsKey(ingredient)){
            this.nameIngredientAmountMap.put(ingredient, amount);
        }
    }
    //fix issue with overriding
    public void add(Ingredient ingredient, int amount){
        super.add(ingredient);
        this.nameIngredientAmountMap.put(ingredient.getName(), amount);
    }
    @Override
    public void remove(String ingredient){
        super.remove(ingredient);
        this.nameIngredientAmountMap.remove(ingredient);
    }
}
