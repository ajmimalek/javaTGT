/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Publication;
import com.esprit.utils.DataSource;
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
public class PublicationService implements IServices<Publication> {

    Connection connection = DataSource.getInstance().getConnection();

    @Override
    public void ajouter(Publication t) {
        try {
            String req = "ALTER TABLE publication AUTO_INCREMENT = "+ Statement.RETURN_GENERATED_KEYS;
            String requete = "INSERT INTO publication (contenu,video,localisation,datePub,ratingPub) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(requete);
            PreparedStatement ps1 = connection.prepareStatement(req);
            ps.setString(1 , t.getContenu() );
            ps.setString(2, t.getVideo());
            ps.setString(3, t.getLocalisation());
            ps.setTimestamp(4, t.getDatePub());
            ps.setFloat(5, calculRatingPub(t));
            ps1.execute(req);
            ps.executeUpdate();
            System.out.println("Publication ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Publication t) {
        try {
            String requete = "DELETE FROM publication WHERE id_pub=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            pst.executeUpdate();
            System.out.println("Publication supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Publication t) {
       try {
            String requete = "UPDATE publication SET contenu='"+t.getContenu()+"',video='"+t.getVideo()+"',localisation='"+t.getLocalisation()+"' WHERE id_pub='"+t.getId_pub()+"'";
            Statement st = connection.createStatement();
            st.executeUpdate(requete);
            System.out.println("Publication modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Publication> afficher() {
        List<Publication> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM publication";
            PreparedStatement pst = connection.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Publication p = new Publication(rs.getInt(1));
                p.setContenu(rs.getString("contenu"));
                p.setVideo(rs.getString("video"));
                p.setLocalisation(rs.getString("localisation"));
                p.setDatePub(rs.getTimestamp("datePub"));
                p.setRatingPub(rs.getFloat("ratingPub"));
                list.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public float calculRatingPub(Publication t){
        float ratingPub = 0;
        try {
            String requete1 = "SELECT SUM(ratingComm) FROM Commentairepublication "
                    + "WHERE id_pub=?";
            String requete2 = "SELECT Count(*) FROM Commentairepublication"
                    + "WHERE id_pub=?";
            PreparedStatement pst1 = connection.prepareStatement(requete1);
            pst1.setInt(1,t.getId_pub());
            PreparedStatement pst2 = connection.prepareStatement(requete2);
            pst2.setInt(1,t.getId_pub());
            ResultSet rs1 = pst1.executeQuery();
            ResultSet rs2 = pst2.executeQuery();
            int SUMRating = Integer.parseInt(rs1.getObject(1).toString());
            int nbpubs = Integer.parseInt(rs2.getObject(1).toString());
            ratingPub = SUMRating/nbpubs;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ratingPub ;
    }
}
