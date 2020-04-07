/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.enumeration.Genre;
import com.esprit.pidev.tgt.services.IUtilisateurService;
import com.esprit.pidev.tgt.services.UtilisateurService;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.security.RootCertStore;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class LoginController implements Initializable {

    private IUtilisateurService  utilisateurService = new UtilisateurService();
    @FXML
    private JFXTextField loginUsername;
    @FXML
    private JFXPasswordField loginPassword;
    @FXML
    private JFXButton login;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXDatePicker dateNaissance;
    @FXML
    private JFXRadioButton genreHomme;
    @FXML
    private ToggleGroup genre;
    @FXML
    private JFXRadioButton genreFemme;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmePassword;
    @FXML
    private JFXTextField numTel;
    @FXML
    private JFXButton ajoutBoutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    

    @FXML
    private void login(ActionEvent event) {
        Rooting.navigate("main", "Main");
        closeStage();
    }

    @FXML
    private void ajouterUtilisateur(ActionEvent event) {
                String nom = this.nom.getText();
        String prenom = this.prenom.getText();
        LocalDate dateNaissance = this.dateNaissance.getValue();
        Genre genre = this.genreHomme.isSelected()? Genre.HOMME:Genre.FEMME;
        String numTel = this.numTel.getText();
        String email = this.email.getText();
        String username = this.username.getText();
        String password = this.password.getText();
        Compte compte = new Compte(username, password, null);
        Utilisateur utilisateur = new Utilisateur(nom, prenom, dateNaissance, genre, numTel, email, null, compte);
        System.out.println("com.esp");
         try {
            utilisateurService.save(utilisateur);
            Rooting.navigate("main", "FXML");
            closeStage();
        } catch (SQLException ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }
        private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }
}
