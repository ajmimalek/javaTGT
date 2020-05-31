/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Julie
 */
public class Rooting {
    
    
    public static FXMLLoader  navigate(String titre, String interfaceName){
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(Rooting.class.getClassLoader().getResource("com/esprit/pidev/tgt/views/"+interfaceName+".fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(titre);
            stage.getIcons().add(new Image("file:favicon.png"));
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            AlertMaker.showErrorMessage(ex);
        }
        
        return loader;
    }
    
}