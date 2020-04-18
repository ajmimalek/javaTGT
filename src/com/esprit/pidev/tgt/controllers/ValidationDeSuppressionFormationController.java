/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;


import com.esprit.pidev.tgt.entities.Formation;
import com.esprit.pidev.tgt.services.FormationService;
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
public class ValidationDeSuppressionFormationController implements Initializable {

    private Statement statement;
    private FormationService formationService = new FormationService();
    private Formation formation;
    private ListFormationController ListFormationController;
     private ListFormation_1Controller ListFormation_1Controller;
    
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
        
        formationService.supprimer(formation);
        ListFormationController.loadData();
         closeStage();
    }
    
    private void closeStage() {
    ((Stage) attention.getScene().getWindow()).close();
    }

    @FXML
    private void abendonner(ActionEvent event) {
        closeStage();
    }
     public void initfields(Formation formation, ListFormationController listFormationController){
    this.formation=formation;
     this.ListFormationController= listFormationController;
    }

     public void initfieldss(Formation formation, ListFormation_1Controller listFormation_1Controller){
    this.formation=formation;
     this.ListFormation_1Controller= listFormation_1Controller;
    }
}
