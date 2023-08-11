package com.example.logic;

import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class RecipeViewController
{
    private Recipe recipe;
    @FXML
    private Button button;

    public void initData(Recipe recipe){
        this.recipe = recipe;
    }

    public void onButtonClick(){
        button.setText(recipe.getRecipeName());
    }
}
