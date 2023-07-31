package com.example.logic;

import com.example.domain.Ingredient;
import com.example.domain.IngredientList;
import com.example.domain.Recipe;
import com.example.domain.RecipeList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RecipeBookApplication extends Application
{
    private static RecipeList recipeList;
    private static IngredientList allIngredientsList;
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void init(){
        recipeList = new RecipeList();
        allIngredientsList = new IngredientList();
    }
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeBookApplication.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        stage.setScene(scene);
        stage.show();

        //test code
        //recipesList.addRecipe(new Recipe("scrambled eggs"));

        //allIngredientsList.add(new Ingredient("tomato"));
        readIngredientsFromFile();
        readRecipesFromFile("Recipes.csv");
        recipeList.getRecipe("scrambled eggs").getIngredientsList().add(allIngredientsList.getIngredient("tomato"));


        writeIngredientsToFile("Ingredients.csv");
        writeRecipesToFile("Recipes.csv");
    }

    public static void readIngredientsFromFile() {
        try(Scanner scanner = new Scanner(Paths.get("Ingredients.csv"));)
        {
            while (scanner.hasNextLine())
            {
                allIngredientsList.add(new Ingredient(scanner.nextLine()));
            }
        }
        catch (Exception error){
            System.out.println(error.getMessage());
            System.out.println(error.getStackTrace());
        }
    }
    public static void writeIngredientsToFile(String filename){
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
    public static void writeRecipesToFile(String filename){
        try(PrintWriter writer = new PrintWriter(filename))
        {
            StringBuilder record = new StringBuilder();
            for(int i = 0; i < recipeList.getRecipesNames().size(); i++){
                //Collecting information to add to record
                Recipe recipe = recipeList.getRecipe(recipeList.getRecipesNames().get(i));
                String recipeName = recipe.getRecipeName();
                Iterator<String> ingredients = recipe.getIngredientsList().getIngredientsNames().stream().toList().iterator();
                Iterator<String> steps = recipe.getRecipeSteps().iterator();
                int prepTime = recipe.getPrepTime();
                String description = recipe.getDescription();

                record.append(recipeName).append(",");

                //Add ingredient name and ingredient amount to record
                for(int j = 0; j < 15; j++){
                    boolean hasNextIngredient = ingredients.hasNext();
                    String ingredientName = "";
                    if(hasNextIngredient){
                        ingredientName = ingredients.next();
                        record.append(ingredientName);
                    }
                    record.append(",");
                    if(hasNextIngredient){
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
            error.printStackTrace();
        }
    }

    public static void readRecipesFromFile(String filename){
        try(Scanner scanner = new Scanner(Paths.get(filename))){
            while(scanner.hasNextLine()){
                String[] record = scanner.nextLine().split(",",48);
                System.out.println(record.length);

                Recipe recipe = new Recipe(record[0]);
                recipeList.addRecipe(recipe);
                for(int i = 1; i < 31 && !record[i].equals(""); i+=2) {
                    recipe.getIngredientsList().add(new Ingredient(record[i]), Integer.parseInt(record[i+1]));
                }

                List<String> recipeSteps = new ArrayList<>();
                for(int i = 31; i < 46 && record[i] != null; i++){
                    recipeSteps.add(record[i]);
                }
                recipe.setRecipeSteps(recipeSteps);

                recipe.setPrepTime(Integer.parseInt(record[46]));
                recipe.setDescription(record[47]);
            }
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }

    public static RecipeList getRecipeList()
    {
        return recipeList;
    }

    public static IngredientList getAllIngredientsList()
    {
        return allIngredientsList;
    }
}