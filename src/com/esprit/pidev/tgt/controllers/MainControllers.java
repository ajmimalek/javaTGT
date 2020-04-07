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
import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class MainControllers implements Initializable {

    @FXML
    private Tab acceuil;
    @FXML
    private Tab publications;
    @FXML
    private Tab Organisation;
    @FXML
    private JFXButton ajouter_un_condidat;
    @FXML
    private Tab gestion_entretients;
    @FXML
    private JFXButton Liste_des_condidats;
    @FXML
    private JFXButton Liste_des_entretients;
    @FXML
    private JFXButton condidats_accepter;
    @FXML
    private Tab formatons;
    @FXML
    private Tab article;
    @FXML
    private Tab profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_un_condidat(ActionEvent event) {
        Rooting.navigate("main", "CondidatFormulaire");
      //      closeStage();
        }
       
    private void closeStage() {
        ((Stage) ajouter_un_condidat.getScene().getWindow()).close();
    }
        
     @FXML
    void Liste_des_condidats(ActionEvent event) {
        
            Rooting.navigate("list candidat", "BackOfice");
    }

    @FXML
    void Liste_des_entretients(ActionEvent event) {
         Rooting.navigate("main", "EntretientFormulaire");
            closeStage();
        }

    @FXML
    void condidats_accepter(ActionEvent event) {

    }
 }

