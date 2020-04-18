/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.services.IUtilisateurService;
import com.esprit.pidev.tgt.services.UtilisateurService;
import com.esprit.pidev.tgt.utils.Rooting;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class BackOficeProfileController implements Initializable {
 ObservableList<Utilisateur> utilisateurtList = FXCollections.observableArrayList();
     IUtilisateurService utilisateurservice = new UtilisateurService();
    @FXML
    private Button gestionCandidature;
    @FXML
    private TableView<Utilisateur> tableViewUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> nom;
    @FXML
    private TableColumn<Utilisateur, String> prenom;
    @FXML
    private TableColumn<Utilisateur, String> date;
    @FXML
    private TableColumn<Utilisateur, String> genre;
    @FXML
    private TableColumn<Utilisateur, String> numero;
    @FXML
    private TableColumn<Utilisateur, String> email;
    @FXML
    private TableColumn<Utilisateur, String> avatarUrl;
    @FXML
    private TableColumn<Utilisateur, String> username;
    @FXML
    private TableColumn<Utilisateur, String> password;
    @FXML
    private TextField l1;
    @FXML
    private Button recherchecandidat;
    @FXML
    private Button modifier;
    @FXML
    private Button suprimer;

    @FXML
    private Button attribuerRole;


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
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenom.setCellValueFactory(new  PropertyValueFactory<>("prenom"));
         date.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
         genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
         numero.setCellValueFactory(new PropertyValueFactory<>("numTel"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
         avatarUrl.setCellValueFactory(new PropertyValueFactory<>("avatarUrl"));
         
         
//         username.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Compte, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Compte, String> param) {
//               String result = param.getValue().getCompte().getUserName().toString();
//                return new SimpleObjectProperty<String>(result);
//            }
//        });
//         
//          nomCasting.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Candidat, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Candidat, String> param) {
//               String result = param.getValue().getCasting().getTitreCasting();
//                return new SimpleObjectProperty<String>(result);
//            }
//        });
         
     }
     
      public void loadData() {
       

        try {
            utilisateurtList = FXCollections.observableArrayList(utilisateurservice.findAll());
            System.out.println(utilisateurtList);
        } catch (SQLException ex) {
            
        }
        
        tableViewUtilisateur.setItems(utilisateurtList);

    }
    
    
    @FXML
    private void modifier(ActionEvent event) {
         Utilisateur selectedUtilisateur  = tableViewUtilisateur.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur==null){
            System.out.println("choisir un utilisateur");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "modifierProfile");
        ModifierProfileController controller = (ModifierProfileController) loader.getController();
        controller.initfields(selectedUtilisateur,this);
        
    }
    }
    @FXML
    private void suprimer(ActionEvent event) {
          Utilisateur selectedUtilisateur  = tableViewUtilisateur.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur==null){
            System.out.println("choisir un utilisateur");
            
        }else{
         
          FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionProfile");
        ValidationDeSuppressionProfileController controller = (ValidationDeSuppressionProfileController) loader.getController();
         controller.initfields(selectedUtilisateur,this);
    }

    }  
}
