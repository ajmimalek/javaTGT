/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Events;
import com.esprit.pidev.tgt.utils.*;
import static java.awt.event.PaintEvent.UPDATE;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.PropertyDescriptor.SET;


/**
 *
 * @author islem
 */
public class EventsService implements IEventsService {

  
   private Statement statement;
    public EventsService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EntretientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Override
    public int save(Events events) throws SQLException {
        String req = "INSERT INTO `events` (`id`,`TitreEvents`, `DescriptionEvents`, `ThemeEvents`,`AdresseEvents`,`DateEvents`,`prixEvents`,`ImageEvents`) VALUES (NULL, '"+events.getTitreEvents()+"', '"+events.getDescriptionEvents()+"','"+events.getThemeEvents()+"', '"+events.getAdresseEvents()+"', '"+events.getDateEvents()+"', '"+events.getPrixEvents()+"', '"+events.getImageEvents()+"')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        return id;
    }
    
    @Override
    public Events findByUsername(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Events findById(int id) throws SQLException{
       
        ResultSet result = statement.executeQuery("SELECT * FROM `events` WHERE id =" + id);
        return result.next()? extract(result) : null;
    }
    
    @Override
    public List<Events> findAll() throws SQLException {
        List<Events> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `events`");
        while (result.next()) {
            list.add(extract(result));
        }
        return list;
    }
    
    private Events extract(ResultSet result)throws SQLException{
           int id = result.getInt("id");
           String TitreEvents = result.getString("TitreEvents");
                      String DescriptionEvents = result.getString("DescriptionEvents");
                      String ThemeEvents = result.getString("ThemeEvents");
                      String AdresseEvents = result.getString("AdresseEvents");
                      LocalDateTime DateEvents= result.getTimestamp("DateEvents").toLocalDateTime();
                      int PrixEvents = result.getInt("PrixEvents");
                      String ImageEvents = result.getString("ImageEvents");
                      
           
            return new Events(id, TitreEvents,DescriptionEvents,ThemeEvents,AdresseEvents,DateEvents,PrixEvents,ImageEvents);
    }
    
  @Override
    public void modifierEvents(Events E) {
        try {
            String requete = "UPDATE casting SET AdresseEvents=? WHERE id=?";
           PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,E.getAdresseEvents());
            preparedStatement.setInt(2, E.getId());
            preparedStatement.executeUpdate();
            System.out.println("Events modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
      @Override
    public void supprimerEvents(Events E) throws SQLException  {
                       statement.executeUpdate("DELETE FROM `events` WHERE id =" + E.getId()); //To change body of generated methods, choose Tools | Templates.

    }
    
    @Override
    public List<Events> Recherche(String val) throws SQLException {
        List<Events> list = new ArrayList<>();
       String requete = "SELECT * FROM `events` WHERE TitreEvents like ?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1,"%"+val+"%");
            pst.executeQuery();
            ResultSet result = pst.getResultSet();
            System.out.println("wsetl service");
            System.out.println(val);
            
        while (result.next()) {
            System.out.println("while");
            list.add(extract(result));
        }
        return list;
    }
    
    
}
