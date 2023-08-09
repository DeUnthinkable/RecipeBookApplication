package com.example.logic;

import com.example.domain.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartViewController
{
    @FXML
    private VBox leftRecipesButtonsColumn;
    @FXML
    private VBox rightRecipesButtonsColumn;

    private  AppDataReadWriteStore appDataReadWriteStore;

    public void initData(AppDataReadWriteStore appDataReadWriteStore){
        this.appDataReadWriteStore = appDataReadWriteStore;

        //testing code
        for(int i = 0; i < 3; i++){
            addBlankRecipeButton();
        }
    }

    @FXML
    protected  void addBlankRecipeButton(){
        //Check to see if column is already too full to add another recipe into it.
        if(leftRecipesButtonsColumn.getChildren().size() < 15){
            HBox hBox = createRecipeBox();

            leftRecipesButtonsColumn.getChildren().add(hBox);
            appDataReadWriteStore.getRecipeList().addRecipe(new Recipe(""));

        } else if (rightRecipesButtonsColumn.getChildren().size() < 15)
        {
            HBox hBox = createRecipeBox();

            rightRecipesButtonsColumn.getChildren().add(hBox);
            appDataReadWriteStore.getRecipeList().addRecipe(new Recipe(""));
        }
        updateData();
    }
    private void updateData(){
        appDataReadWriteStore.writeIngredientsToFile();
        appDataReadWriteStore.writeRecipesToFile();
    }

    public HBox createRecipeBox(){
        HBox hBox = new HBox();
        hBox.setSpacing(5);

        TextField textField = new TextField();
        textField.setPrefWidth(240);
        textField.setPromptText("Recipe name:");
        hBox.getChildren().add(textField);

        Button button = new Button("open");
        button.setOpacity(0);
        hBox.getChildren().add(button);

        hBox.setOnMouseEntered(event -> {
            hBox.getChildren().get(1).setOpacity(1);
        });
        hBox.setOnMouseExited(event -> {
            hBox.getChildren().get(1).setOpacity(0);
        });

        return hBox;
    }

}