/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Organisations;
import com.esprit.pidev.tgt.entities.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author islem
 */
public interface IOrganisationsService  {
    public void ajouterOrganisations(Organisations O);
    public List<Organisations> afficherOrganisations() throws SQLException;
    public void supprimerOrganisations(Organisations O) throws SQLException;
    public void modifierOrganisations(Organisations O);
    public List<Organisations> findAll() throws SQLException ;
}
