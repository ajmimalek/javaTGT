/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Entretien;
import com.esprit.pidev.tgt.utils.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author goldzeo
 */
public class CastingService {

     private Statement statement;
    public CastingService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EntretientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public Casting findById(int id) throws SQLException{
       
        ResultSet result = statement.executeQuery("SELECT * FROM `casting` WHERE id =" + id);
        return result.next()? extract(result) : null;
    }
    
    
    private Casting extract(ResultSet result)throws SQLException{
            int id = result.getInt("id");
            String titreCasting = result.getString("TitreCasting"); 
            return new Casting(id, titreCasting);
    }
    
}
