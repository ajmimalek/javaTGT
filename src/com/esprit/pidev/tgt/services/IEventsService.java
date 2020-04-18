/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Events;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author islem
 * @param <E>
 */
public interface IEventsService{
//    public void ajouterEvents(Events E);
 //  public List<Events> afficherEvents() ;
   public void supprimerEvents(Events E) throws SQLException ;
   public void modifierEvents(Events E);
   public int save(Events events) throws SQLException;
   public Events findByUsername(String username) throws SQLException;
   public Events findById(int id)throws SQLException;
   public List<Events> findAll() throws SQLException;
   public List<Events> Recherche(String val) throws SQLException;
    
}
