/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.services.CompteService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.ConectedUser;
import static com.esprit.pidev.tgt.utils.ConectedUser.utilisateur;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Seif Henchir
 */
public class ChangerMdpController implements Initializable {

    private CompteService compteService = new CompteService();

    @FXML
    private Button modifier;
    @FXML
    private TextField nvpassword;
    @FXML
    private TextField rppassword;
    @FXML
    private JFXPasswordField anpssword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void changermdp(ActionEvent event) {
        String anpssword = this.anpssword.getText();
        String nvpassword = this.nvpassword.getText();
        String rppassword = this.rppassword.getText();
        if ( ConectedUser.getUtilisateur().getCompte().getPassword().length() != anpssword.length() ) {
            AlertMaker.showSimpleAlert("mot de passe incorect", "merci de saisire l ancien mot de passe");
        } else if (nvpassword.length() != rppassword.length()) {
            AlertMaker.showSimpleAlert("mot de passe incorect", " la confiramation est incorrecte  ");
        } else {
            Compte compte = new Compte(ConectedUser.getUtilisateur().getCompte().getId(), rppassword);
            System.out.println(compte);
            System.out.println(rppassword);
            try {
                boolean a = compteService.update(compte);
                AlertMaker.showSimpleAlert("succes", "mot de passe a changer");
                //closeStage();
            } catch (Exception ex) {
                // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
