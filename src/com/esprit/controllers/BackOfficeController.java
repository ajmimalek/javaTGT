/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers;

import com.esprit.models.CatégoriePublication;
import com.esprit.services.CatégoriePublicationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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
    private TableColumn<CatégoriePublication, Integer> idcat;
    @FXML
    private TableColumn<CatégoriePublication, String> nomcat;
    @FXML
    private TableColumn<CatégoriePublication, Button> update;
    @FXML
    private TableColumn<CatégoriePublication, Button> delete;
    
    ObservableList<CatégoriePublication> oblist = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        gestionTableView();
    }    
    
    
    public void gestionTableView(){
        //Déclaration
        idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        nomcat.setCellValueFactory(new PropertyValueFactory<>("nomCat"));
        update.setCellValueFactory(new PropertyValueFactory<>("update"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        
        //Editable nom du catégorie
        nomcat.setCellFactory(TextFieldTableCell.forTableColumn());
        nomcat.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNomCat(e.getNewValue());
        });
        
        //Transforme le tableau en éditable
        lscatégories.setEditable(true);
        
        //Chargement des donnés du BD
        CatégoriePublicationService cps = new CatégoriePublicationService();
        oblist.addAll(cps.afficher());
        lscatégories.setItems(oblist);
    }
}
