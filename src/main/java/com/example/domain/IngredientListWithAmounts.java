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

    public void add(Ingredient ingredient, int amount){
        if(!this.nameIngredientAmountMap.containsKey(ingredient.getName())) {
            super.add(ingredient);
            this.nameIngredientAmountMap.put(ingredient.getName(), amount);
        }
    }

    public void add(Ingredient ingredient){
        this.add(ingredient, 0);
    }

    @Override
    public void remove(String ingredient){
        super.remove(ingredient);
        this.nameIngredientAmountMap.remove(ingredient);
    }

    public int getTotalMacronutrientValue(Macronutrient macronutrient){
        return   nameIngredientAmountMap.keySet().stream()
                .map(this::getIngredient)
                .map(Ingredient::getNutritionFacts)
                .map(nutritionFacts -> nutritionFacts.getMacronutrientValue(macronutrient))
                .reduce(0, Integer::sum);
    }
}
