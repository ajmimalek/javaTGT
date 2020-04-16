/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class Scene1Controller implements Initializable {

    @FXML
    private AnchorPane splash;

    /**
     * Initializes the controller class.
     */
    class ShowSplashScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(ShowSplashScreen.this.getClass().getResource("/com/esprit/pidev/tgt/views/login.fxml"));
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                    Scene scene = new Scene(root);
                    //Lancer le stage
                    stage.setTitle("Tunisians Got Talents");
                    stage.setScene(scene);
                    stage.show();
                    splash.getScene().getWindow().hide();
                    //Icone
                    stage.getIcons().add(new Image("file:favicon.png"));
                });
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ShowSplashScreen().start();
    }
   
    
}
