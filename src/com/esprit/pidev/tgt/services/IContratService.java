/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Contrat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author goldzeo
 */
public interface IContratService {
      public int save(Contrat contrat) throws SQLException; 
    public List<Contrat> findAll() throws SQLException;
    public boolean update(Contrat contrat) throws SQLException;
    public void delete(Contrat contrat) throws SQLException;
    public void deleteAll() throws SQLException;
    public Contrat findById(int id) throws SQLException;
    
}
