package com.example.logic;

import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;


public class RecipeViewController
{
    private Recipe recipe;
    private ArrayList<Recipe> recipeList;

    @FXML
    private Button backToStartViewButton;
    @FXML
    private Label recipeName;
    @FXML
    private TextArea description;
    @FXML
    private TextField prepTime;
    //Ingredients View
    @FXML
    private ListView<String> ingredientListView;
    @FXML
    private Button deleteIngredient;
    @FXML
    private Button addIngredient;
    @FXML
    private TextField newIngredientName;
    //Preparation Steps View
    @FXML
    private ListView<String> preparationStepsView;
    @FXML
    private Button deleteStep;
    @FXML
    private Button addStep;
    @FXML
    private TextField newStepName;


    public void initData(String recipeName){
        this.recipeList = AppDataReadWrite.getRecipeListFromFile();
        for (Recipe recipe : recipeList)
        {
            if (recipe.getRecipeName().equals(recipeName))
            {
                this.recipe = recipe;
            }
        }

        navigationHandlers();
        loadDataToView();
        dataChangeHandlers();
    }

    public void navigationHandlers(){
        this.backToStartViewButton.setOnAction(SceneController::switchToStartView);
    }

    public void loadDataToView(){
        this.recipeName.setText(recipe.getRecipeName());
        this.description.setText(recipe.getDescription());
        this.prepTime.setText(recipe.getPrepTime() + "");
        this.recipe.getIngredientList().forEach(ingredient -> this.ingredientListView.getItems().add(ingredient));
        this.recipe.getRecipeSteps().forEach(step -> this.preparationStepsView.getItems().add(step));
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