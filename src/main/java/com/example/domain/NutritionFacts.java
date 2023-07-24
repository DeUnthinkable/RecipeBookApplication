package com.example.domain;

import java.util.HashMap;
import java.util.Map;

public class NutritionFacts {
    private final Map<Macronutrient, Integer> macronutrientCountMap;

    public NutritionFacts() {
        this.macronutrientCountMap = new HashMap<>();
    }
    public void setProteinValue(int value){
        if(value > 0) {
            this.macronutrientCountMap.put(Macronutrient.PROTEIN, value);
        }
    }
    public int getProteinValue(){
        return this.macronutrientCountMap.getOrDefault(Macronutrient.PROTEIN, 0);
    }
    public void setFatValue(int value){
        if(value > 0) {
            this.macronutrientCountMap.put(Macronutrient.FAT, value);
        }
    }
    public int getFatValue(){
        return this.macronutrientCountMap.getOrDefault(Macronutrient.FAT, 0);
    }
    public void setCarbValue(int value){
        if(value > 0) {
            this.macronutrientCountMap.put(Macronutrient.CARBOHYDRATE, value);
        }
    }
    public int getCarbValue(){
        return this.macronutrientCountMap.getOrDefault(Macronutrient.CARBOHYDRATE, 0);
    }
}
