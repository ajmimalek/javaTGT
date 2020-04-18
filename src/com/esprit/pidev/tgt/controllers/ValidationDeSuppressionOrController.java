/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.entities.Organisations;
import com.esprit.pidev.tgt.services.CandidatService;
import com.esprit.pidev.tgt.services.CastingService;
import com.esprit.pidev.tgt.services.EventsService;
import com.esprit.pidev.tgt.services.OrganisationsService;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class ValidationDeSuppressionOrController implements Initializable {

    private Statement statement;
    private OrganisationsService organisationsService = new OrganisationsService();
    private Organisations organisations;
    
    private CastingService castingService = new CastingService();
    private Casting casting;
    
    private EventsService eventsService = new EventsService();
    private Events events;
    
    private BackOfficeOrganisationsController backOfficeOrganisationsController;
    
    
 
    
    
    @FXML
    private Label attention;
    @FXML
    private Button supprimerOrganisations;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//**************Organisations
    @FXML
    private void supprimerOrganisations(ActionEvent event) throws SQLException {
        
        
        organisationsService.supprimerOrganisations(organisations);
        backOfficeOrganisationsController.loadData();
         closeStage();
    }
    

  
     public void initfieldsOrg(Organisations organisations, BackOfficeOrganisationsController backOfficeOrganisationsController){
     this.organisations=organisations;
     this.backOfficeOrganisationsController= backOfficeOrganisationsController;
     }
     
  
     
   //**************Casting  
     @FXML
    private void supprimerCasting(ActionEvent event) throws SQLException {
        
        
        castingService.supprimerCasting(casting);
        backOfficeOrganisationsController.loadData();
         closeStage();
    }
       
         
      public void initfieldsCasting(Casting casting, BackOfficeOrganisationsController backOfficeOrganisationsController){
     this.casting=casting;
     this.backOfficeOrganisationsController= backOfficeOrganisationsController;

      }
     
   //*************Events
      
       @FXML
    private void supprimerEvents(ActionEvent event) throws SQLException {
        
        
        eventsService.supprimerEvents(events);
        backOfficeOrganisationsController.loadData();
         closeStage();
    }
       
      public void initfieldsEvents(Events events, BackOfficeOrganisationsController backOfficeOrganisationsController){
     this.events=events;
     this.backOfficeOrganisationsController= backOfficeOrganisationsController;

      }
      
      
      
      
    //************************Abondonner 
        @FXML
    private void abendonner(ActionEvent event) {
        closeStage();
    }
    
      private void closeStage() {
        ((Stage) attention.getScene().getWindow()).close();
    }
         
  


}
