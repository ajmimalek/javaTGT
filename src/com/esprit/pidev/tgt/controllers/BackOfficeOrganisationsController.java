/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.entities.Organisations;
import com.esprit.pidev.tgt.services.CastingService;
import com.esprit.pidev.tgt.services.EventsService;
import com.esprit.pidev.tgt.services.ICastingService;
import com.esprit.pidev.tgt.services.IEventsService;
import com.esprit.pidev.tgt.services.IOrganisationsService;
import com.esprit.pidev.tgt.services.OrganisationsService;
import com.esprit.pidev.tgt.utils.Rooting;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;

        

import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class BackOfficeOrganisationsController implements Initializable {
       ObservableList<Organisations> organisationstList = FXCollections.observableArrayList();
       IOrganisationsService organisationservice = new OrganisationsService();
       private OrganisationsService organisationsService = new OrganisationsService();
               
        ObservableList<Casting> castingList = FXCollections.observableArrayList();
        ICastingService castingservice = new CastingService();
        private CastingService castingService = new CastingService();
               
        ObservableList<Events> eventsList = FXCollections.observableArrayList();
        IEventsService eventsservice = new EventsService();
        private EventsService eventsService = new EventsService();
        
        
        EventsService es =new EventsService();
        OrganisationsService os =new OrganisationsService();
        CastingService cs = new CastingService();
 
    @FXML
    private Button gestionOrganisation;
    @FXML
    private Tab afficheOrganisation;
    @FXML
    private TextField recherche;
    @FXML
    private Button rechercheOrganisations;
    @FXML
    private TableView<Organisations> tableViewOrganisations;
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
    @FXML
    private TableColumn<Organisations, String> LoginOrganisation;
    @FXML
    private TableColumn<Organisations, String> PasswordOrganisation;
    @FXML
    private Button ajouterOrganisations;
    @FXML
    private Button modifierOrganisations;
    @FXML
    private Button supprimerOrganisations;
    @FXML
    private TableView<Casting> tableViewCasting;
    @FXML
    private TableColumn<Casting, String> TitreCasting;
    @FXML
    private TableColumn<Casting, String> DescriptionCasting;
    @FXML
    private TableColumn<Casting, String> DateCasting;
    @FXML
    private TableColumn<Casting, String> DateLP;
    @FXML
    private TableColumn<Casting, String> AdresseCasting;
    @FXML
    private TableColumn<Casting, String> ImageCasting;
    @FXML
    private TableColumn<Casting, String> ThemeCasting;
    @FXML
    private Button ajouterCasting;
    @FXML
    private Button modifierCasting;
    @FXML
    private Button supprimerCasting;
    @FXML
    private Tab AfficheCasting;
    @FXML
    private TableColumn<Events, String> TitreEvents;
    @FXML
    private TableColumn<Events, String> DescriptionEvents;
    @FXML
    private TableColumn<Events, String> ThemeEvents;
    @FXML
    private TableColumn<Events, String> AdresseEvents;
    @FXML
    private TableColumn<Events, String> DateEvents;
    @FXML
    private TableColumn<Events, String> HeureEvents;
    @FXML
    private TableColumn<Events, String> PrixEvents;
    @FXML
    private TableColumn<Events, String> ImageEvents;
    @FXML
    private TextField recherche1;
    @FXML
    private Button rechercheEvents;
    @FXML
    private Button AjouterEvents;
    @FXML
    private Button modifierEvents;
    @FXML
    private Button supprimerEvents;
    @FXML
    private Tab afficheEvents;
    @FXML
    private TableView<Events> tableViewEvents;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       initCol();
       loadData();
       initColCasting();
       initColEvents();
  
    }    
    
    private void initCol() {
                 NomOrganisation.setCellValueFactory(new PropertyValueFactory<>("NomOrganisation"));
                 Apropos.setCellValueFactory(new PropertyValueFactory<>("Apropos"));
                 AdresseOrganisation.setCellValueFactory(new PropertyValueFactory<>("AdresseOrganisation"));
                 tel_organisation.setCellValueFactory(new PropertyValueFactory<>("tel_organisation"));
                 Email_Org.setCellValueFactory(new PropertyValueFactory<>("Email_Org"));
                 LoginOrganisation.setCellValueFactory(new PropertyValueFactory<>("LoginOrganisation"));
                 PasswordOrganisation.setCellValueFactory(new PropertyValueFactory<>("PasswordOrganisation"));
    }
     private void initColCasting() {
                 TitreCasting.setCellValueFactory(new PropertyValueFactory<>("TitreCasting"));
                 DescriptionCasting.setCellValueFactory(new PropertyValueFactory<>("DescriptionEvents"));
                 DateCasting.setCellValueFactory(new PropertyValueFactory<>("DateCasting"));
                 DateLP.setCellValueFactory(new PropertyValueFactory<>("DateLP"));
                 AdresseCasting.setCellValueFactory(new PropertyValueFactory<>("AdresseCasting"));
                 ImageCasting.setCellValueFactory(new PropertyValueFactory<>("ImageCasting"));
                 ThemeCasting.setCellValueFactory(new PropertyValueFactory<>("ThemeCasting"));

    }
      private void initColEvents() {
                 TitreEvents.setCellValueFactory(new PropertyValueFactory<>("TitreEvents"));
                 DescriptionEvents.setCellValueFactory(new PropertyValueFactory<>("DescriptionEvents"));
                 ThemeEvents.setCellValueFactory(new PropertyValueFactory<>("ThemeEvents"));
                 AdresseEvents.setCellValueFactory(new PropertyValueFactory<>("AdresseEvents"));
                 PrixEvents.setCellValueFactory(new PropertyValueFactory<>("PrixEvents"));
                 ImageEvents.setCellValueFactory(new PropertyValueFactory<>("ImageEvents"));
                 
                 DateEvents.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Events, String>, ObservableValue<String>>() {
                  @Override
                     public ObservableValue<String> call(TableColumn.CellDataFeatures<Events, String> param) {
                     String result = param.getValue().getDateEvents().toLocalDate().toString();
                     return new SimpleObjectProperty<String>(result);
    }
                       });
                  HeureEvents.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Events, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Events, String> param) {
               String result = param.getValue().getDateEvents().toLocalTime().toString();
                return new SimpleObjectProperty<String>(result);
            }
        });
      }
                 
                
    
    public void loadData() {
       

        try {
            organisationstList = FXCollections.observableArrayList(organisationservice.afficherOrganisations());
            castingList = FXCollections.observableArrayList(castingService.afficherCasting()); 
            eventsList = FXCollections.observableArrayList(eventsService.findAll()); 
           
            
        } catch (SQLException ex) {
            
        }
    
       
         
        tableViewCasting.setItems(castingList);
        tableViewOrganisations.setItems(organisationstList);
        tableViewEvents.setItems(eventsList);

    }
    

    @FXML
    private void showDetaille(ActionEvent event) {
    }

    @FXML
    private void ajouterOrganisations(ActionEvent event) {
               FXMLLoader loader = Rooting.navigate("Ajouter", "OrganisationsFormulaire");

    }

    @FXML
    private void modifierOrganisations(ActionEvent event) {
       Organisations selectedOrganisations  = tableViewOrganisations.getSelectionModel().getSelectedItem();
        if (selectedOrganisations==null){
            System.out.println("choisir une Organisation");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "OrganisationsFormulaire");
        OrganisationsFormulaireController controller = (OrganisationsFormulaireController) loader.getController();
         controller.initfields(selectedOrganisations,this);
        
        }
    }

    @FXML
    private void supprimerOrganisations(ActionEvent event) throws SQLException {
        Organisations selectedOrganisations  = tableViewOrganisations.getSelectionModel().getSelectedItem();
        if (selectedOrganisations==null){
            System.out.println("choisir une organisation");
            
        }else{
        os.supprimerOrganisations(selectedOrganisations);
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionOr");
        ValidationDeSuppressionOrController controller = (ValidationDeSuppressionOrController) loader.getController();
        controller.initfieldsOrg(selectedOrganisations,this);
        
        }
      
     
    }
