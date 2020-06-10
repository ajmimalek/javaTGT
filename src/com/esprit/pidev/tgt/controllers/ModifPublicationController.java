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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ajmim_9xsk8tf
 */
public class ModifPublicationController implements Initializable {

    @FXML
    private AnchorPane ancorPane;

    @FXML
    private JFXButton btnpublier;

    @FXML
    private JFXButton btnlocation;

    @FXML
    private JFXComboBox<String> location;

    @FXML
    private JFXTextArea contenu;

    @FXML
    private JFXComboBox<String> catégories;

    @FXML
    private Button video;

    @FXML
    private Label videotitle;

    PublicationService ps = new PublicationService();

    private final String[] locations = {"Ariana", "Ben Arous", "Tunis", "Manouba", "Sfax", "Béja", "Bizerte", "Gabes", "Gafsa", "Jendouba",
        "Kairaouan", "Kasserine", "Kébili", "Le Kef", "Mahdia", "Médenine", "Monastir", "Nabeul", "Sidi Bouzid",
        "Siliana", "Sousse", "Tataouine", "Tozeur", "Zaghouan"};

    public static Publication publication = null;

    //Loading Categories
    CatégoriePublicationService cps = new CatégoriePublicationService();
    ObservableList<CatégoriePublication> oblist = FXCollections.observableArrayList();
    HashMap<Integer, String> catégoriesMap = new HashMap();
    HashMap<String, Integer> catégoriesMap1 = new HashMap();

    @FXML
    void uploadVidéo(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choisir un vidéo");
        Stage s = (Stage) ancorPane.getScene().getWindow();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP4", "*.mp4"),
                new FileChooser.ExtensionFilter("AVI", "*.avi"),
                new FileChooser.ExtensionFilter("WMV", "*.wmv")
        );
        File f = chooser.showOpenDialog(s);
        Path path = Paths.get(System.getProperty("user.home") + "/Desktop/TGTVideos");
        Files.createDirectories(path);
        File publicationFile = new File(path.toString() + "/" + f.getName());
        try {
            copyFile(f, publicationFile);
        } catch (FileAlreadyExistsException e) {
            AlertMaker.showErrorMessage("Fichier Existant", e.getMessage());
        }
        videotitle.setText(publicationFile.toURI().toString());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oblist.addAll(cps.afficher());
        System.out.println(oblist);
        for (CatégoriePublication catégoriePublication : oblist) {
            catégories.getItems().add(catégoriePublication.getNomCat());
            catégoriesMap.put(catégoriePublication.getId_cat(), catégoriePublication.getNomCat());
            catégoriesMap1.put(catégoriePublication.getNomCat(), catégoriePublication.getId_cat());
        }
        contenu.setText(publication.getContenu());
        location.setPromptText(publication.getLocalisation());
        catégories.setValue(catégoriesMap.get(publication.getId_cat()));
        videotitle.setText(publication.getVideo());
        location.getItems().addAll(locations);
    }

    private static void copyFile(File src, File dest) throws IOException {
        Files.copy(src.toPath(), dest.toPath());
    }

    @FXML
    void modifierLocalisation(ActionEvent event) throws IOException {
        if (location.getValue() == null) {
            location.setValue(ps.parseAddress());
            AlertMaker.showSimpleAlert("Localisation Modifiée", "Votre position " + ps.parseAddress() + " a été pris comme localisation !");
        } else {
            AlertMaker.showSimpleAlert("Localisation Modifiée", "Votre nouvelle position est " + location.getSelectionModel().getSelectedItem());
        }
    }

    public void ModifierPublication(Publication p,Stage stage,Label contenu1,Label position,MediaView mediaView) {
        btnpublier.addEventHandler(ActionEvent.ACTION, (event) -> {
            int indexCat = catégoriesMap1.get(catégories.getSelectionModel().getSelectedItem());
            Publication nouvellePub = new Publication(p.getId_pub(),contenu.getText(), videotitle.getText(), location.getValue(), indexCat);
            ps.modifier(nouvellePub);
            AlertMaker.showSimpleAlert("Publication Modifiée", "Publication Modifiée avec Succés");
            stage.close();
            contenu1.setText(contenu.getText());
            position.setText(location.getSelectionModel().getSelectedItem());
            mediaView.setMediaPlayer(new MediaPlayer(new Media(videotitle.getText())));
        });
    }
}
