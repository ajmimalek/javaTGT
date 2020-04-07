/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.services.CandidatService;
import com.esprit.pidev.tgt.utils.Rooting;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goldzeo
 */
public class CondidatFormulaireController implements Initializable {
  
    private Statement statement;
    private CandidatService candidatService = new CandidatService();
    private Candidat candidat;
    private BackOficeController backOficeController;
    
    @FXML
    private JFXTextField nomC;
    @FXML
    private JFXTextField cinCondidat;
    @FXML
    private JFXTextField mailaddress;
    @FXML
    private JFXTextField cv;
    @FXML
    private JFXTextArea motivation;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXButton ajoutCondidat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void ajoutCondidat(ActionEvent event) {
        if(this.candidat==null) this.candidat= new Candidat(0, null, null, null, 0, null, null, null, 0);
        this.candidat.setNomC(this.nomC.getText());
        this.candidat.setCinCondidat(Integer.valueOf(this.cinCondidat.getText()));
        this.candidat.setCv(this.cv.getText()); 
        this.candidat.setMailaddress(this.mailaddress.getText());
        this.candidat.setMotivation(this.motivation.getText());
        this.candidat.setTel(Integer.valueOf(this.tel.getText()));
       // System.out.println(candidat);
         try {
            if(this.candidat.getId() == 0){
            candidatService.save(candidat);
            }else{
            candidatService.update(candidat);
            backOficeController.loadData();
            }
            closeStage();
        } catch (Exception ex) {
           // Logger.getLogger(SingUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     private void closeStage() {
        ((Stage) cinCondidat.getScene().getWindow()).close();
    }
 public void setcandidat(Candidat candidat){
    this.candidat=candidat;
    initInput();
    }
    
    void initInput (){
    this.nomC.setText(candidat.getNomC());
    this.cinCondidat.setText(candidat.getCinCondidat()+"");
    this.cv.setText(candidat.getCv());
     this.mailaddress.setText(candidat.getMailaddress());
       this.motivation.setText(candidat.getMotivation());
      this.tel.setText(candidat.getTel()+"");
    }

    void initfields(Candidat selectedCandidat, BackOficeController backOficeController) {
       this.candidat=selectedCandidat;
       this.backOficeController= backOficeController;
       initInput();
    }
}