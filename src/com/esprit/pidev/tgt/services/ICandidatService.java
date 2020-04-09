/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Candidat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author goldzeo
 */
public interface ICandidatService {
    public int save(Candidat condidat) throws SQLException;
    public Candidat findByUsername(String username) throws SQLException; 
    public List<Candidat> findAll() throws SQLException;
    public boolean update(Candidat condidat) throws SQLException;
    public void delete(Candidat condidat) throws SQLException;
    public void deleteAll() throws SQLException;
    public Candidat findById(int id) throws SQLException;
}
