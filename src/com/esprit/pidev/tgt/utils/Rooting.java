/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            //stage.getIcons().add(new Image("/resources/image/Image3.png"));
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        
        return loader;
    }
    
}
