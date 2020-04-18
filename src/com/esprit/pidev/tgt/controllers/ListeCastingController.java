/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.services.CastingService;
import com.esprit.pidev.tgt.services.ICastingService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class ListeCastingController implements Initializable {
    ObservableList<Casting> castingList = FXCollections.observableArrayList();
        ICastingService castingservice = new CastingService();
        private CastingService castingService = new CastingService();

    @FXML
    private TableView<Casting> ListeCasting;
    @FXML
    private TableColumn<Casting, String> ImageCasting;
    @FXML
    private TableColumn<Casting, String> TitreCasting;
    @FXML
    private TableColumn<Casting, String> DescriptionCasting;
    @FXML
    private TableColumn<Casting, String> AdresseCasting;
    @FXML
    private TableColumn<Casting, String> ThemeCasting;
    @FXML
    private TableColumn<Casting, String> DateCasting;
    @FXML
    private TableColumn<Casting, String> DateLP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         initColCasting();
         loadData();
    }    
 
     private void initColCasting() {
                 TitreCasting.setCellValueFactory(new PropertyValueFactory<>("TitreCasting"));
                 DescriptionCasting.setCellValueFactory(new PropertyValueFactory<>("DescriptionCasting"));
                 DateCasting.setCellValueFactory(new PropertyValueFactory<>("DateCasting"));
                 DateLP.setCellValueFactory(new PropertyValueFactory<>("DateLP"));
                 AdresseCasting.setCellValueFactory(new PropertyValueFactory<>("AdresseCasting"));
                 ImageCasting.setCellValueFactory(new PropertyValueFactory<>("ImageCasting"));
                 ThemeCasting.setCellValueFactory(new PropertyValueFactory<>("ThemeCasting"));

    }
     
      public void loadData() {
       

          castingList = FXCollections.observableArrayList(castingService.afficherCasting());
          System.out.println(castingList);
    
       
         
        ListeCasting.setItems(castingList);
     
    }
    
}
