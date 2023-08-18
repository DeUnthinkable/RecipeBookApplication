package com.example.logic;

import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;

public class StartViewController
{
    @FXML
    private VBox leftRecipesButtonsColumn;
    @FXML
    private VBox rightRecipesButtonsColumn;

    private AppDataReadWriteStore appDataReadWriteStore;
    private HashMap<HBox, Recipe> hBoxRecipeHashMap;

    public void initData(AppDataReadWriteStore appDataReadWriteStore){
        this.appDataReadWriteStore = appDataReadWriteStore;
        this.hBoxRecipeHashMap = new HashMap<>();

        loadSavedRecipesButtons();
    }

    public void loadSavedRecipesButtons(){
        for(int i = 0; i < this.appDataReadWriteStore.getRecipeList().size(); i++){
            addRecipeButton(this.appDataReadWriteStore.getRecipeList().get(i));
        }
    }

    @FXML
    public void addNewRecipe(){
        Recipe recipe = new Recipe("");
        addRecipeButton(recipe);
        appDataReadWriteStore.getRecipeList().addRecipe(recipe);
    }

    @FXML
    public void addRecipeButton(Recipe recipe){
        HBox hBox = createRecipeControlField(recipe);

        //Check to see if column is already too full to add another recipe into it.
        if(leftRecipesButtonsColumn.getChildren().size() < 15){
            leftRecipesButtonsColumn.getChildren().add(hBox);
        } else if (rightRecipesButtonsColumn.getChildren().size() < 15) {
            rightRecipesButtonsColumn.getChildren().add(hBox);
        } else{
            return;
        }

        hBoxRecipeHashMap.put(hBox, recipe);

        updateData();
    }

    private void updateData(){
        appDataReadWriteStore.writeIngredientsToFile();
        appDataReadWriteStore.writeRecipesToFile();
    }

    public HBox createRecipeControlField(Recipe recipe){
        HBox hBox = new HBox();
        hBox.setSpacing(5);

        TextField textField = new TextField(recipe.getRecipeName());
        textField.setPrefWidth(220);
        textField.setPromptText("Recipe name:");

        Button openRecipeButton = new Button("open");
        openRecipeButton.setOpacity(0);

        Button deleteRecipeButton = new Button("del.");
        deleteRecipeButton.setOpacity(0);

        hBox.getChildren().add(textField);
        hBox.getChildren().add(openRecipeButton);
        hBox.getChildren().add(deleteRecipeButton);

        //Opens a recipe-view.fxml file based on the recipe
        openRecipeButton.setOnAction(event -> SceneController.switchToRecipeView(event, hBoxRecipeHashMap.get(hBox)));

        //Deletes recipe from GUI and internal data structure
        deleteRecipeButton.setOnAction(event -> {
            HBox hBoxToDelete = (HBox)((Node)event.getSource()).getParent();
            if(this.rightRecipesButtonsColumn.getChildren().contains(hBoxToDelete)){
                this.rightRecipesButtonsColumn.getChildren().remove(hBoxToDelete);
            } else if (this.leftRecipesButtonsColumn.getChildren().contains(hBoxToDelete)) {
                this.leftRecipesButtonsColumn.getChildren().remove(hBoxToDelete);
            }

            appDataReadWriteStore.getRecipeList().remove(this.hBoxRecipeHashMap.get(hBox));
        });

        //Updates recipe object's value to that of the text-field
        textField.textProperty().addListener(event -> hBoxRecipeHashMap.get(hBox).setRecipeName(textField.getText()));

        //Functionality to let button show only on hover
        hBox.setOnMouseEntered(event -> {
            hBox.getChildren().get(1).setOpacity(1);
            hBox.getChildren().get(2).setOpacity(1);
        });
        hBox.setOnMouseExited(event -> {
            hBox.getChildren().get(1).setOpacity(0);
            hBox.getChildren().get(2).setOpacity(0);
        });

        return hBox;
    }
}