//******************************************* Back Casting ***************************************************//
    
  
      
    @FXML
    private void ajouterCasting(ActionEvent event) {
                       FXMLLoader loader = Rooting.navigate("Ajouter Casting", "CastingFormulaire");
    }

    @FXML
    private void modifierCasting(ActionEvent event) {
        Casting selectedCasting  = tableViewCasting.getSelectionModel().getSelectedItem();
        if (selectedCasting==null){
            System.out.println("choisir une casting");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "CastingFormulaire");
        CastingFormulaireController controller = (CastingFormulaireController) loader.getController();
         controller.initfields(selectedCasting,this);
        
        }
    }

    @FXML
    private void supprimerCasting(ActionEvent event) throws SQLException   {
         Casting selectedCasting  = tableViewCasting.getSelectionModel().getSelectedItem();
        if (selectedCasting==null){
            System.out.println("choisir un casting");
            
        }else{
        cs.supprimerCasting(selectedCasting);    
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppressionOr");
                

        ValidationDeSuppressionOrController controller = (ValidationDeSuppressionOrController) loader.getController();
        controller.initfieldsCasting(selectedCasting,this);
        
        }

    }

    
    //******************************************* Back Events ***************************************************//
   
    @FXML
    private void AjouterEvents(ActionEvent event) {
        FXMLLoader loader = Rooting.navigate("Ajouter", "EventsFormulaire");
        loadData();
    }

    @FXML
    private void modifierEvents(ActionEvent event) {
        Events selectedEvents  = tableViewEvents.getSelectionModel().getSelectedItem();
        if (selectedEvents==null){
            System.out.println("choisir une evenements");
            
        }else{
         
        FXMLLoader loader = Rooting.navigate("modif", "EventsFormulaire");
        EventsFormulaireController controller = (EventsFormulaireController) loader.getController();
         controller.initfieldsEvents(selectedEvents,this);
    }
    }


    @FXML
    private void supprimerEvents(ActionEvent event) throws SQLException {
        Events selectedEvents  = tableViewEvents.getSelectionModel().getSelectedItem();
        if (selectedEvents==null){
            System.out.println("choisir un evenement");
            
        }else{
        es.supprimerEvents(selectedEvents);    
        FXMLLoader loader = Rooting.navigate("supprimer", "ValidationDeSuppression");
                

        ValidationDeSuppressionOrController controller = (ValidationDeSuppressionOrController) loader.getController();
        controller.initfieldsEvents(selectedEvents,this);
        
        }

    }
 
    @FXML
    private void rechercheEvents(ActionEvent event) throws SQLException  {
         String val = this.recherche.getText();
       ObservableList<Events> events = FXCollections.observableArrayList( eventsService.Recherche(val));       
        tableViewEvents.setItems(events);
        tableViewEvents.refresh();
       // System.out.println("wselt controller zeda");
       // System.out.println(val);
        //System.out.println(cours);
    }

    @FXML
    private void condidature(ActionEvent event) {
         Rooting.navigate("condidature", "BackOfice");
          closeStage();
          
    }

    @FXML
    private void organisation(ActionEvent event) {
         Rooting.navigate("condidature", "BackOfficeOrganisations");
          closeStage();
    }

    @FXML
    private void utilisateur(ActionEvent event) {
                Rooting.navigate("profile", "BackOficeProfile");
          closeStage();
    
    }

    @FXML
    private void publication(ActionEvent event) {
    }

    @FXML
    private void produit(ActionEvent event) {
    }

    @FXML
    private void formation(ActionEvent event) {
           Rooting.navigate("profile", "ListFormation_1");
          closeStage();
        
    }
    
       
    private void closeStage() {
        ((Stage) AjouterEvents.getScene().getWindow()).close();
    }
    
    
        }
    
