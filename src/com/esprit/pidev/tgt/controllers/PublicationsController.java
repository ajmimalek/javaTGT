/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Publication;
import com.esprit.pidev.tgt.services.PublicationService;
import com.esprit.pidev.tgt.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author oXCToo
 */
public class PublicationsController implements Initializable {

    @FXML
    private VBox pnl_scroll;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btnlist;
    
    PublicationService ps = new PublicationService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void AjouterPublication(ActionEvent event) {
        pnl_scroll.getChildren().clear();
        try {
            Node n = (Node) FXMLLoader.load(getClass().getResource("/com/esprit/pidev/tgt/views/AjoutPublication.fxml"));
            pnl_scroll.getChildren().add(n);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            AlertMaker.showErrorMessage(ex);
        }
    }

    @FXML
    public void AfficherPublications(ActionEvent event) {
        pnl_scroll.getChildren().clear();
        //Create Node Tables
        Node[] nodes = new Node[30];
        //Declare a counter and the list
        int i =0;
        List<Publication> list = ps.afficher();
        for (Publication publication : list) {
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/pidev/tgt/views/ItemPublication.fxml"));
                ItemPublicationController ic = new ItemPublicationController();
                loader.setController(ic);
                nodes[i] = loader.load();
                pnl_scroll.getChildren().add(nodes[i]);
                i++;
                ic.showPublication(publication,nodes,i,list.size());
               
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                AlertMaker.showErrorMessage(ex);
            }
        }
        if (pnl_scroll.getChildren().isEmpty()) {
            AlertMaker.showSimpleAlert("Pas de publications", "Veuillez ajouter des publications");
        }
    }

}
