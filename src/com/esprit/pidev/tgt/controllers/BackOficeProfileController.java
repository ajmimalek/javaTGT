/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.controllers;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.entities.Utilisateur;
import com.esprit.pidev.tgt.services.CompteService;
import com.esprit.pidev.tgt.services.ICompteService;
import com.esprit.pidev.tgt.services.IUtilisateurService;
import com.esprit.pidev.tgt.services.UtilisateurService;
import com.esprit.pidev.tgt.utils.Rooting;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Seif Henchir
 */
public class BackOficeProfileController implements Initializable {

    ObservableList<Utilisateur> utilisateurtList = FXCollections.observableArrayList();
    IUtilisateurService utilisateurservice = new UtilisateurService();

    ObservableList<Compte> comptetList = FXCollections.observableArrayList();
    ICompteService compteservice = new CompteService();

    UtilisateurService utilisateurs = new UtilisateurService();
    CompteService comptes = new CompteService();

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
    private Button modifier;
    @FXML
    private Button suprimer;

    @FXML
    private TableView<Compte> tableViewCompte;
    @FXML
    private TableColumn<Compte, String> us;
    @FXML
    private TableColumn<Compte, String> ps;
    @FXML
    private TableColumn<Compte, String> ro;
    @FXML
    private Button suppcomp;

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
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        avatarUrl.setCellValueFactory(new PropertyValueFactory<>("avatarUrl"));

        username.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Utilisateur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Utilisateur, String> param) {
                String result = param.getValue().getCompte().getUsername();
                return new SimpleObjectProperty<String>(result);
            }
        });
        password.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Utilisateur, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Utilisateur, String> param) {
                String result = param.getValue().getCompte().getPassword();
                return new SimpleObjectProperty<String>(result);
            }
        });

        us.setCellValueFactory(new PropertyValueFactory<>("username"));
        ps.setCellValueFactory(new PropertyValueFactory<>("password"));
        ro.setCellValueFactory(new PropertyValueFactory<>("roleType"));

    }

    public void loadData() {

        try {
            utilisateurtList = FXCollections.observableArrayList(utilisateurservice.findAll());
            System.out.println(utilisateurtList);
        } catch (SQLException ex) {

        }

        tableViewUtilisateur.setItems(utilisateurtList);

        try {
            comptetList = FXCollections.observableArrayList(compteservice.findAll());
            System.out.println(comptetList);
        } catch (SQLException ex) {

        }

        tableViewCompte.setItems(comptetList);

    }

    @FXML
    private void modifier(ActionEvent event) {
        Utilisateur selectedUtilisateur = tableViewUtilisateur.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur == null) {
            System.out.println("choisir un utilisateur");

        } else {

            FXMLLoader loader = Rooting.navigate("modif", "modifierProfile");
            ModifierProfileController controller = (ModifierProfileController) loader.getController();
            controller.initfields(selectedUtilisateur, this);

        }
    }

    @FXML
    private void suprimer(ActionEvent event) throws SQLException {
        Utilisateur selectedUtilisateur = tableViewUtilisateur.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur == null) {
            System.out.println("choisir un utilisateur");

        } else {
            utilisateurs.delete(selectedUtilisateur);
            utilisateurtList.remove(selectedUtilisateur);
            //FXMLLoader loader = Rooting.navigate("Admin", "ValidationDeSuppressionProfile");
            ValidationDeSuppressionProfileController controller = new ValidationDeSuppressionProfileController();
            controller.initfields(selectedUtilisateur, this);
        }

    }

    @FXML
    private void suppcomp(ActionEvent event) throws SQLException {
        Compte selectedCompte = tableViewCompte.getSelectionModel().getSelectedItem();
        if (selectedCompte == null) {
            System.out.println("choisir un compte");

        } else {
            comptes.delete(selectedCompte);
            comptetList.remove(selectedCompte);
            //FXMLLoader loader = Rooting.navigate("Admin", "ValidationDeSuppressionCompte");
            ValidationDeSuppressionCompteController controller = new ValidationDeSuppressionCompteController();
            controller.initfields(selectedCompte, this);
        }
    }

    @FXML
    private void utilisateur(ActionEvent event) {
    }
}
