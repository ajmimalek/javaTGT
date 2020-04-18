/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Organisations;

import com.esprit.pidev.tgt.utils.*;
import static java.awt.Event.INSERT;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author islem
 */
public class CastingService implements ICastingService {

    private Statement statement;
    public CastingService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CandidatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //************************************************AJOUTER CASTING************************************************//

    @Override
    public void ajouterCasting(Casting C) {
        try {
           String requete =" INSERT INTO casting (TitreCasting ,DescriptionCasting,DateCasting ,DateLP ,AdresseCasting,ImageCasting ,ThemeCasting) VALUES (?,?,?,?,?,?,?)";
           PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, C.getTitreCasting());
            preparedStatement.setString(2, C.getDescriptionCasting());
            preparedStatement.setDate(3, new java.sql.Date(C.getDateCasting().getTime()));
            preparedStatement.setDate   (4, new java.sql.Date(C.getDateLP().getTime()));
            preparedStatement.setString(5, C.getAdresseCasting());
            preparedStatement.setString(6, C.getImageCasting());
            preparedStatement.setString(7, C.getThemeCasting());

            preparedStatement.executeUpdate();
            System.out.println("Casting ajoutée !");

        } catch (SQLException ex) {
                       Logger.getLogger(CastingService.class.getName()).log(Level.SEVERE, null, ex);

        }}

     //************************************************AFFIHCER CASTING************************************************//

    
     @Override
     public List<Casting> afficherCasting() {
        List<Casting> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM casting";
           
           PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Casting(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getDate(5),rs.getDate(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     
     
     //************************************************SUPPRIMER CASTING************************************************//

    
      @Override
    public void supprimerCasting(Casting C) throws SQLException  {
          
                statement.executeUpdate("DELETE FROM `casting` WHERE id =" + C.getId()); //To change body of generated methods, choose Tools | Templates.

    }
    
     //************************************************MODIFIER CASTING************************************************//


     @Override
    public void modifierCasting(Casting C) {
        try {
            String requete = "UPDATE casting SET DateCasting=? WHERE id=?";
           PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, (Date) C.getDateCasting());
            preparedStatement.setInt(2, C.getId());
            preparedStatement.executeUpdate();
            System.out.println("casting modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    //************************************************FIND ALL************************************************//

    @Override
    public List<Casting> findAll() throws SQLException {
        List<Casting> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `casting`");
        while (result.next()) {
            list.add(extract(result));
        }
         return list;
        
    }        


    private Casting extract(ResultSet result)throws SQLException{
        
            int id = result.getInt("id");
            String TitreCasting = result.getString("TitreCasting");
            String DescriptionCasting= result.getString("DescriptionCasting");
            Date DateCasting= result.getDate("DateCasting");
            Date DateLP= result.getDate("DateLP");
           String AdresseCasting = result.getString("AdresseCasting");
            String ImageCasting= result.getString("ImageCasting");
             String ThemeCasting= result.getString("ThemeCasting");
            

                  
            
        return new Casting(id, TitreCasting, DescriptionCasting, DateCasting, DateLP, AdresseCasting, ImageCasting, ThemeCasting);
    }
    
     public Casting findById(int id) throws SQLException{
       
        ResultSet result = statement.executeQuery("SELECT * FROM `casting` WHERE id =" + id);
        return result.next()? extract(result) : null;
    }
}


