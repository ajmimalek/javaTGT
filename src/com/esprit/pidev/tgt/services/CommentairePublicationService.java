/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;
import com.esprit.pidev.tgt.entities.CommentairePublication;
import com.esprit.pidev.tgt.utils.DataSource;
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
public class CommentairePublicationService implements IServices<CommentairePublication>{
    
    Connection connection = DataSource.getInstance().getConnection();

    @Override
    public void ajouter(CommentairePublication t) {
        try {
            String req = "ALTER TABLE commentairePublication AUTO_INCREMENT = "+ Statement.RETURN_GENERATED_KEYS;
            String requete = "INSERT INTO Commentairepublication (contenu,nbinutile,dateComm,ratingComm) VALUES (?,0,?,?)";
            PreparedStatement ps = connection.prepareStatement(requete);
            PreparedStatement ps1 = connection.prepareStatement(req);
            ps.setString(1 , t.getContenu() );
            ps.setTimestamp(2, t.getDateComm());
            ps.setInt(3, t.getRatingComm());
            ps1.execute();
            ps.executeUpdate();
            System.out.println("Commentaire ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(CommentairePublication t) {
         try {
            String requete = "DELETE FROM Commentairepublication WHERE id_comment=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, t.getId_comment());
            pst.executeUpdate();
            System.out.println("Commentaire supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CommentairePublication t) {
        try {
            String requete = "UPDATE Commentairepublication SET contenu='"+t.getContenu()+"',Nbinutile='"+t.getNbinutile()+"',ratingComm='"+t.getRatingComm()+"' WHERE id_comment='"+t.getId_comment()+"'";
            Statement st = connection.createStatement();
            st.executeUpdate(requete);
            System.out.println("Commentaire modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<CommentairePublication> afficher() {
       List<CommentairePublication> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Commentairepublication";
            PreparedStatement pst = connection.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CommentairePublication cp = new CommentairePublication(rs.getInt("id_comment"));
                cp.setContenu(rs.getString("contenu"));
                cp.setNbinutile(rs.getInt("nbinutile"));
                cp.setRatingComm(rs.getInt("ratingComm"));
                cp.setDateComm(rs.getTimestamp("dateComm"));
                list.add(cp);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public List<CommentairePublication> trieParRating(int id_pub){
        List<CommentairePublication> list = new ArrayList<>();
        
        try {
            String requete = "SELECT contenu,ratingComm FROM Commentairepublication "
                    + "WHERE id_pub=? ORDER BY ratingComm";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, id_pub);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CommentairePublication cp = new CommentairePublication(rs.getInt("id_comment"));
                cp.setContenu(rs.getString("contenu"));
                cp.setNbinutile(rs.getInt("nbinutile"));
                cp.setRatingComm(rs.getInt("ratingComm"));
                cp.setDateComm(rs.getTimestamp("dateComm"));
                list.add(cp);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public List<CommentairePublication> trieParDate(int id_pub){
        List<CommentairePublication> list = new ArrayList<>();
        
        try {
            String requete = "SELECT contenu,ratingComm FROM Commentairepublication "
                    + "WHERE id_pub=? ORDER BY dateComm DESC";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, id_pub);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CommentairePublication cp = new CommentairePublication(rs.getInt("id_comment"));
                cp.setContenu(rs.getString("contenu"));
                cp.setNbinutile(rs.getInt("nbinutile"));
                cp.setRatingComm(rs.getInt("ratingComm"));
                cp.setDateComm(rs.getTimestamp("dateComm"));
                list.add(cp);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public void filtrageCommentaires(CommentairePublication t) {
         try {
            String requete = "DELETE FROM Commentairepublication WHERE nbinutile=27";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commentaires Inutiles supprimées !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
