/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.CommentairePublication;
import com.esprit.pidev.tgt.services.CommentairePublicationService;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import com.esprit.pidev.tgt.entities.Publication;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXTextField;
import java.sql.Timestamp;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author ajmim_9xsk8tf
 */
public class CommentairesController implements Initializable {

    @FXML
    private AnchorPane ancorPane;
    @FXML
    private JFXButton btnpublier;
    @FXML
    private Rating btnrating;
    @FXML
    private JFXTextField txtrating;
    @FXML
    private MaterialDesignIconView menu;
    @FXML
    private TableColumn<CommentairePublication, String> commentaire;
    @FXML
    private TableColumn<CommentairePublication, Integer> rating;
    @FXML
    private TableColumn<CommentairePublication,Timestamp> dateComm;
    @FXML
    private TableView<CommentairePublication> lscommentaires;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton trieDate;
    @FXML
    private JFXButton trieRating;
    @FXML
    private JFXButton restore;

    @FXML
    void SupprimerCatégorie(ActionEvent event) {
        CommentairePublication selectedCommentaire = lscommentaires.getSelectionModel().getSelectedItem();
        if (selectedCommentaire == null){
            AlertMaker.showSimpleAlert("Commentaire non sélectionné", "Veuillez sélectionner un commentaire");
        } else {
            cps.supprimer(selectedCommentaire);
            oblist.remove(selectedCommentaire);
        }
    }

    CommentairePublicationService cps = new CommentairePublicationService();
    public static ObservableList<CommentairePublication> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oblist.clear();
        //cellValueFactory gets value from item
        commentaire.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        rating.setCellValueFactory(new PropertyValueFactory<>("ratingComm"));
        dateComm.setCellValueFactory(new PropertyValueFactory<>("dateComm"));
        //Transforme le tableau en éditable
        lscommentaires.setEditable(true);
        //Editable contenu commentaire
        commentaire.setCellFactory(TextFieldTableCell.forTableColumn());
        commentaire.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setContenu(e.getNewValue());
            cps.modifier(e.getRowValue());
        });
        rating.setCellFactory(TextFieldTableCell.<CommentairePublication, Integer>forTableColumn(new IntegerStringConverter()));
        rating.setOnEditCommit(e -> {
            if (e.getNewValue() > 5) {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setRatingComm(5);
                AlertMaker.showErrorMessage("erroné", "Veuillez saisir une valeur inférieur à 5");
                cps.modifier(e.getRowValue());
                System.out.println(e.getRowValue());
            } else if (e.getNewValue() < 0) {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setRatingComm(0);
                AlertMaker.showErrorMessage("erroné", "Veuillez saisir une valeur supérieur à 0");
                cps.modifier(e.getRowValue());
                System.out.println(e.getRowValue());
            }
            cps.modifier(e.getRowValue());
            System.out.println(e.getRowValue());
        });
        lscommentaires.setItems(oblist);
        
    }

    public void AjouterCommentaire(Publication p) {
        lscommentaires.setItems(oblist);
        btnpublier.addEventHandler(ActionEvent.ACTION, (event) -> {
            
                if (txtrating.getText() == null) {
                    txtrating.setText("");
                }
                CommentairePublication cp = new CommentairePublication(txtrating.getText(), (int) btnrating.getRating());
                cp.setPublication(p);
                cps.ajouter(cp);
                oblist.add(cp);
                txtrating.setText("");
                btnrating.setRating(0);
        });
        trieDate.addEventHandler(ActionEvent.ACTION, (event) -> {
            oblist.clear();
            //System.out.println(cps.trieParDate(p.getId_pub()));
            oblist.addAll(cps.trieParDate(p.getId_pub()));
            lscommentaires.setItems(oblist);
        });
        trieRating.addEventHandler(ActionEvent.ACTION, (event) -> {
            oblist.clear();
            oblist.addAll(cps.trieParRating(p.getId_pub()));
            lscommentaires.setItems(oblist);
        });
        restore.addEventHandler(ActionEvent.ACTION, (event) -> {
            oblist.clear();
            oblist.addAll(cps.afficher(p));
            lscommentaires.setItems(oblist);
        });
    }

}
