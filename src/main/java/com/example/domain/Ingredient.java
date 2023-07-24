package com.example.domain;

import com.example.domain.Macronutrient;

import java.util.Map;

public class Ingredient
{
    private String name;
    private String baseQuantity;
    private NutritionFacts nutritionFacts;

    public Ingredient(String name){
        this.name = name;
        this.baseQuantity = "";
        this.nutritionFacts = new NutritionFacts();
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBaseQuantity() {
        return this.baseQuantity;
    }

    public void setBaseQuantity(String baseQuantity) {
        this.baseQuantity = baseQuantity;
    }

    public int getNutritionFacts(Macronutrient macronutrient){
        return this.macronutrientCountMap.getOrDefault(macronutrient, 0);
    }
}
