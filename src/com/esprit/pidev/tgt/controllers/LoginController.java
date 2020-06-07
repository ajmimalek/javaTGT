/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.enumeration.Genre;
import com.esprit.pidev.tgt.services.CompteService;
import com.esprit.pidev.tgt.services.UtilisateurService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.esprit.pidev.tgt.utils.ConectedUser;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Seif Henchir
 */
public class LoginController implements Initializable {

    private UtilisateurService utilisateurService = new UtilisateurService();
    private CompteService compteService = new CompteService();

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer1;
    @FXML
    private Label a2;
//    private TextField u1;
//    private TextField u2;
//    private TextField u3;
//    private JFXButton btnsignup;
    
    @FXML
    private Label b2;
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
    private StackPane stackPanel;
    @FXML
    private JFXButton login;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private TextField prenom;
    @FXML
    private TextField numTel;
    @FXML
    private TextField password;
    @FXML
    private JFXButton ajoutBoutton;
    @FXML
    private TextField username;
    @FXML
    private JFXDatePicker dateNaissance;
    @FXML
    private JFXRadioButton genreHomme;
    @FXML
    private JFXRadioButton genreFemme;
    @FXML
    private TextField confirmePassword;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private Label i1;
    @FXML
    private Label i2;
    @FXML
    private Label i3;
    @FXML
    private Label i4;
    @FXML
    private Label i6;
    @FXML
    private Label i7;
    @FXML
    private Label i8;
    @FXML
    private Label i9;
    @FXML
    private Label i5;
    @FXML
    private JFXButton oublier;
public  Utilisateur utilisateurs;
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
        login.setVisible(false);
        oublier.setVisible(false);
        
        loginUsername.setVisible(false);
        loginPassword.setVisible(false);

    }

    @FXML
    private void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        login.setVisible(true);
        oublier.setVisible(true);
        a2.setVisible(false);
        b2.setVisible(true);
        
        
        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        signin.setVisible(false);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        
        

        loginUsername.setVisible(true);
        loginPassword.setVisible(true);

        
         i1.setVisible(false);
         i2.setVisible(false);
         i3.setVisible(false);
         i4.setVisible(false);
         i5.setVisible(false);
         i6.setVisible(false);
         i7.setVisible(false);
         i8.setVisible(false);
         i9.setVisible(false);
         
         nom.setVisible(false);
         prenom.setVisible(false);
         dateNaissance.setVisible(false);
         genreHomme.setVisible(false);
         genreFemme.setVisible(false);
         email.setVisible(false);
         numTel.setVisible(false);
         username.setVisible(false);
         password.setVisible(false);
         confirmePassword.setVisible(false);
         ajoutBoutton.setVisible(false);
         

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
        
        login.setVisible(false);
        oublier.setVisible(false);

        a2.setVisible(true);
        b2.setVisible(false);
        
        
        
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        signin.setVisible(true);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        
        

        loginUsername.setVisible(false);
        loginPassword.setVisible(false);



         i1.setVisible(true);
         i2.setVisible(true);
         i3.setVisible(true);
         i4.setVisible(true);
         i5.setVisible(true);
         i6.setVisible(true);
         i7.setVisible(true);
         i8.setVisible(true);
         i9.setVisible(true);
         
         nom.setVisible(true);
         prenom.setVisible(true);
         dateNaissance.setVisible(true);
         genreHomme.setVisible(true);
         genreFemme.setVisible(true);
         email.setVisible(true);
         numTel.setVisible(true);
         username.setVisible(true);
         password.setVisible(true);
         confirmePassword.setVisible(true);
         ajoutBoutton.setVisible(true);
         
        

        slide.setOnFinished((e -> {

        }));
        }

    

    
    
    
    @FXML
    private void login(ActionEvent event) {
       try{
        Utilisateur utilisateur = utilisateurService.findByUsername(this.loginUsername.getText());
        
        if (utilisateur!= null && utilisateur.getCompte().getPassword().equals(this.loginPassword.getText())&&
                utilisateur.getCompte().getRoleType().equals(utilisateur.getCompte().getRoleType().talent)
                ){
            System.out.println(utilisateur);
            ConectedUser.setUtilisateur(utilisateur);
            
           
            
            
            Rooting.navigate("Profile", "profile");
             closeStage();
         
        }else if (utilisateur!= null && utilisateur.getCompte().getPassword().equals(this.loginPassword.getText())&&
                utilisateur.getCompte().getRoleType().equals(utilisateur.getCompte().getRoleType().admin)
                ){
            System.out.println(utilisateur);
            ConectedUser.setUtilisateur(utilisateur);
            
           
            
            
                    Rooting.navigate("Admin", "BackOfficeProfile");
                    closeStage();
       }else {
            AlertMaker.showErrorMessage("invalid password or login", "merci de saisire le mdp or login valide");
 
        }
        }catch(SQLException ex){
        
        }
        
    }
    
    
    @FXML
    private void ajouterUtilisateur(ActionEvent event) {
        boolean ok = true;
        
        String nom = this.nom.getText();
        String prenom = this.prenom.getText();
        LocalDate dateNaissance = this.dateNaissance.getValue();
        Genre genre = this.genreHomme.isSelected()? Genre.HOMME:Genre.FEMME;
        String numTel = this.numTel.getText();
        String email = this.email.getText();
        String username = this.username.getText();
        String password = this.password.getText();
        String confirmePassword = this.confirmePassword.getText();
        if(!email.matches("[^@]+@[^\\.]+\\..+")){
           
             ok=false;
            AlertMaker.showErrorMessage("invalid mail", "suive ce pattern ([^@]+@[^\\.]+\\..+) stp");}
             else if ( nom.length()==0 || prenom.length()==0 || username.length()==0   ) {
            
                    AlertMaker.showErrorMessage("chanps vide ", "le nom ,prenom et username  ne doit pas etre vide");}
                    else  if( password.length() != confirmePassword.length() ){
                         AlertMaker.showErrorMessage(" password incorrect ", " le mot de passe et la confirmation doit etre identique ");
                     }
                         
            
            
        else {  
        Compte compte = new Compte(username, password, null);
        Utilisateur utilisateur = new Utilisateur(nom, prenom, dateNaissance, genre, numTel, email, null, compte);
        System.out.println("com.esp");
         try {
            utilisateurService.save(utilisateur);
            AlertMaker.showSimpleAlert("iscription", "inscription avec succes");
           // ConectedUser.setUtilisateur(utilisateur);
            Rooting.navigate("login", "login");
            closeStage();
        } catch (SQLException ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
       

    }

    
    
    
        private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
          }

    @FXML
    private void Recuperation(ActionEvent event) {
         Rooting.navigate("Admin", "RecupMdp");
                   closeStage();
    }

}
