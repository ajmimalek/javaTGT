/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Cour;
import com.esprit.pidev.tgt.services.CourService;

import com.esprit.pidev.tgt.entities.Formation;
import com.esprit.pidev.tgt.services.FormationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nadhem
 */
public class CourFormulaireController implements Initializable {

     private Statement statement;
    private CourService courService = new CourService();
    private Cour cour;
    private ListFormationController ListFormationController;
    private ListFormation_1Controller ListFormation_1Controller;
    
    @FXML
    private JFXTextField titre_cour;
    @FXML
    private JFXTextField description_cour;
    @FXML
    private JFXTextField image_cour;
    @FXML
    private JFXButton ajoutCour;
    @FXML
    private JFXTextField duree_cour;
    @FXML
    private ComboBox<Formation> formation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficherCat();
        // TODO
    }    

    @FXML
    private void ajoutCour(ActionEvent event) {
        if(this.cour==null) this.cour= new Cour(0,0 ,null, null, 0, 0, null,null);
        this.cour.setTitre_cour(this.titre_cour.getText());
        this.cour.setDescription_cour(this.description_cour.getText()); 
        this.cour.setImage(this.description_cour.getText()); 
        this.cour.setDuree_cour(Integer.parseInt(this.duree_cour.getText()));
        this.cour.setFormation_id(this.formation.getValue().getFormation_id());
        System.out.println(cour);
         try {
            if(this.cour.getCour_id()== 0){
            
            courService.save(cour);
            }else{
                
                courService.update(cour);
        
            ListFormationController.loadData();
            }
            closeStage();
        } catch (Exception ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficherCat(){
        FormationService formationservice= new FormationService();
       System.out.println("1");
        ArrayList array = (ArrayList) formationservice.getall();
        ObservableList observableList = FXCollections.observableArrayList(array);
        formation.setItems(observableList);
    }
    
    
     private void closeStage() {
        ((Stage) titre_cour.getScene().getWindow()).close();
    }
 public void setcour(Cour cour){
    this.cour=cour;
    initInput();
    }
    
    void initInput (){
    this.titre_cour.setText(cour.getTitre_cour());
    this.description_cour.setText(cour.getDescription_cour()+"");
       this.image_cour.setText(cour.getImage());
      
    }

    void initfields(Cour selectedCour, ListFormationController ListFormationController) {
       this.cour=selectedCour;
       this.ListFormationController= ListFormationController;
       initInput();
    }
    void initfieldss(Cour selectedCour, ListFormation_1Controller ListFormation_1Controller) {
       this.cour=selectedCour;
       this.ListFormation_1Controller= ListFormation_1Controller;
       initInput();
    }
}
