package com.example.logic;

import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.HashMap;


public class RecipeViewController
{
    private Recipe recipe;
    private ArrayList<Recipe> recipeList;
    private HashMap<String, Integer> ingredientNameAndAmountMap;
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

        this.ingredientNameAndAmountMap = recipe.getIngredientNameAndAmountMap();

        this.recipeName.setText(recipe.getRecipeName());
        this.description.setText(recipe.getDescription());

        navigationHandlers();
        loadDataToView();
        dataChangeHandlers();

        //test
        ingredientNameAndAmountMap.keySet().forEach(ingredientName -> ingredientListView.getItems().add(ingredientName));
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