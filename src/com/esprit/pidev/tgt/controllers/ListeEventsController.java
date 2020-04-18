/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.services.EventsService;
import com.esprit.pidev.tgt.services.IEventsService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class ListeEventsController implements Initializable {
    
      
        ObservableList<Events> eventsList = FXCollections.observableArrayList();
        IEventsService eventsservice = new EventsService();
        private EventsService eventsService = new EventsService();
        
    @FXML
    private TableView<Events> ListeEvents;
    @FXML
    private TableColumn<Events, String> ImageEvents;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initColEvents();
        loadData();
        
    }    
    
    private void initColEvents() {
                 TitreEvents.setCellValueFactory(new PropertyValueFactory<>("TitreEvents"));
                 DescriptionEvents.setCellValueFactory(new PropertyValueFactory<>("DescriptionEvents"));
                 ThemeEvents.setCellValueFactory(new PropertyValueFactory<>("ThemeEvents"));
                 AdresseEvents.setCellValueFactory(new PropertyValueFactory<>("AdresseEvents"));
                 PrixEvents.setCellValueFactory(new PropertyValueFactory<>("PrixEvents"));               
                 
               //ImageEvents.setCellValueFactory(new PropertyValueFactory<>("ImageEvents"));
                 
              ImageEvents.setCellFactory(param -> {
                
        //Set up the ImageView
        final ImageView imageview = new ImageView();
        imageview.setFitHeight(10);
        imageview.setFitWidth(10);
        //imageview.setImage(imageComputer); //uncommenting this places the image on all cells, even empty ones
        //Set up the Table
        TableCell<Events, String> cell = new TableCell<Events, String>() {
        @Override
        public void updateItem (String item , boolean empty){
            if (item != null) {                                  
                // choice of image is based on values from item, but it doesn't matter now
                Class<?> clazz = this.getClass();
                System.out.println(item);
                InputStream input = clazz.getResourceAsStream("photo.jpg");
                //  input = new FileInputStream("C:/wamp64/www/javaPiDEV/javaTGT/javaTGT/src/resources/images/IHTSTZvs.jpg");
                Image image = new Image(input);
                imageview.setImage(image);
             
                }
        }
        };

        // Attach the imageview to the cell
        cell.setGraphic(imageview);
        return cell;
    
                });
    
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
            
            eventsList = FXCollections.observableArrayList(eventsService.findAll()); 
            
            
            
        } catch (SQLException ex) {
            
        }
        ListeEvents.setItems(eventsList);

    }
}
