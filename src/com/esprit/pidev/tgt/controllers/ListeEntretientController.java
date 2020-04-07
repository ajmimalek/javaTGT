/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class ListeEntretientController implements Initializable {

    @FXML
    private JFXButton affecter_une_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void affecter_une_date(ActionEvent event) {
        
         Rooting.navigate("formulaire entretient", "EntretientFormulaire");
            closeStage();
    }
            private void closeStage() {
        ((Stage) affecter_une_date.getScene().getWindow()).close();
    }
}
