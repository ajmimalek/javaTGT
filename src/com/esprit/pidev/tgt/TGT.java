/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Malek
 */
public class TGT extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent  root = FXMLLoader.load(getClass().getResource("views/scene1.fxml"));
        Scene scene = new Scene(root);
        
        //Appliquer l'effet transparent pour enlever la barre Windows 
        primaryStage.initStyle(StageStyle.  TRANSPARENT);
        
        primaryStage.setTitle("Tunisians Got Talents");
        primaryStage.getIcons().add(new Image("file:favicon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
