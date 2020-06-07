/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.utils.ConectedUser;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author Seif Henchir
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
    @FXML
    private AnchorPane main;
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
    private ImageView image;

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
        Rooting.navigate("titre", "ChangerMdp");
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

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void GestionPublications(ActionEvent event) {
    }

      public static String saveToFileImageNormal(Image image) throws SQLException, IOException {
        
        String ext = "jpg";
        File dir = new File("C:/wamp64/www/javaTGT/src/com/esprit/pidev/tgt/views/resources");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
        return outputFile.toURI().toString();
    }
        
    @FXML
    private void addImage(MouseEvent event) {
            FileChooser fc = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image imageF = SwingFXUtils.toFXImage(bufferedImage, null);
            image.setImage(imageF);
        } catch (IOException ex) {
            System.out.println("add image");
        }
    }
}
