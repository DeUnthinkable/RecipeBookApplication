package com.example.logic;

import com.example.domain.Ingredient;
import com.example.domain.IngredientList;
import com.example.domain.RecipesList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloApplication extends Application
{
    private RecipesList recipesList;
    private IngredientList ingredientList;
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void init(){
        recipesList = new RecipesList();
        ingredientList = new IngredientList();
    }
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        readIngredientsFromFile();
        System.out.println(ingredientList.getIngredient("food"));
    }

    public void readIngredientsFromFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get("Ingredients.csv"));
        while (scanner.hasNextLine()){
            this.ingredientList.add(new Ingredient(scanner.nextLine()));
        }
    }


}