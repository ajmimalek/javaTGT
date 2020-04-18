package com.esprit.pidev.tgt.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.esprit.pidev.tgt.controllers.BackOfficeOrganisationsController;
import com.esprit.pidev.tgt.entities.Organisations;
import com.esprit.pidev.tgt.services.OrganisationsService;
import com.esprit.pidev.tgt.utils.Alert;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 * nlawj 3la l cnx , oussema base 3aml achtice ta3 lprojet -_-
 * @author islem
 */
public class OrganisationsFormulaireController implements Initializable {

    private Statement statement;
    private OrganisationsService organisationsService = new OrganisationsService();
    private Organisations organisations;
    private BackOfficeOrganisationsController backOfficeOrganisationsController;

    @FXML
    private Label lab1;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private TextArea apropos;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Button ajouterOrganisations;
    @FXML
    private ImageView logo;
    
   

    /**
     * Initializes the controller class.
     * mta3 mobile ydhohrli mdeclrinha string nn ahayka int email ya5dem whya la braby la7dha 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterOrganisations(ActionEvent event) {
            Organisations O = new Organisations();
        OrganisationsService os = new OrganisationsService();
        O.setNomOrganisation(nom.getText());
        O.setAdresseOrganisation(adresse.getText());
        O.setEmail_Org(email.getText());
        O.setTel_organisation(Integer.valueOf(tel.getText()));
        O.setApropos(apropos.getText());
        O.setLoginOrganisation(login.getText());
        O.setPasswordOrganisation(password.getText());
        
        if (validateEmaill() && validateMobileNo()  ){
            os.ajouterOrganisations(O);
            System.out.println(os);
        }
        
    }
    
    public void setorganisations(Organisations organisations){
    this.organisations=organisations;
    initInput();
    }
    
    
    void initInput (){
    this.nom.setText(organisations.getNomOrganisation());
    this.adresse.setText(organisations.getAdresseOrganisation());
    this.email.setText(organisations.getEmail_Org());
    this.tel.setText(organisations.getTel_organisation()+"");
    this.apropos.setText(organisations.getApropos());
    this.login.setText(organisations.getLoginOrganisation());
    this.password.setText(organisations.getPasswordOrganisation());

    }

    void initfields(Organisations selectedOrganisations, BackOfficeOrganisationsController backOfficeOrganisationsController) {
       this.organisations=selectedOrganisations;
       this.backOfficeOrganisationsController= backOfficeOrganisationsController;
       initInput();
    }
    
    
    // Validate Number Phone will work 
    
        private boolean validateMobileNo(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tel.getText());
        if(m.find() && m.group().equals(tel.getText())){
            return true;
        }else{
            
            return false;            
        }
    }
        
        // validate email wokring 
        
        private boolean validateEmaill(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if(m.find() && m.group().equals(email.getText())){
            return true;
        }else{
                System.out.println("email non valide");
            
            return false;            
        }
        }

   
}
