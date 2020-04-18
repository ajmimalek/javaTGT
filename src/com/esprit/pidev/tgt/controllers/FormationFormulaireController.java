/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Formation;
import com.esprit.pidev.tgt.services.FormationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nadhem
 */
public class FormationFormulaireController implements Initializable {

     private Statement statement;
    private FormationService formationService = new FormationService();
    private Formation formation;
    private ListFormationController ListFormationController;
    private ListFormation_1Controller ListFormation_1Controller;
    
    @FXML
    private JFXTextField titre_formation;
    @FXML
    private JFXTextField description_formation;
    @FXML
    private JFXTextField image;
    @FXML
    private JFXButton ajoutFormation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutFormation(ActionEvent event) {
        if(this.formation==null) this.formation= new Formation(0,0 ,null, null, null, 0, 0);
        this.formation.setTitre_formation(this.titre_formation.getText());
        this.formation.setDescription_formation(this.description_formation.getText()); 
        this.formation.setImage(this.description_formation.getText()); 
        System.out.println(formation);
         try {
            if(this.formation.getFormation_id()== 0){
            formationService.save(formation);
            
            }else{
            formationService.update(formation);
            ListFormationController.loadData();
            }
            closeStage();
        } catch (Exception ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     private void closeStage() {
        ((Stage) titre_formation.getScene().getWindow()).close();
    }
 public void setformation(Formation formation){
    this.formation=formation;
    initInput();
    }
    
    void initInput (){
    this.titre_formation.setText(formation.getTitre_formation());
    this.description_formation.setText(formation.getDescription_formation()+"");
       this.image.setText(formation.getImage());
      
    }

    void initfields(Formation selectedFormation, ListFormationController ListFormationController) {
       this.formation=selectedFormation;
       this.ListFormationController= ListFormationController;
       initInput();
    }
    
    void initfieldss(Formation selectedFormation, ListFormation_1Controller ListFormation_1Controller) {
       this.formation=selectedFormation;
       this.ListFormation_1Controller= ListFormation_1Controller;
       initInput();
    }
}
