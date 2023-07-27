package com.example.domain;

import com.example.domain.Macronutrient;

import java.util.Map;

public class Ingredient implements HasCalories
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

    public NutritionFacts getNutritionFacts(){
        return this.nutritionFacts;
    }

    @Override
    public int getCalories()
    {
        int carbCalories = this.nutritionFacts.getCarbValue() * Macronutrient.CARBOHYDRATE.getCalories();
        int fatCalories = this.nutritionFacts.getFatValue() * Macronutrient.FAT.getCalories();
        int proteinCalories = this.nutritionFacts.getProteinValue() * Macronutrient.PROTEIN.getCalories();
        return carbCalories + fatCalories + proteinCalories;
    }
}
