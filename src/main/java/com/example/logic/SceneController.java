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

    public static void switchToStartView(ActionEvent event)
    {
        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            switchToStartView(stage);
        } catch (Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }

    public static void switchToStartView(Stage stage){
        try {
            root = new FXMLLoader(RecipeBookApplication.class.getResource("start-view.fxml"));
            scene = new Scene(root.load(), 640, 480);

            stage.setScene(scene);
            stage.show();
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

        } catch (Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }
}
