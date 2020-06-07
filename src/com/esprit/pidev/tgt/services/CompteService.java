/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import com.esprit.pidev.tgt.entities.Compte;
import com.esprit.pidev.tgt.enumeration.Role;
import com.esprit.pidev.tgt.utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Seif Henchir
 */
public class CompteService  implements ICompteService{
    
    private Statement statement;
    public CompteService() {
        try {
            this.statement = DataSource.getInstance().getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CompteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int save(Compte compte) throws SQLException{
        String req = "INSERT INTO `compte` (`id`, `username`, `password`,`roleType`)"
                +" VALUES (NULL, '"+compte.getUsername()+"', '"+compte.getPassword()+"','"+compte.getRoleType().talent+"')";
        PreparedStatement preparedStatement= DataSource.getInstance().getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        int idCompte = genKeysRs.getInt(1);
        return idCompte;
    }
    
         @Override
         public List<Compte> findAll() throws SQLException {
        List<Compte> list = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM `compte`");
        while (result.next()) {
            list.add(extractCompte(result));
        }
        return list;
        }
    
    
          @Override
          public void delete(Compte compte ) throws SQLException {
         statement.executeUpdate("DELETE FROM `compte` WHERE id =" + compte.getId()); //To change body of generated methods, choose Tools | Templates.
              }
    
    
    
    
    
       @Override
    public Compte findByUsername(String username) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM `compte` WHERE username = '" + username+"'");
        System.out.println(result);
        return result.next()? extractCompte(result) : null;
    }

   // @Override
    public Compte findById(int id) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM `compte` WHERE id =" + id);
        return result.next()? extractCompte(result) : null;
    }
        private Compte extractCompte(ResultSet result)throws SQLException{
        
            int id = result.getInt("id");
            String username = result.getString("username");
            String password = result.getString("password");
            Role roleType = Role.valueOf(result.getString("roleType"));
        return new Compte(id, username, password, roleType);
    }
        
       @Override
    public boolean update(Compte compte) throws SQLException {
         String reqUpdate="UPDATE `compte` SET `password`='"+compte.getPassword()+"' WHERE id = '"+compte.getId()+"'"; 
        return statement.executeUpdate(reqUpdate)>0; //To change body of generated methods, choose Tools | Templates.
    }    

   
}
