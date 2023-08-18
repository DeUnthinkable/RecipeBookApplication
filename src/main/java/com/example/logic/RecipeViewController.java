package com.example.logic;

import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class RecipeViewController
{
    private Recipe recipe;
    @FXML
    private TextArea description;
    @FXML
    private Button backToStartViewButton;
    @FXML
    private Label recipeName;


    public void initData(Recipe recipe){
        this.recipe = recipe;

        this.recipeName.setText(recipe.getRecipeName());
        this.description.setText(recipe.getDescription());

        handlers();
    }

    public void handlers(){
        this.backToStartViewButton.setOnAction(event -> SceneController.switchToStartView(event));

        this.description.textProperty().addListener(event -> this.recipe.setDescription(this.description.getText()));
    }



}
