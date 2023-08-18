package com.example.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeBookApplication extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeBookApplication.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        AppDataReadWriteStore appDataReadWriteStore = new AppDataReadWriteStore("Ingredients.csv","Recipes.csv");
        appDataReadWriteStore.readIngredientsFromFile();
        appDataReadWriteStore.readRecipesFromFile();

        StartViewController controller = fxmlLoader.getController();
        controller.initData(appDataReadWriteStore);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            appDataReadWriteStore.writeIngredientsToFile();
            appDataReadWriteStore.writeRecipesToFile();
        });
    }
}