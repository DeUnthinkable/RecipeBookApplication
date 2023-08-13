package com.example.logic;

import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class StartViewController
{
    @FXML
    private VBox leftRecipesButtonsColumn;
    @FXML
    private VBox rightRecipesButtonsColumn;

    private AppDataReadWriteStore appDataReadWriteStore;
    private HashMap<Button, Recipe> buttonRecipeHashMap;

    public void initData(AppDataReadWriteStore appDataReadWriteStore){
        this.appDataReadWriteStore = appDataReadWriteStore;
        this.buttonRecipeHashMap = new HashMap<>();

        //testing code
        for(int i = 0; i < 3; i++){
            addBlankRecipeButton();
        }
    }

    @FXML
    protected  void addBlankRecipeButton(){
        HBox hBox = createRecipeControlField();
        Recipe recipe = new Recipe("");

        //Check to see if column is already too full to add another recipe into it.
        if(leftRecipesButtonsColumn.getChildren().size() < 15){
            leftRecipesButtonsColumn.getChildren().add(hBox);
        } else if (rightRecipesButtonsColumn.getChildren().size() < 15) {
            rightRecipesButtonsColumn.getChildren().add(hBox);
        } else{
            return;
        }

        appDataReadWriteStore.getRecipeList().addRecipe(recipe);
        //Bad code
        buttonRecipeHashMap.put((Button) hBox.getChildren().get(1), recipe);

        updateData();
    }



    @FXML
    protected void addExistingRecipeButton(){
        if(leftRecipesButtonsColumn.getChildren().size() < 15){
            HBox hBox = createRecipeControlField();
            leftRecipesButtonsColumn.getChildren().add(hBox);
        } else if (rightRecipesButtonsColumn.getChildren().size() < 15)
        {
            HBox hBox = createRecipeControlField();
            rightRecipesButtonsColumn.getChildren().add(hBox);
        }
        updateData();
    }

    private void updateData(){
        appDataReadWriteStore.writeIngredientsToFile();
        appDataReadWriteStore.writeRecipesToFile();
    }

    public HBox createRecipeControlField(){
        HBox hBox = new HBox();
        hBox.setSpacing(5);

        TextField textField = new TextField();
        textField.setPrefWidth(240);
        textField.setPromptText("Recipe name:");

        Button button = new Button("open");
        button.setOpacity(0);

        hBox.getChildren().add(textField);
        hBox.getChildren().add(button);

        //Open recipe button handler
        button.setOnAction(event -> SceneController.switchToRecipeView(event, buttonRecipeHashMap.get(button)));
        //Updates recipe object's value to that of the text-field
        textField.textProperty().addListener(event -> buttonRecipeHashMap.get(button).setRecipeName(textField.getText()));

        //Functionality to let button show only on hover
        hBox.setOnMouseEntered(event -> {
            hBox.getChildren().get(1).setOpacity(1);
        });
        hBox.setOnMouseExited(event -> {
            hBox.getChildren().get(1).setOpacity(0);
        });

        return hBox;
    }
}