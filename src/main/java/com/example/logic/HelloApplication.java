package com.example.logic;

import com.example.domain.Ingredient;
import com.example.domain.IngredientList;
import com.example.domain.RecipesList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

        writeIngredientsToFile("Ingredients.csv");
        readIngredientsFromFile();
        System.out.println(ingredientList.getIngredient("food"));
    }

    public void readIngredientsFromFile() {
        try(Scanner scanner = new Scanner(Paths.get("Ingredients.csv"));)
        {
            while (scanner.hasNextLine())
            {
                this.ingredientList.add(new Ingredient(scanner.nextLine()));
            }
        }
        catch (Exception error){
            System.out.println(error.getMessage());
            System.out.println(error.getStackTrace());
        }
    }
    public void writeIngredientsToFile(String filename){
        try(PrintWriter writer = new PrintWriter(filename))
        {
            List<String> ingredientsNames = new ArrayList<>();
            for(int i = 0; i < ingredientsNames.size(); i++){
                writer.println(ingredientsNames.get(i));
            }
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            System.out.println(error.getStackTrace());
        }
    }



}