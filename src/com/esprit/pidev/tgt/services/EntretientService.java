/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Candidat;
import com.esprit.pidev.tgt.entities.Casting;
import com.esprit.pidev.tgt.entities.Entretien;
import com.esprit.pidev.tgt.enumeration.Role;
import com.esprit.pidev.tgt.enumeration.StatutEnt;
import com.esprit.pidev.tgt.utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author goldzeo
 */
public class EntretientService  implements IEntretientService{
    
    private Statement statement;
    public EntretientService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EntretientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int save(Entretien entretient) throws SQLException {
        String req = "INSERT INTO `entretien` (`id`, `dateEnt`, `statutEnt`, `noteEnt`) VALUES (NULL, '"+entretient.getDateEnt()+"', '"+entretient.getStatutEnt()+"', '"+entretient.getNoteEnt()+"')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int id = genKeysRs.getInt(1);
        return id;
    }

    @Override
    public Entretien findByUsername(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entretien findById(int id) throws SQLException{
       
        ResultSet result = statement.executeQuery("SELECT * FROM `entretien` WHERE id =" + id);
        return result.next()? extract(result) : null;
    }
    
    
    private Entretien extract(ResultSet result)throws SQLException{
           int id = result.getInt("id");
           LocalDateTime dateEnt= result.getTimestamp("dateEnt").toLocalDateTime();
           StatutEnt statutEnt = StatutEnt.valueOf(result.getString("statutEnt"));
           float noteEnt =result.getFloat("noteEnt"); 
            return new Entretien(id, dateEnt, statutEnt, noteEnt);
    }
    
    public boolean notter(Entretien entretien) throws SQLException {
        String reqUpdate="UPDATE `entretien` SET ` `statutEnt`="+entretien.getStatutEnt().accepte+",`noteEnt`="+entretien.getNoteEnt()+" WHERE `id` =" +  ;
         return statement.executeUpdate(reqUpdate)>0; //To change body of generated methods, choose Tools | Templates.
    }
}
