/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.enumeration.Genre;
import com.esprit.pidev.tgt.services.CastingService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class CastingFormulaireController implements Initializable {
    
     private Statement statement;
    private CastingService castingService = new CastingService();
    private Casting casting;
    private BackOfficeOrganisationsController backOfficeOrganisationsController;

    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker DateCasting;
    @FXML
    private DatePicker DateLimite;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<String> theme;
        ObservableList <String> listeTheme = FXCollections.observableArrayList("Mode & Pub","Theatre & Humour","Cinéma & Fiction","Musique & Dance","Télévision & Radio","Peinture & Art plastique","Sports & Arts de cirque","Audiovisuel");

    @FXML
    private ImageView image;
    @FXML
    private Button ajouterCasting;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         theme.setItems(listeTheme);
    }    

    @FXML
    private void ajouterCasting(ActionEvent event) throws SQLException, IOException {
        Casting  C = new Casting();
        CastingService cs = new CastingService();
       

        C.setTitreCasting(titre.getText());
        C.setDescriptionCasting(description.getText());
        C.setDateCasting(Date.valueOf(DateCasting.getValue()));
        C.setDateLP(Date.valueOf(DateLimite.getValue()));
        C.setAdresseCasting(adresse.getText());
        
        C.setThemeCasting((String) theme.getValue());
        Image img = image.getImage();
        String imgFile = saveToFileImageNormal(img);
          C.setImageCasting(imgFile);
       
        cs.ajouterCasting(C);
        System.out.println(cs);
    }
    
    public void setcasting(Casting casting){
    this.casting=casting;
    initInput();
    }
    
    void initInput (){
    this.titre.setText(casting.getTitreCasting());
    this.description.setText(casting.getDescriptionCasting());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(casting.getDateCasting());
   this.DateCasting.setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));
  calendar.setTime(casting.getDateLP());
   this.DateLimite.setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));
    this.adresse.setText(casting.getAdresseCasting());
   this.theme.setValue(casting.getThemeCasting());
  
    }
    
    void initfields(Casting selectedCasting, BackOfficeOrganisationsController backOfficeOrganisationsController) {
       this.casting=selectedCasting;
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
            image.setImage(imageF);
        } catch (IOException ex) {
            System.out.println("add image");
        }
    }
    
    
    
    
    
    
}
