package com.example.domain;

import java.util.HashMap;
import java.util.Map;

public class NutritionFacts
{
    private final Map<Macronutrient, Integer> macronutrientCountMap;

    public NutritionFacts()
    {
        this.macronutrientCountMap = new HashMap<>();
    }

    public void setMacronutrientValue(Macronutrient macronutrient, int value)
    {
        if (value > 0)
        {
            this.macronutrientCountMap.put(macronutrient, value);
        }
    }

    public int getMacronutrientValue(Macronutrient macronutrient)
    {
        return this.macronutrientCountMap.getOrDefault(macronutrient, 0);
    }
}
