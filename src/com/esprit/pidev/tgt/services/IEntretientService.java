/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Entretien;
import java.sql.SQLException;

/**
 *
 * @author goldzeo
 */
public interface IEntretientService {
    public int save(Entretien entretient) throws SQLException;
    public Entretien findByUsername(String username) throws SQLException; 

    public Entretien findById(int id)throws SQLException;
    
}
