/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Utilisateur;
import java.sql.SQLException;

/**
 *
 * @author Seif Henchir
 */
public interface IUtilisateurService extends IServices<Utilisateur>{
     public int save(Utilisateur utilisateur) throws SQLException;
     public boolean update(Utilisateur t) throws SQLException;
     public void delete(Utilisateur utilisateur) throws SQLException;
     public Utilisateur findByUsername(String userName) throws SQLException;
     
}
