package com.example.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private static Stage stage;
    private static Scene scene;
    private static FXMLLoader root;

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

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
            scene = new Scene(root.load(), SCENE_WIDTH, SCENE_HEIGHT);
            scene.getStylesheets().add(RecipeBookApplication.class.getResource("start-view.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }

    public static void switchToRecipeView(ActionEvent event, String recipeName)
    {
        try {
            root = new FXMLLoader(RecipeBookApplication.class.getResource("recipe-view.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root.load(), SCENE_WIDTH, SCENE_HEIGHT);
            scene.getStylesheets().add(RecipeBookApplication.class.getResource("recipe-view.css").toExternalForm());

            stage.setScene(scene);
            stage.show();

            RecipeViewController controller = root.getController();
            controller.initData(recipeName);

        } catch (Exception error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }
}
