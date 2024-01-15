package com.example.logic;

import com.example.domain.IngredientListWithAmounts;
import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;


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
    @FXML
    private ListView ingredientListView;


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

        navigationHandlers();
        loadDataToView();
        dataChangeHandlers();

        //test
        ingredientsList.getIngredientsNames().forEach(ingredientName -> ingredientListView.getItems().add(ingredientName));
    }

    public void navigationHandlers(){
        this.backToStartViewButton.setOnAction(SceneController::switchToStartView);
    }

    public void loadDataToView(){
        this.description.setText(recipe.getDescription());
    }

    private void updateData()
    {
        AppDataReadWrite.writeRecipeListToFile(recipeList);
    }

    private void dataChangeHandlers()
    {
        this.description.textProperty().addListener(event -> {
            this.recipe.setDescription(this.description.getText());
            updateData();
        });
    }
}