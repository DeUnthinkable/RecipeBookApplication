package com.example.logic;

import com.example.domain.Recipe;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AppDataReadWrite
{
    private static File recipeListFile;

    public static void init(File newRecipeFilePath){
        recipeListFile = newRecipeFilePath;
    }

    public static File getRecipeListFile()
    {
        return recipeListFile;
    }
    public static void setRecipeListFile(File newRecipeFilePath)
    {
        recipeListFile = newRecipeFilePath;
    }

    public static void writeRecipeListToFile(ArrayList<Recipe> recipeList){
        try(PrintWriter writer = new PrintWriter(recipeListFile))
        {
            for(Recipe recipe : recipeList)
            {
                StringBuilder record = new StringBuilder();

                //Collecting information to add to record
                String recipeName = recipe.getRecipeName();
                Iterator<String> ingredients = recipe.getIngredientNameAndAmountMap().keySet().stream().toList().iterator();
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
                        record.append(recipe.getIngredientNameAndAmountMap().get(ingredientName));
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

    public static ArrayList<Recipe> getRecipeListFromFile(){
        ArrayList<Recipe> recipeList = new ArrayList<>();
        try(Scanner scanner = new Scanner(recipeListFile)){
            while(scanner.hasNextLine()){
                String[] record = scanner.nextLine().split(",",48);

                Recipe recipe = new Recipe(record[0]);
                recipeList.add(recipe);
                for(int i = 1; i < 31 && !record[i].equals(""); i+=2) {
                    int ingredientAmount = record[i+1].isBlank() || record[i+1].isEmpty() ? 0 : Integer.parseInt(record[i+1]);
                    recipe.getIngredientNameAndAmountMap().put(record[i],ingredientAmount);
                }

                List<String> recipeSteps = new ArrayList<>();
                for(int i = 31; i < 46 && record[i] != null; i++){
                    recipeSteps.add(record[i]);
                }
                recipe.setRecipeSteps(recipeSteps);

                int prepTime = record[46].isEmpty() || record[46].isBlank() ? 0 : Integer.parseInt(record[46]);
                recipe.setPrepTime(prepTime);
                recipe.setDescription(record[47]);
            }
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
        return recipeList;
    }
}
