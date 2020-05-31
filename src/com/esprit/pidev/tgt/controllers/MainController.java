/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton publications;
    @FXML
    private JFXButton organisations;
    @FXML
    private JFXButton evenements;
    @FXML
    private JFXButton casting;
    @FXML
    private JFXButton formations;
    @FXML
    private JFXButton articles;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    public AnchorPane main;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void GestionPublications(ActionEvent event) {
        anchorPane.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/Publications.fxml"));
            anchorPane.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        Rooting.navigate("Connexion", "login");
        anchorPane.getScene().getWindow().hide();
    }

}
