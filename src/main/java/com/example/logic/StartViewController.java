package com.example.logic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    }

    @FXML
    protected  void addRecipeButton(){
        //Check to see if column is already too full to add another recipe into it.
        if(leftRecipesButtonsColumn.getChildren().size() < 15){
            leftRecipesButtonsColumn.getChildren().add(new Button(""));
        } else if (rightRecipesButtonsColumn.getChildren().size() < 15)
        {
            rightRecipesButtonsColumn.getChildren().add(new Button(""));
        }
    }
}