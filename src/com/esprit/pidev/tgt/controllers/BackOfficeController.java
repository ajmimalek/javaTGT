/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.CatégoriePublication;
import com.esprit.pidev.tgt.services.CatégoriePublicationService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class BackOfficeController implements Initializable {

    @FXML
    private Button gestionCandidature;
    @FXML
    private JFXTextField txtnomcat;
    @FXML
    private JFXButton btnajoutcat;
    @FXML
    private TableView<CatégoriePublication> lscatégories;
    @FXML
    private TableColumn<CatégoriePublication, String> nomcat;

    public static ObservableList<CatégoriePublication> oblist = FXCollections.observableArrayList();
    CatégoriePublicationService cps = new CatégoriePublicationService();
    @FXML
    private JFXButton btnsupprimer;
    @FXML
    private ImageView logout;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //Déclaration
        nomcat.setCellValueFactory(new PropertyValueFactory<>("nomCat"));

        //Editable nom du catégorie
        nomcat.setCellFactory(TextFieldTableCell.forTableColumn());
        nomcat.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNomCat(e.getNewValue());
            cps.modifier(e.getRowValue());
        });

        //Transforme le tableau en éditable
        lscatégories.setEditable(true);

        //Chargement des donnés du BD
        oblist.addAll(cps.afficher());
        lscatégories.setItems(oblist);
    }

    @FXML
    private void AjouterCatégorie(ActionEvent event) {
        if (txtnomcat.getText().trim().isEmpty()) {
            AlertMaker.showErrorMessage("Pas de texte", "Veuillez introduire un nom non vide Exemple : Art,Cinéma..");
        } else {
            CatégoriePublication cp = new CatégoriePublication(txtnomcat.getText());
            cps.ajouter(cp);
            oblist.add(cp);
            txtnomcat.setText("");
        }
    }

    @FXML
    private void SupprimerCatégorie(ActionEvent event) {
        CatégoriePublication selectedCatégorie = lscatégories.getSelectionModel().getSelectedItem();
        if (selectedCatégorie == null){
            AlertMaker.showSimpleAlert("Candidat non sélectionné", "Veuillez sélectionner un candidat");
        } else {
            cps.supprimer(selectedCatégorie);
            oblist.remove(selectedCatégorie);
        }
    }

    @FXML
    private void logout(MouseEvent event) {
        Rooting.navigate("Connexion", "login");
        anchorPane.getScene().getWindow().hide();
    }


}
