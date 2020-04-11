/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers;

import com.esprit.models.CatégoriePublication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class BackOfficeController implements Initializable {

    @FXML
    private Button gestionCandidature;
    @FXML
    private TextField txtnomcat;
    @FXML
    private Button btnajoutcat;
    @FXML
    private TableView<CatégoriePublication> lscatégories;
    @FXML
    private TableColumn<?, ?> idcat;
    @FXML
    private TableColumn<?, ?> nomcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
