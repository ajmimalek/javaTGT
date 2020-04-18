package com.esprit.pidev.tgt.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.esprit.pidev.tgt.controllers.EventsFormulaireController.saveToFileImageNormal;
import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.services.EventsService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class EventsFormulaireController implements Initializable {
    
    private Statement statement;
    private EventsService eventsService = new EventsService();
    private Events events;
    private BackOfficeOrganisationsController backOfficeOrganisationsController;
    
    
    @FXML
    private TextField TitreEvents;
    @FXML
    private TextArea DescriptionEvents;
    @FXML
    private ComboBox<String> ThemeEvents;
     ObservableList <String> listeThemeEvents = FXCollections.observableArrayList("Culture","Economie","Politique","Scientifique","Social","Sport");
    @FXML
    private TextField AdresseEvents;
    @FXML

    private JFXTimePicker HeureEvents;
    @FXML
    private TextField PrixEvents;
    @FXML
    private ImageView ImageEvents;
    @FXML
    private Button AjouterEvents;
    @FXML
    private JFXDatePicker DateEvents;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ThemeEvents.setItems(listeThemeEvents);
    }    

    @FXML
    private void AjouterEvents(ActionEvent event) throws SQLException, IOException {
        Events  E = new Events();
        EventsService es = new EventsService();
        
        E.setTitreEvents(TitreEvents.getText());
        E.setDescriptionEvents(DescriptionEvents.getText());
        E.setThemeEvents((String) ThemeEvents.getValue());
        E.setAdresseEvents(AdresseEvents.getText());
        E.setPrixEvents(Integer.valueOf(this.PrixEvents.getText()));
        E.setDateEvents( LocalDateTime.of(DateEvents.getValue(), HeureEvents.getValue()));
        
        Image img = ImageEvents.getImage();
        String imgFile = saveToFileImageNormal(img);
        E.setImageEvents(imgFile);
 
        es.save(E);
        System.out.println(es);
       
        
    }
    
            public void setevents(Events events){
                this.events=events;
                initInput();
                } 
            

        void initInput (){
       this.TitreEvents.setText(events.getTitreEvents());
       this.DescriptionEvents.setText(events.getDescriptionEvents());
              this.AdresseEvents.setText(events.getAdresseEvents());
               this.ThemeEvents.setValue(events.getThemeEvents());
               Calendar calendar = Calendar.getInstance();
                this.DateEvents.setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));
       this.PrixEvents.setText(events.getPrixEvents()+"");

         }

    
      
     void initfieldsEvents(Events selectedEvents, BackOfficeOrganisationsController backOfficeOrganisationsController) {
       this.events=selectedEvents;
       this.backOfficeOrganisationsController= backOfficeOrganisationsController;
       initInput();
    }

      public static String saveToFileImageNormal(Image image) throws SQLException, IOException {
        
        String ext = "jpg";
        File dir = new File("C:/wamp64/www/javaPiDEV/javaTGT/javaTGT/src/resources/images");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
        return name;
    }
     
    @FXML
    private void addImage(MouseEvent event) {
          FileChooser fc = new FileChooser();
        
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image imageF = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageEvents.setImage(imageF);
        } catch (IOException ex) {
            System.out.println("add image");
        }
    }
}
    
