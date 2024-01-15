package com.example.domain;

import java.util.HashMap;
import java.util.Map;

public class Ingredient
{
    private String name;
    private String baseQuantity;
    private final Map<Macronutrient, Integer> macronutrientValueMap;

    public Ingredient(String name, String baseQuantity){
        this.name = name;
        this.baseQuantity = baseQuantity;
        this.macronutrientValueMap = new HashMap<>();

        macronutrientValueMap.put(Macronutrient.PROTEIN, 0);
        macronutrientValueMap.put(Macronutrient.CARBOHYDRATE, 0);
        macronutrientValueMap.put(Macronutrient.FAT, 0);
    }
    public Ingredient(String name){
        this.name = name;
        this.baseQuantity = "";
        this.macronutrientValueMap = new HashMap<>();

        macronutrientValueMap.put(Macronutrient.PROTEIN, 0);
        macronutrientValueMap.put(Macronutrient.CARBOHYDRATE, 0);
        macronutrientValueMap.put(Macronutrient.FAT, 0);
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

    /**
     *
     * @param macronutrient
     * @return
     */
    public int getMacronutrientValue(Macronutrient macronutrient){
        return this.macronutrientValueMap.getOrDefault(macronutrient, 0);
    }

    /**
     * Sets new value for the provided macronutrient
     * @param macronutrient the macronutrient whose value is to be changed
     * @param value the new value for the macronutrient provided
     */
    public void setMacronutrientValue(Macronutrient macronutrient, int value){
        //Turns the value of the macronutrient to be 0 if the provided value was negative
        if(value < 0){
            value = 0;
        }
        //Doesn't add new macronutrient but changes the existing one since it is already contained in the map
        macronutrientValueMap.put(macronutrient, value);
    }

    public int getCalories()
    {
        int carbCalories = getMacronutrientValue(Macronutrient.CARBOHYDRATE) * Macronutrient.CARBOHYDRATE.getCalories();
        int fatCalories = getMacronutrientValue(Macronutrient.FAT) * Macronutrient.FAT.getCalories();
        int proteinCalories = getMacronutrientValue(Macronutrient.PROTEIN) * Macronutrient.PROTEIN.getCalories();
        return carbCalories + fatCalories + proteinCalories;
    }
}
