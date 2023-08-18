package com.example.logic;

import com.example.domain.Ingredient;
import com.example.domain.IngredientList;
import com.example.domain.Recipe;
import com.example.domain.RecipeList;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AppDataReadWriteStore
{
    private final RecipeList recipeList;
    private final IngredientList allIngredientsList;
    private String ingredientsFilePath;
    private String recipeFilePath;

    public AppDataReadWriteStore(String ingredientsFilePath, String recipeFilePath){
        recipeList = new RecipeList();
        allIngredientsList = new IngredientList();

        this.ingredientsFilePath = ingredientsFilePath;
        this.recipeFilePath = recipeFilePath;

        //Preload past data entered by user
        readIngredientsFromFile();
        readRecipesFromFile();
    }

    public  RecipeList getRecipeList()
    {
        return recipeList;
    }

    public  IngredientList getAllIngredientsList()
    {
        return allIngredientsList;
    }

    public String getIngredientsFilePath()
    {
        return ingredientsFilePath;
    }

    public void setIngredientsFilePath(String ingredientsFilePath)
    {
        this.ingredientsFilePath = ingredientsFilePath;
    }

    public String getRecipeFilePath()
    {
        return recipeFilePath;
    }

    public void setRecipeFilePath(String recipeFilePath)
    {
        recipeFilePath = recipeFilePath;
    }



    public void readIngredientsFromFile() {
        try(Scanner scanner = new Scanner(Paths.get(ingredientsFilePath));)
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
    public void writeIngredientsToFile(){
        try(PrintWriter writer = new PrintWriter(ingredientsFilePath))
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

    public void writeRecipesToFile(){
        try(PrintWriter writer = new PrintWriter(recipeFilePath))
        {
            for(int i = 0; i < recipeList.getRecipesNames().size(); i++){
                StringBuilder record = new StringBuilder();

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
            }
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }

    public void readRecipesFromFile(){
        try(Scanner scanner = new Scanner(Paths.get(recipeFilePath))){
            while(scanner.hasNextLine()){
                String[] record = scanner.nextLine().split(",",48);

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
}
