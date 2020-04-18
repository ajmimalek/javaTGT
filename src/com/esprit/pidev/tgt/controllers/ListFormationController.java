/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.esprit.pidev.tgt.entities.Cour;
import com.esprit.pidev.tgt.services.CourService;
import com.esprit.pidev.tgt.services.ICourService;
import com.esprit.pidev.tgt.entities.Formation;
import com.esprit.pidev.tgt.services.FormationService;
import com.esprit.pidev.tgt.services.IFormationService;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Nadhem
 */
public class ListFormationController implements Initializable {
     ObservableList<Formation> formationList = FXCollections.observableArrayList();
     IFormationService formationservice = new FormationService();
     ObservableList<Cour> courList = FXCollections.observableArrayList();
     ICourService courservice = new CourService();
    ObservableList<Formation> ParticiperList = FXCollections.observableArrayList();
    @FXML
    private TableView<Formation> tableViewFormation;
   
    @FXML
    private TableColumn<Formation, String> Image;
    @FXML
    private TableColumn<Formation, String> Titre;
    @FXML
    private TableColumn<Formation, String> Description;
    @FXML
    private TableColumn<Formation, Integer> Duree;
    @FXML
    private TableColumn<Formation, Integer> Note;
    @FXML
    private JFXTextField recherche;
    @FXML
    private Button rechercheFormation;
    @FXML
    private Button modifier;
    @FXML
    private Button suprimer;
    @FXML
    private Button Ajout_formation;
    @FXML
    private Button gestionCandidature;
    @FXML
    private TableView<Cour> tableViewCour;
    @FXML
    private Button supprimerCour;
    @FXML
    private Button ajouterCour;
    @FXML
    private TableColumn<Cour, String> image_cour;
    @FXML
    private TableColumn<Cour, String> titre_cour;
    @FXML
    private TableColumn<Cour, String> description_cour;
    @FXML
    private TableColumn<Cour, Integer> duree_cour;
    @FXML
    private TableColumn<Cour, Integer> note_cour;
    @FXML
    private TextField note;
    @FXML
    private ComboBox<String> trie;
    ObservableList <String> ListeTrie = FXCollections.observableArrayList("Trier Titre ASC", "Trier Titre DESC","Trier Duree ASC","Trier Duree DESC");
    @FXML
    private TableView<Formation> tableViewMaliste;
    @FXML
    private TableColumn<Formation, String> image_formation1;
    @FXML
    private TableColumn<Formation, String> titre_formation1;
    @FXML
    private TableColumn<Formation, String> description_formation1;
    @FXML
    private TableColumn<Formation, Integer> duree_formation1;
    @FXML
    private TableColumn<Formation, Integer> note_formation1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        trie.setItems(ListeTrie) ;
        loadData();
        tableViewFormation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            
            try {

               showCours(tableViewFormation.getSelectionModel().getSelectedItem().getFormation_id());
               
              //System.out.println(tableViewFormation.getSelectionModel().getSelectedItem().getFormation_id());
            } catch (SQLException ex) {
                Logger.getLogger(ListFormationController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        });
    }    

     private void initCol() {
         Image.setCellValueFactory(new PropertyValueFactory<>(" image"));
         Titre.setCellValueFactory(new  PropertyValueFactory<>("titre_formation"));
         Description.setCellValueFactory(new PropertyValueFactory<>("description_formation"));
         Duree.setCellValueFactory(new PropertyValueFactory<>("duree_formation"));
         Note.setCellValueFactory(new PropertyValueFactory<>("note"));
         
         image_cour.setCellValueFactory(new PropertyValueFactory<>(" image"));
         titre_cour.setCellValueFactory(new  PropertyValueFactory<>("titre_cour"));
         description_cour.setCellValueFactory(new PropertyValueFactory<>("description_cour"));
         duree_cour.setCellValueFactory(new PropertyValueFactory<>("duree_cour"));
         note_cour.setCellValueFactory(new PropertyValueFactory<>("note_cour"));
         
         image_formation1.setCellValueFactory(new PropertyValueFactory<>(" image"));
         titre_formation1.setCellValueFactory(new  PropertyValueFactory<>("titre_formation"));
         description_formation1.setCellValueFactory(new PropertyValueFactory<>("description_formation"));
         duree_formation1.setCellValueFactory(new PropertyValueFactory<>("duree_formation"));
         note_formation1.setCellValueFactory(new PropertyValueFactory<>("note_formation"));
         
     }
     
    public void loadData() {
       

        try {
            formationList = FXCollections.observableArrayList(formationservice.findAll());
            System.out.println(formationList);
            courList = FXCollections.observableArrayList(courservice.findAll());
            System.out.println(courList);
        } catch (SQLException ex) {
            
        }
         

        tableViewFormation.setItems(formationList);
        tableViewCour.setItems(courList);

    }

      
      
    @FXML
    private void showDetaille(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
         Formation selectedFormation  = (Formation) tableViewFormation.getSelectionModel().getSelectedItem();
        if (selectedFormation==null){
            System.out.println("choisir un formation");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "FormationFormulaire");
        FormationFormulaireController controller = (FormationFormulaireController) loader.getController();
         controller.initfields(selectedFormation,this);
        
        }
    }

    // Controlleur modification cour 
     @FXML
    private void modifierC(ActionEvent event) {
         Cour selectedCour  = (Cour) tableViewCour.getSelectionModel().getSelectedItem();
        if (selectedCour==null){
            System.out.println("choisir un cour");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "CourFormulaire");
        CourFormulaireController controller = (CourFormulaireController) loader.getController();
         controller.initfields(selectedCour,this);
        
        }
    }
    
    
    
    @FXML
    private void suprimer(ActionEvent event) {
       Formation selectedFormation  = (Formation)tableViewFormation.getSelectionModel().getSelectedItem();
        if (selectedFormation== null){
            System.out.println("choisir un formation");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionFormation");
        ValidationDeSuppressionFormationController controller = (ValidationDeSuppressionFormationController) loader.getController();
         controller.initfields(selectedFormation,this);
        
        }
    }
    
    
    //suppression du cour 
    @FXML
    private void suprimerC(ActionEvent event) {
       Cour selectedCour  = (Cour)tableViewCour.getSelectionModel().getSelectedItem();
        if (selectedCour== null){
            System.out.println("choisir un cour");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionCour");
        ValidationDeSuppressionCourController controller = (ValidationDeSuppressionCourController) loader.getController();
         controller.initfields(selectedCour,this);
        
        }
    }
    
   

    @FXML
    private void AjoutFormation(ActionEvent event) {
        Rooting.navigate("main", "FormationFormulaire");
    }


    @FXML
    private void AjoutCour(ActionEvent event) {
Rooting.navigate("main", "CourFormulaire");
    }

    @FXML
    private void affectationDate(Event event) {
    }

    private void showCours(int formation_id) throws SQLException {
       ObservableList<Cour> cours = FXCollections.observableArrayList( courservice.findByFormationId(formation_id));       
        tableViewCour.setItems(cours);
        tableViewCour.refresh();
        //System.out.println(cours);
    }

    
    @FXML
    private void Rechercher (ActionEvent event) throws SQLException {
        String val = this.recherche.getText();
       ObservableList<Formation> formations = FXCollections.observableArrayList( formationservice.Recherche(val));       
        tableViewFormation.setItems(formations);
        tableViewFormation.refresh();
       // System.out.println("wselt controller zeda");
       // System.out.println(val);
        //System.out.println(cours);
    }
    
    
     @FXML
    private void Trie (ActionEvent event) throws SQLException {
      //String output = trie.getSelectionModel().getSelectedItem().toString();
      String output1 = trie.getValue();
      //System.out.println(output);
      System.out.println(output1);
      switch(output1) {
  case "Trier Titre ASC":
ObservableList<Formation> formations = FXCollections.observableArrayList( formationservice.TrieTitreASC());       
        tableViewFormation.setItems(formations);
        tableViewFormation.refresh();    break;
  case "Trier Titre DESC":
ObservableList<Formation> formationss = FXCollections.observableArrayList( formationservice.TrieTitreDESC());       
        tableViewFormation.setItems(formationss);
        tableViewFormation.refresh();    break;
 
   case "Trier Duree ASC":
ObservableList<Formation> formationsss = FXCollections.observableArrayList( formationservice.TrieDureeASC());       
        tableViewFormation.setItems(formationsss);
        tableViewFormation.refresh();    break;
        
   case "Trier Duree DESC":
ObservableList<Formation> formationssss = FXCollections.observableArrayList( formationservice.TrieDureeDESC());       
        tableViewFormation.setItems(formationssss);
        tableViewFormation.refresh();    break;      
        
  default: System.out.println("selectionner un choix");
    // code block
}
      
    
    }
    
    
    
    @FXML
    private void Noter(ActionEvent event) throws SQLException {
        Formation selectedFormation  = (Formation)tableViewFormation.getSelectionModel().getSelectedItem();
        selectedFormation.setNote(Integer.parseInt(this.note.getText()));
        formationservice.updateNote(selectedFormation);
        int notes=Integer.parseInt(this.note.getText());
    }

    
      @FXML
    private void Participer(ActionEvent event) throws SQLException {
        Formation selectedFormation  = (Formation)tableViewFormation.getSelectionModel().getSelectedItem();
        ObservableList<Formation> Participerr = FXCollections.observableArrayList( formationservice.Participer(selectedFormation));       
        System.out.println(Participerr);
        tableViewMaliste.setItems(Participerr);
        tableViewMaliste.refresh();
    }
      
    
    
    
}
