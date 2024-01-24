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

    private void dataChangeHandlers(){
        this.description.textProperty().addListener(event -> registerDescriptionChange());
        this.addIngredient.setOnAction(event -> registerNewIngredient());
        this.addStep.setOnAction(event -> registerNewStep());
    }

    private void registerDescriptionChange(){
        this.recipe.setDescription(this.description.getText());
        updateData();
    }

    private void registerNewIngredient(){
        //Adds the new ingredient entered using the text field to the ingredient list
        this.ingredientListView.getItems().add(this.newIngredientName.getText());
        //Clears the text field
        this.newIngredientName.textProperty().set("");
        //Scroll the list to show the newly added ingredient
        this.ingredientListView.scrollTo(this.ingredientListView.getItems().size());
        //Updates the recipe with the new ingredient list
        this.recipe.setIngredientList(this.ingredientListView.getItems().stream().toList());
        updateData();
    }

    private void registerNewStep(){
        //Adds the new step entered using the text field to the recipe steps list
        this.preparationStepsView.getItems().add(this.newStepName.getText());
        //Clears the text field
        this.newIngredientName.textProperty().set("");
        //Scroll the list to show the newly added step
        this.preparationStepsView.scrollTo(this.preparationStepsView.getItems().size());
        //Updates the recipe with the new recipe steps list
        this.recipe.setRecipeSteps(this.preparationStepsView.getItems().stream().toList());
        updateData();
    }
}