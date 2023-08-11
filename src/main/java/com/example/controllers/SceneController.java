package com.example.controllers;

import com.example.logic.RecipeBookApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public static void switchToStartView(ActionEvent event)
    {
        try {
            root = FXMLLoader.load(RecipeBookApplication.class.getResource("start-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }

    public static void switchToRecipeView(ActionEvent event)
    {
        try {
        root = FXMLLoader.load(RecipeBookApplication.class.getResource("recipe-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (Exception error){
        System.out.println(error.getMessage());
        error.printStackTrace();
        }
    }
}
