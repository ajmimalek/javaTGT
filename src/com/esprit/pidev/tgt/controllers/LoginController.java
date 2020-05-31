/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer1;
    @FXML
    private Label a2;
    @FXML
    private TextField u1;
    @FXML
    private TextField u2;
    @FXML
    private TextField u3;
    @FXML
    private JFXButton btnsignup;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private TextField n1;
    @FXML
    private PasswordField n2;
    @FXML
    private AnchorPane layer2;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private JFXButton signin;
    @FXML
    private JFXButton signup;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
    @FXML
    private Label b2;
    @FXML
    private StackPane stackPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s1.setVisible(false);
        s2.setVisible(false);
        b2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        btnsignin.setVisible(false);
        n1.setVisible(false);
        n2.setVisible(false);
        u1.setVisible(true);
        u2.setVisible(true);
        u3.setVisible(true);
    }

    @FXML
    private void btn(MouseEvent event) {
       TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);
        b2.setVisible(true);
        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        n2.setVisible(true);
        u1.setVisible(false);
        u2.setVisible(false);
        u3.setVisible(false);

        slide.setOnFinished((e -> {

        }));
    }

    @FXML
    private void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);
        btnsignin.setVisible(false);

        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        signin.setVisible(true);
        a2.setVisible(true);
        btnsignup.setVisible(true);
        n1.setVisible(false);
        n2.setVisible(false);
        u1.setVisible(true);
        u2.setVisible(true);
        u3.setVisible(true);
        b2.setVisible(false);

        slide.setOnFinished((e -> {

        }));
    }

    @FXML
    private void Connexion(ActionEvent event) {
        Stage stage = new Stage();
        Parent root = null;
        if ((n1.getText().equals("ajmimalek"))&&(n2.getText().equals("123"))){
            Rooting.navigate("Espace Administrateur", "BackOffice");
            stackPanel.getScene().getWindow().hide();
        } else if ((n1.getText().equals("malek123"))&&(n2.getText().equals("saveme"))) {
            Rooting.navigate("Acceuil", "Main");
            stackPanel.getScene().getWindow().hide();
        } else {
            AlertMaker.showErrorMessage("Username et Mot de passe manquant", "Veuillez mentionner votre username et votre mot de passe");
        }
        
    }

    @FXML
    private void Inscription(ActionEvent event) {
    }

}
