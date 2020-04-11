/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.services.UtilisateurService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class ValidationDeSuppressionProfileController implements Initializable {

    private Statement statement;
    private UtilisateurService utilisateurService = new UtilisateurService();
    private Utilisateur utilisateur;
    private BackOficeProfileController backOficeprofileController;
    
       @FXML
       private Label attention;
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       
  
         private void closeStage() {
        ((Stage) attention.getScene().getWindow()).close();
    }

     public void initfields(Utilisateur utilisateur, BackOficeProfileController backOficeprofileController){
    this.utilisateur=utilisateur;
     this.backOficeprofileController= backOficeprofileController;
    }

    @FXML
    private void confirmationUS(ActionEvent event) throws SQLException {
        utilisateurService.delete(utilisateur);
        backOficeprofileController.loadData();
         closeStage();
        
       
    }

    @FXML
    private void abendonnerUS(ActionEvent event) throws SQLException {
           closeStage();
    }


}
