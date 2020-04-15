/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.controllers.BackOfficeController;
import com.esprit.models.CatégoriePublication;
import com.esprit.utils.DataSource;
import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Malek
 */
public class CatégoriePublicationService implements IServices<CatégoriePublication>{
    
    Connection connection = DataSource.getInstance().getConnection();

    @Override
    public void ajouter(CatégoriePublication t) {
         try {
            String req = "ALTER TABLE categoriePublication AUTO_INCREMENT = "+ Statement.RETURN_GENERATED_KEYS;
            String requete = "INSERT INTO categoriePublication (nomCat) VALUES (?)";
            PreparedStatement pst = connection.prepareStatement(requete);
            PreparedStatement pst1 = connection.prepareStatement(req);
            pst.setString(1, t.getNomCat());
            pst1.execute();
            pst.executeUpdate();
            System.out.println("Catégorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(CatégoriePublication t) {
        try {
            String requete = "DELETE FROM categoriePublication WHERE id_cat=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_cat());
            pst.executeUpdate();
            System.out.println("Catégorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void supprimerParID(int id_cat){
        try {
            String requete = "DELETE FROM categoriePublication WHERE id_cat=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, id_cat);
            pst.executeUpdate();
            System.out.println("Catégorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CatégoriePublication t) {
         try {
            String requete = "UPDATE categoriePublication SET nomCat=? WHERE id_cat=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(2, t.getId_cat());
            pst.setString(1, t.getNomCat());
            pst.executeUpdate();
            System.out.println("Catégorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<CatégoriePublication> afficher() {
        List<CatégoriePublication> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categoriePublication";
            PreparedStatement pst = connection.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                JFXButton button = new JFXButton("Supprimer");
                button.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white;");
                int current_row_number = rs.getRow();
                button.setId(Integer.toString(current_row_number));
                list.add(new CatégoriePublication(rs.getInt(1),rs.getString(2), button));
                button.setOnAction((event) -> {
                        this.supprimerParID(Integer.parseInt(button.getId()));
                        BackOfficeController.oblist.remove(Integer.parseInt(button.getId()));
                });
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
