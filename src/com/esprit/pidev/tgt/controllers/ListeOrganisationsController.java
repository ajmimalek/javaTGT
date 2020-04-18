/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Organisations;
import com.esprit.pidev.tgt.services.IOrganisationsService;
import com.esprit.pidev.tgt.services.OrganisationsService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class ListeOrganisationsController implements Initializable {
    
     ObservableList<Organisations> organisationstList = FXCollections.observableArrayList();
       IOrganisationsService organisationservice = new OrganisationsService();
       private OrganisationsService organisationsService = new OrganisationsService();
               

    @FXML
    private TableView<Organisations> ListeOrganisations;
    @FXML
    private TableColumn<Organisations, String> NomOrganisation;
    @FXML
    private TableColumn<Organisations, String> Apropos;
    @FXML
    private TableColumn<Organisations, String> AdresseOrganisation;
    @FXML
    private TableColumn<Organisations, Integer> tel_organisation;
    @FXML
    private TableColumn<Organisations, String> Email_Org;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      initCol();
      loadData();
    }    
   
  
   
    private void initCol() {
                 NomOrganisation.setCellValueFactory(new PropertyValueFactory<>("NomOrganisation"));
                 Apropos.setCellValueFactory(new PropertyValueFactory<>("Apropos"));
                 AdresseOrganisation.setCellValueFactory(new PropertyValueFactory<>("AdresseOrganisation"));
                 tel_organisation.setCellValueFactory(new PropertyValueFactory<>("tel_organisation"));
                 Email_Org.setCellValueFactory(new PropertyValueFactory<>("Email_Org"));
    }
  
     public void loadData() {
       

        try {
            organisationstList = FXCollections.observableArrayList(organisationservice.afficherOrganisations());
            System.out.println(organisationstList);
             } catch (SQLException ex) {
            
        }
   
        ListeOrganisations.setItems(organisationstList);
}
}
