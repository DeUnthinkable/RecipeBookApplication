package com.example.domain;

import com.example.domain.Macronutrient;

import java.util.Map;

public class Ingredient
{
    private String name;
    private String baseQuantity;
    private Map<Macronutrient, Integer> macronutrientCountMap;

    public Ingredient(String name){
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    public String getBaseQuantity()
    {
        return this.baseQuantity;
    }

    public int getMacronutrientCount(Macronutrient macronutrient){
        return this.macronutrientCountMap.getOrDefault(macronutrient, 0);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setBaseQuantity(String baseQuantity)
    {
        this.baseQuantity = baseQuantity;
    }

    public void setMacronutrientCountMap(Macronutrient macronutrient, int count){
        this.macronutrientCountMap.put(macronutrient, count);
    }
}
