/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;
import com.esprit.pidev.tgt.entities.Cour;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Nadhem
 */
public interface ICourService {
    public int save(Cour cour) throws SQLException;
    public Cour findByUsername(String username) throws SQLException; 
    public List<Cour> findAll() throws SQLException;
    public boolean update(Cour cour) throws SQLException;
    public void delete(Cour cour) throws SQLException;
    public void supprimer(Cour cour) throws SQLException;
    public void deleteAll() throws SQLException;
    public List<Cour> findByFormationId(int id) throws SQLException;
    public boolean updateNote(Cour cour) throws SQLException; 
}

