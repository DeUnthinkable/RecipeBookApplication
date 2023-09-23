package com.example.logic;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class RecipeBookApplication extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        //Initiate which files to read data from and write data into
        File ingredientListFile = new File("Ingredients.csv");
        File recipeListFile = new File("Recipes.csv");
        AppDataReadWrite.init(ingredientListFile, recipeListFile);

        SceneController.switchToStartView(stage);
    }
}