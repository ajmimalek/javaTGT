/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Contrat;
import com.esprit.pidev.tgt.enumeration.TypeContrat;
import com.esprit.pidev.tgt.services.ContratService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class ContratFormulaireController implements Initializable {
    
     private Statement statement;
    private ContratService contratService = new ContratService();
    private Contrat contrat;
    private EspaceEntretientController espaceEntretientController;
     ObservableList<TypeContrat> list2 = FXCollections.observableArrayList();

    @FXML
    private JFXTextField salaire;
    @FXML
    private JFXTextField durer;
    @FXML
    private JFXButton ajoutCondidat;
    @FXML
    private JFXComboBox<TypeContrat> typecontrat;
    private Candidat candidat;
    @FXML
    private Label nomcand;
    @FXML
    private Label nomcast;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         list2 = FXCollections.observableArrayList(TypeContrat.values());
       typecontrat.setItems(list2);
        // TODO
    }    

    @FXML
    private void ajoutCondidat(ActionEvent event) {
         if(this.contrat==null) this.contrat= new Contrat(0,this.candidat.getCasting(),this.candidat , 0, null, 0);
        this.contrat.setSalaire(Float.valueOf(this.salaire.getText())); 
       
        this.contrat.setTypeContrat(this.typecontrat.getValue());
        
         this.contrat.setDureeContrat(Integer.valueOf(this.durer.getText()));
       // System.out.println(candidat);
         try {
                System.out.println(contrat);
            contratService.save(contrat);
            closeStage();

                       
        } catch (Exception ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    private void closeStage() {
        ((Stage) salaire.getScene().getWindow()).close();
    }
    
     void initfields(Candidat selectedCandidat, EspaceEntretientController espaceEntretientController) {
       this.candidat=selectedCandidat;
       this.espaceEntretientController = espaceEntretientController;
       initlabel();
    }
     
              private void initlabel() {
     nomcast.setText(candidat.getCasting().getTitreCasting());
     nomcand.setText(candidat.getNomC());
     
         
    
         
     }
}
