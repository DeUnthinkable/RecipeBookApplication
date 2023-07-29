package com.example.logic;

import com.example.domain.Ingredient;
import com.example.domain.IngredientList;
import com.example.domain.Recipe;
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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HelloApplication extends Application
{
    private RecipesList recipesList;
    private IngredientList allIngredientsList;
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void init(){
        recipesList = new RecipesList();
        allIngredientsList = new IngredientList();
    }
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //test code
        recipesList.addRecipe(new Recipe("scrambled eggs"));
        allIngredientsList.add(new Ingredient("tomato"));
        readIngredientsFromFile();
        recipesList.getRecipe("scrambled eggs").getIngredientsList().add(allIngredientsList.getIngredient("tomato"));


        writeIngredientsToFile("Ingredients.csv");
        writeRecipesToFile("Recipes.csv");
    }

    public void readIngredientsFromFile() {
        try(Scanner scanner = new Scanner(Paths.get("Ingredients.csv"));)
        {
            while (scanner.hasNextLine())
            {
                this.allIngredientsList.add(new Ingredient(scanner.nextLine()));
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
            List<String> ingredientsNames = allIngredientsList.getIngredientsNames().stream().toList();
            for(int i = 0; i < ingredientsNames.size(); i++){
                writer.println(ingredientsNames.get(i));
            }
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            System.out.println(error.getStackTrace());
        }
    }
    public void writeRecipesToFile(String filename){
        try(PrintWriter writer = new PrintWriter(filename))
        {
            StringBuilder record = new StringBuilder();
            for(int i = 0; i < recipesList.getRecipesNames().size(); i++){
                //Collecting information to add to record
                Recipe recipe = recipesList.getRecipe(recipesList.getRecipesNames().get(i));
                String recipeName = recipe.getRecipeName();
                Iterator<String> ingredients = recipe.getIngredientsList().getIngredientsNames().stream().toList().iterator();
                Iterator<String> steps = recipe.getRecipeSteps().iterator();
                int prepTime = recipe.getPrepTime();
                String description = recipe.getDescription();

                record.append(recipeName).append(",");

                //Add ingredient name and ingredient amount to record
                for(int j = 0; j < 15; j++){
                    if(ingredients.hasNext()){

                        String ingredientName = ingredients.next();
                        record.append(ingredientName);
                        record.append(",");
                        record.append(recipe.getIngredientsList().getIngredientAmount(ingredientName));
                    }
                    record.append(",");
                }

                //Add recipe steps to record
                for(int j = 0; j < 15; j++){
                    if(steps.hasNext()){
                        record.append(steps.next());
                    }
                    record.append(",");
                }

                //Add prep time and description to record
                record.append(prepTime).append(",");
                record.append(description);

                writer.println(record);
                writer.close();
            }
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            System.out.println(error.getStackTrace());
        }
    }

}