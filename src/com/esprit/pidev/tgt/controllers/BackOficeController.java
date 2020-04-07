/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Entretien;
import com.esprit.pidev.tgt.enumeration.StatutEnt;
import com.esprit.pidev.tgt.services.CandidatService;
import com.esprit.pidev.tgt.services.ICandidatService;
import com.esprit.pidev.tgt.utils.Rooting;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class BackOficeController implements Initializable {

     ObservableList<Candidat> candidatList = FXCollections.observableArrayList();
     ICandidatService candidatservice = new CandidatService();
    @FXML
    private Button gestionCandidature;
    @FXML
    private TableView<Candidat> tableViewCandidat;
    @FXML
    private TableColumn<Candidat, Integer> cin;
    @FXML
    private TableColumn<Candidat, String> nomC;
    @FXML
    private TableColumn<Candidat, String> addressmail;
    @FXML
    private TableColumn<Candidat, Integer> numtel;
    @FXML
    private TableColumn<Candidat, String> cv;
    @FXML
    private TableColumn<Candidat, String> motivation;
    @FXML
    private TableColumn<Candidat, String> nomCasting;
    @FXML
    private TableColumn<Candidat, String> statut;
    @FXML
    private Button recherchecandidat;
     @FXML
    private Button modifier;

    @FXML
    private Button suprimer;



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
         cin.setCellValueFactory(new PropertyValueFactory<>("cinCondidat"));
         nomC.setCellValueFactory(new  PropertyValueFactory<>("nomC"));
         addressmail.setCellValueFactory(new PropertyValueFactory<>("mailaddress"));
         numtel.setCellValueFactory(new PropertyValueFactory<>("tel"));
         cv.setCellValueFactory(new PropertyValueFactory<>("cv"));
         motivation.setCellValueFactory(new PropertyValueFactory<>("motivation"));
         statut.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Candidat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Candidat, String> param) {
               String result = param.getValue().getEntretient().getStatutEnt().toString();
                return new SimpleObjectProperty<String>(result);
            }
        });
         
          nomCasting.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Candidat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Candidat, String> param) {
               String result = param.getValue().getCasting().getTitreCasting();
                return new SimpleObjectProperty<String>(result);
            }
        });
         
     }
     private void loadData() {
       

        try {
            candidatList = FXCollections.observableArrayList(candidatservice.findAll());
            System.out.println(candidatList);
        } catch (SQLException ex) {
            
        }
        
         

        tableViewCandidat.setItems(candidatList);
    }

    @FXML
    void modifier(ActionEvent event) {
     Candidat selectedCandidat  = tableViewCandidat.getSelectionModel().getSelectedItem();
        if (selectedCandidat==null){
            System.out.println("choisir un candidat");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "CondidatFormulaire");
        CondidatFormulaireController controller = (CondidatFormulaireController) loader.getController();
         controller.setcandidat(selectedCandidat);
        
        }
        
    }

    @FXML
    void showDetaille(ActionEvent event) {

    }

    @FXML
    void suprimer(ActionEvent event) {

    }
}
