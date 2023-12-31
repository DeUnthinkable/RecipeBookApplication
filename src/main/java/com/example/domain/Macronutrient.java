package com.example.domain;

public enum Macronutrient
{
    PROTEIN(4),
    CARBOHYDRATE(4),
    FAT(9);

    private final int calories;

    Macronutrient(int calories){
        this.calories = calories;
    }

    public int getCalories(){
        return this.calories;
    }
}
