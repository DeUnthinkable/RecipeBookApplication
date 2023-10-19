package com.example.logic;

import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.HashMap;


public class RecipeViewController
{
    private Recipe recipe;
    private ArrayList<Recipe> recipeList;
    private IngredientListWithAmounts ingredientsList;
    @FXML
    private TextArea description;
    @FXML
    private Button backToStartViewButton;
    @FXML
    private Label recipeName;


    public void initData(String recipeName){
        this.recipeList = AppDataReadWrite.getRecipeListFromFile();
        for (Recipe recipe : recipeList)
        {
            if (recipe.getRecipeName().equals(recipeName))
            {
                this.recipe = recipe;
            }
        }

        this.ingredientsList = recipe.getIngredientsList();

        this.recipeName.setText(recipe.getRecipeName());
        this.description.setText(recipe.getDescription());

        handlers();
    }

    public void handlers(){
        this.backToStartViewButton.setOnAction(SceneController::switchToStartView);

        this.description.textProperty().addListener(event -> this.recipe.setDescription(this.description.getText()));
    }
}
