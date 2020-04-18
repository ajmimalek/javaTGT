/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;


import com.esprit.pidev.tgt.entities.Casting;
import java.sql.SQLException;
import java.util.List;



/**
 *
 * @author goldzeo
 * @param <C>
 */
public interface ICastingService{
     public void ajouterCasting(Casting C);
    public List<Casting> afficherCasting() throws SQLException;
    public void supprimerCasting(Casting C) throws SQLException;
    public void modifierCasting(Casting C);
    public List<Casting> findAll() throws SQLException;
}
