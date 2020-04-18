/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;
import com.esprit.pidev.tgt.entities.Formation;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Nadhem
 */
public interface IFormationService {
    public int save(Formation formation) throws SQLException;
    public Formation findByUsername(String username) throws SQLException; 
    public List<Formation> findAll() throws SQLException;
    public boolean update(Formation formation) throws SQLException;
            public boolean updateNote(Formation formation) throws SQLException;
    public void delete(Formation formation) throws SQLException;
    public void supprimer(Formation formation) throws SQLException;
    public void deleteAll() throws SQLException;
    public List<Formation> Recherche(String val) throws SQLException;
    public List<Formation> TrieTitreASC() throws SQLException;
    public List<Formation> TrieTitreDESC() throws SQLException;
    public List<Formation> TrieDureeASC() throws SQLException;
    public List<Formation> TrieDureeDESC() throws SQLException;
    public List<Formation> getall() throws SQLException;
    public List<Formation> Participer(Formation formation) throws SQLException;
}

