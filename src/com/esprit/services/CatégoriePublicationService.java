/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.CatégoriePublication;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                list.add(new CatégoriePublication(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
