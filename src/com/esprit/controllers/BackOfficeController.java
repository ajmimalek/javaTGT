/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers;

import com.esprit.models.CatégoriePublication;
import com.esprit.services.CatégoriePublicationService;
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
    @FXML
    private TableColumn<CatégoriePublication, JFXButton> delete;

    public static ObservableList<CatégoriePublication> oblist = FXCollections.observableArrayList();
    CatégoriePublicationService cps = new CatégoriePublicationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Déclaration
        nomcat.setCellValueFactory(new PropertyValueFactory<>("nomCat"));
        nomcat.prefWidthProperty().bind(lscatégories.widthProperty().multiply(2.75).divide(4));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        delete.prefWidthProperty().bind(lscatégories.widthProperty().divide(4));

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
        JFXButton button = new JFXButton("Supprimer");
        button.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white;");
        button.setOnAction((deleteEvent) -> {

        });
        if (txtnomcat.getText().trim().isEmpty()) {
            System.out.println("Pas de texte");
        } else {
            CatégoriePublication cp = new CatégoriePublication(txtnomcat.getText(), button);
            cps.ajouter(cp);
            oblist.add(cp);
            txtnomcat.setText(null);
        }
    }

}
