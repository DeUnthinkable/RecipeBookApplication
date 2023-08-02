package com.example.logic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartViewController
{
    private  AppDataReadWriteStore appDataReadWriteStore;

    public void initData(AppDataReadWriteStore appDataReadWriteStore){
        this.appDataReadWriteStore = appDataReadWriteStore;
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}