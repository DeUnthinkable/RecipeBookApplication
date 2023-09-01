package com.example.logic;

import com.example.domain.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private static Stage stage;
    private static Scene scene;
    private static FXMLLoader root;
    private static final AppDataReadWriteStore appDataReadWriteStore = new AppDataReadWriteStore("Ingredients.csv","Recipes.csv");

    public static void switchToStartView(ActionEvent event)
    {
        try {
            root = new FXMLLoader(RecipeBookApplication.class.getResource("start-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root.load(), 640, 480);

            appDataReadWriteStore.readIngredientsFromFile();
            appDataReadWriteStore.readRecipesFromFile();

            StartViewController startViewController = root.getController();
            startViewController.initData(appDataReadWriteStore);

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(windowEvent -> {
                appDataReadWriteStore.writeIngredientsToFile();
                appDataReadWriteStore.writeRecipesToFile();
            });
        } catch (Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }

    public static void switchToRecipeView(ActionEvent event, Recipe recipe)
    {
        try {
            root = new FXMLLoader(RecipeBookApplication.class.getResource("recipe-view.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root.load());
            stage.setScene(scene);
            stage.show();

            RecipeViewController controller = root.getController();
            controller.initData(recipe);

            stage.setOnCloseRequest(windowEvent -> {
                appDataReadWriteStore.writeIngredientsToFile();
                appDataReadWriteStore.writeRecipesToFile();
            });
        } catch (Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }
}
