/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.services.CandidatService;
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
public class ValidationDeSuppressionController implements Initializable {

    private Statement statement;
    private CandidatService candidatService = new CandidatService();
    private Candidat candidat;
    private BackOficeController backOficeController;
    
    @FXML
    private Label attention;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmation(ActionEvent event) throws SQLException {
        
        candidatService.delete(candidat);
        backOficeController.loadData();
         closeStage();
    }
         private void closeStage() {
        ((Stage) attention.getScene().getWindow()).close();
    }

    @FXML
    private void abendonner(ActionEvent event) {
        closeStage();
    }
     public void initfields(Candidat candidat, BackOficeController backOficeController){
    this.candidat=candidat;
     this.backOficeController= backOficeController;
    }

}
