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
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeBookApplication.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        AppDataReadWriteStore appDataReadWriteStore = new AppDataReadWriteStore();
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