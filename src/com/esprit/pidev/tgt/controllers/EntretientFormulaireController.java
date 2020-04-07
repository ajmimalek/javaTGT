/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Entretien;
import com.esprit.pidev.tgt.enumeration.StatutEnt;
import com.esprit.pidev.tgt.services.EntretientService;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Statement;
import java.time.LocalDate;
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
public class EntretientFormulaireController implements Initializable {
private Statement statement;
    private EntretientService entretientService = new EntretientService();
    @FXML
    private JFXDatePicker dateEnt;
    @FXML
    private JFXButton affecter_une_date;
    @FXML
    private Label nom_condidat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void affecter_une_date(ActionEvent event) {
        
        LocalDate dateEnt = this.dateEnt.getValue();
        Entretien entretient=new Entretien(0, dateEnt, StatutEnt.en_cour, 0);
        System.out.println(entretient);
         try {
            entretientService.save(entretient);
            Rooting.navigate("main", "Main");
            closeStage();
        } catch (Exception ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
         private void closeStage() {
        ((Stage) nom_condidat.getScene().getWindow()).close();
    }
}
