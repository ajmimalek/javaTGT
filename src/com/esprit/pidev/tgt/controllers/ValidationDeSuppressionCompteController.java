/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.services.CompteService;
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
 *
 * @author Seif Henchir
 */
public class ValidationDeSuppressionCompteController  implements Initializable{
    
    
    
     private Statement statement;
    private CompteService compteService = new CompteService();
    private Compte compte;
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

     public void initfields(Compte compte, BackOficeProfileController backOficeprofileController){
    this.compte=compte;
     this.backOficeprofileController= backOficeprofileController;
    }

    @FXML
    private void confirmationUS(ActionEvent event) throws SQLException {
        compteService.delete(compte);
        backOficeprofileController.loadData();
         closeStage();
        
       
    }

    @FXML
    private void abendonnerUS(ActionEvent event) throws SQLException {
           closeStage();
    }

//    void initfields(Utilisateur selectedCompte, BackOficeProfileController aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
    
    
    
    
    
    
    
    
    
}
