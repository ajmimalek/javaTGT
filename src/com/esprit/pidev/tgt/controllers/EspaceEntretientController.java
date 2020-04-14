/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Contrat;
import com.esprit.pidev.tgt.services.CandidatService;
import com.esprit.pidev.tgt.services.ContratService;
import com.esprit.pidev.tgt.services.EntretientService;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class EspaceEntretientController implements Initializable {
    private Candidat candidat;
    private Contrat contrat;
    private BackOficeController backOficeController;
    private EntretientService entretientService = new EntretientService();
    private ContratService contratService = new ContratService();
    
    

    @FXML
    private Label nomC;
    @FXML
    private Label titreCasting;
    @FXML
    private JFXTextField note;
    @FXML
    private JFXButton accepter_condidat;
    @FXML
    private JFXButton refuser_codidat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    }    

     private void initlabel() {
        nomC.setText(candidat.getNomC());
        titreCasting.setText(candidat.getCasting().getTitreCasting());
        
    
         
     }
    
       void initfields(Candidat selectedCandidat, BackOficeController backOficeController) {
       this.candidat=selectedCandidat;
       this.backOficeController= backOficeController;
       initlabel();
    }

    @FXML
    private void accepter(ActionEvent event) throws SQLException {
        this.candidat.getEntretient().setNoteEnt(Integer.valueOf(this.note.getText())); 
         System.out.println(candidat);
            entretientService.notter(candidat.getEntretient());
         FXMLLoader loader = Rooting.navigate("call", "ContratFormulaire");
          ContratFormulaireController controller = (ContratFormulaireController) loader.getController();
         controller.initfields(candidat,this); 
        
    }

    @FXML
    private void refuser(ActionEvent event) {
    }


    
}
