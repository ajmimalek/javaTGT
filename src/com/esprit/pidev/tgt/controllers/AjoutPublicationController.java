/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.CatégoriePublication;
import com.esprit.pidev.tgt.entities.Publication;
import com.esprit.pidev.tgt.services.CatégoriePublicationService;
import com.esprit.pidev.tgt.services.PublicationService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class AjoutPublicationController implements Initializable {

    @FXML
    private JFXTextArea contenu;
    @FXML
    private JFXComboBox<String> catégories;
    @FXML
    private Button video;
    @FXML
    private Label videotitle;
    @FXML
    private JFXButton btnpublier;
    @FXML
    private AnchorPane ancorPane;

    PublicationService ps = new PublicationService();

    //Loading Categories
    CatégoriePublicationService cps = new CatégoriePublicationService();
    ObservableList<CatégoriePublication> oblist = FXCollections.observableArrayList();
    HashMap<String, Integer> catégoriesMap = new HashMap();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        videotitle.setText("Aucun fichier choisi");
        oblist.addAll(cps.afficher());
        for (CatégoriePublication catégoriePublication : oblist) {
            catégories.getItems().add(catégoriePublication.getNomCat());
            catégoriesMap.put(catégoriePublication.getNomCat(), catégoriePublication.getId_cat());
        }
    }

    @FXML
    private void uploadVidéo(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choisir un vidéo");
        Stage s = (Stage) ancorPane.getScene().getWindow();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP4", "*.mp4"),
                new FileChooser.ExtensionFilter("AVI", "*.avi"),
                new FileChooser.ExtensionFilter("WMV", "*.wmv")
        );
        File f = chooser.showOpenDialog(s);
        Path path = Paths.get("C:\\wamp64\\www\\TGTWeb\\web\\uploads\\assets");
        Files.createDirectories(path);
        File publicationFile = new File(path.toString() + "/" + f.getName());
        try {
            copyFile(f, publicationFile);
        } catch (FileAlreadyExistsException e) {
            AlertMaker.showErrorMessage("Fichier Existant", e.getMessage());
        }
        videotitle.setText(publicationFile.toURI().toString());
    }

    @FXML
    private void Publier(ActionEvent event) {
        if (catégories.getValue() == null) {
            AlertMaker.showSimpleAlert("Catégorie non sélectionnée", "Veuillez sélectionner une catégorie");
        } else if ("Aucun fichier choisi".equals(videotitle.getText())) {
            AlertMaker.showSimpleAlert("Vidéo non disponible", "Veuillez déposer un vidéo");
        } else {
            try {
                int indexCat = catégoriesMap.get(catégories.getSelectionModel().getSelectedItem());
                ps.ajouter(new Publication(contenu.getText(), videotitle.getText(), ps.parseAddress(), indexCat));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                AlertMaker.showErrorMessage(ex);
            }
            AlertMaker.showSimpleAlert("Publication Ajoutée", "Publication Ajoutée avec succés");
            contenu.setText("");
            catégories.getSelectionModel().clearSelection();
            videotitle.setText("Aucun fichier choisi");
        }
    }

    private static void copyFile(File src, File dest) throws IOException {
        Files.copy(src.toPath(), dest.toPath());
    }

}
