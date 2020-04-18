/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.utils.ConectedUser;
import com.esprit.pidev.tgt.utils.Rooting;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class ProfileController implements Initializable {

    @FXML
    private Button modifpro;
    private ImageView photo;
    @FXML
    private Button changerpasse;
    @FXML
    private Label idnom;
    @FXML
    private Label idnumerotel;
    @FXML
    private Label idemail;
    @FXML
    private Label iddateden;
    @FXML
    private Label idgenre;
    @FXML
    private Label idprenom;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initdata();
     //   photo.setImage(new Image("../ABC.JPG"));
    }    

    @FXML
    private void modifier_profile(ActionEvent event) 
    {
        FXMLLoader loader = Rooting.navigate("titre", "modifierProfile");
        ModifierProfileController controller = (ModifierProfileController) loader.getController();
         controller.initfields(ConectedUser.getUtilisateur(),this);
    }

    @FXML
    private void changer_mot_passe(ActionEvent event) {
    }


    public void loadData(){
    initdata();
    }

    private void initdata() {
         Utilisateur conectedUser = ConectedUser.getUtilisateur();
        idnom.setText(conectedUser.getNom());
        idnumerotel.setText(conectedUser.getNumTel());
        idemail.setText(conectedUser.getEmail());
        iddateden.setText(conectedUser.getDateNaissance().toString());
        idgenre.setText(conectedUser.getGenre().toString());
        idprenom.setText(conectedUser.getPrenom());
    }

    @FXML
    private void changerph(ActionEvent event) {
    }
}
